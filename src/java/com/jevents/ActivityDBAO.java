package com.jevents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import com.jevents.model.Activity;
import com.jevents.model.Category;

public class ActivityDBAO {

    Connection con;
    private boolean conFree = true;

    // Database configuration
    public static String url = "jdbc:mysql://localhost:3308/sos";
    public static String dbdriver = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "password";

    public ActivityDBAO() throws Exception {
        try {
            /*
			 * Context initCtx = new InitialContext(); Context envCtx =
			 * (Context) initCtx.lookup("java:comp/env"); DataSource ds =
			 * (DataSource) envCtx.lookup("jdbc/BookDB"); con =
			 * ds.getConnection();
             */

            Class.forName(dbdriver);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("Exception in AccountDBAO: " + ex);
            throw new Exception("Couldn't open connection to database: " + ex.getMessage());
        }
    }

    public void remove() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected synchronized Connection getConnection() {
        while (conFree == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        conFree = false;
        notify();

        return con;
    }

    protected synchronized void releaseConnection() {
        while (conFree == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

        conFree = true;
        notify();
    }

    public boolean createActivity(Activity ac) {
        getConnection();

        // the mysql insert statement
        String query = " insert into activity (name, description,creator, time,category, latitude, longitude,location)"
                + " values (?, ?, ?, ?, ?,?,?,?)";

        // create the mysql insert preparedstatement
        try {
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, ac.getName());
            preparedStmt.setString(2, ac.getDescription());

            preparedStmt.setInt(3, ac.getCreatorID());
            preparedStmt.setTimestamp(4, ac.getTime());
            preparedStmt.setInt(5, ac.getCategory());
            preparedStmt.setFloat(6, ac.getLatitude());
            preparedStmt.setFloat(7, ac.getLongitude());
            preparedStmt.setString(8, ac.getLocation());
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        releaseConnection();

        return true;

    }

    public ArrayList<Activity> getActivities() {

        boolean status = false;
        try {
            String selectStatement = "select * from activity";
            getConnection();
            ArrayList<Activity> result = new ArrayList<Activity>();

            ResultSet rs = con.createStatement().executeQuery(selectStatement);
            while (rs.next()) {

                Activity ac = new Activity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getTimestamp(8), rs.getInt(9), rs.getFloat(10), rs.getFloat(11), rs.getString(12));
                result.add(ac);

            }

            releaseConnection();
            System.out.println(result.get(0).getId());

            return result;

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Category> getCategory() {
        boolean status = false;
        try {
            String selectStatement = "select * from category";
            getConnection();
            ArrayList<Category> result = new ArrayList<Category>();

            ResultSet rs = con.createStatement().executeQuery(selectStatement);
            while (rs.next()) {

                result.add(new Category(rs.getInt(1), rs.getString(2)));
            }
            releaseConnection();
            return result;

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return null;
    }

    public Activity getActivityByID(String id) {
        // TODO Auto-generated method stub
        try {
            String selectStatement = "select a.id,a.name,a.description,a.creator,a.status,a.joined,a.rating,a.time,c.name,a.latitude,a.longitude,a.location from activity as a,category as c where a.category=c.id and a.id = " + id;
            int creatorid = 0;
            getConnection();
            Activity ac = null;
            ResultSet rs = con.createStatement().executeQuery(selectStatement);

            if (rs.next()) {
                creatorid = rs.getInt(4);
                ac = new Activity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getTimestamp(8), rs.getString(9), rs.getFloat(10), rs.getFloat(11), rs.getString(12));
            }
            System.err.println("creatorid" + creatorid);
            String sql1 = "Select Nickname,Nationality from sos.user_profile where UserId = " + creatorid;
            ResultSet rs2 = con.createStatement().executeQuery(sql1);
            if (rs2.next()) {
                if (rs2.getString(1) != null) {
                    ac.setCreatorName(rs2.getString(1));
                } else {
                    ac.setCreatorName("User is still pending.");
                }
                if (rs2.getString(2) != null) {
                    ac.setCreatorNationlity(rs2.getString(2));
                } else {
                    ac.setCreatorNationlity("User is still pending.");

                }
            } else {
                ac.setCreatorName("No User Info");
                ac.setCreatorNationlity("No User Info");
            }

            releaseConnection();
            return ac;

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
        return null;

    }

    public void incJoined(int id) {
        // TODO Auto-generated method stub
        try {
            String selectStatement = "select joined from activity where id = " + id;
            int joined = 0;
            getConnection();
            Activity ac = null;
            ResultSet rs = con.createStatement().executeQuery(selectStatement);
            if (rs.next()) {
                joined = rs.getInt(1);
            }
            joined += 1;
            String updateStatement = "Update activity set joined = " + joined + " where id = " + id;
            con.createStatement().executeUpdate(updateStatement);
            releaseConnection();

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }

    }

    public void addPaticipant(int userID, int activityID) {
        // TODO Auto-generated method stub
        try {
            String insertStatement = "insert into participant (activity_id,participant_id) values (" + activityID + " , " + userID + ")";
            getConnection();
            con.createStatement().executeUpdate(insertStatement);
            releaseConnection();

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }

    }

    public boolean checkParticipant(int participantID, int activityID) {
        // TODO Auto-generated method stub
        String selectStatement = "select * from participant where participant_id = " + participantID + " and activity_id = " + activityID;
        ResultSet rs;
        try {
            getConnection();
            rs = con.createStatement().executeQuery(selectStatement);
            releaseConnection();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public void decJoined(int id) {
        // TODO Auto-generated method stub
        try {
            String selectStatement = "select joined from activity where id = " + id;
            int joined = 0;
            getConnection();
            Activity ac = null;
            ResultSet rs = con.createStatement().executeQuery(selectStatement);
            if (rs.next()) {
                joined = rs.getInt(1);
            }
            joined -= 1;
            String updateStatement = "Update activity set joined = " + joined + " where id = " + id;
            con.createStatement().executeUpdate(updateStatement);
            releaseConnection();

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
    }

    public void removePaticipant(int userID, int activityID) {
        // TODO Auto-generated method stub
        try {
            String deleteStatement = "delete from participant where activity_id = " + activityID + " and participant_id = " + userID;
            System.out.println(deleteStatement);
            getConnection();
            con.createStatement().executeUpdate(deleteStatement);
            releaseConnection();

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
    }

    public void rating(int userID, String activityID, String rate) {
        // TODO Auto-generated method stub
        try {
            String insertStatement = "insert into Rating (user_id,activity_id,rate) values (" + userID + " , " + activityID + " , " + rate + ")";
            getConnection();
            con.createStatement().executeUpdate(insertStatement);
            releaseConnection();

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }

    }

    public boolean checkRating(int userID, String activityID) {
        String selectStatement = "select * from rating where user_id = " + userID + " and activity_id = " + activityID;
        ResultSet rs;
        try {
            getConnection();
            rs = con.createStatement().executeQuery(selectStatement);
            releaseConnection();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public void approveActivity(String id) {
        // TODO Auto-generated method stub
        try {
            String updateStatement = "Update activity set status = 1 where id = " + id;
            Log.out("Approve Ac", updateStatement);
            getConnection();
            con.createStatement().executeUpdate(updateStatement);
            releaseConnection();

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }

    }

    public void rejectActivity(String id) {
        // TODO Auto-generated method stub
        try {
            String updateStatement = "Update activity set status = -1 where id = " + id;
            getConnection();
            con.createStatement().executeUpdate(updateStatement);
            releaseConnection();

        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        }
    }

    public ArrayList<Activity> getActivitiesByFilter(String keyword, String category, String location, String description) {

        ResultSet rs = null;
        try {
            String selectStatement = "select * from activity where status = 1 ";

            if (keyword != null && !keyword.trim().equals("")) {
                selectStatement = selectStatement + "and UPPER(name) like '%" + keyword.toUpperCase() + "%' ";
            }

            if (category != null && !category.trim().equals("")) {
                selectStatement = selectStatement + "and category = " + category + " ";
            }

            if (location != null && !location.trim().equals("")) {
                selectStatement = selectStatement + "and UPPER(location) like '%" + location.toUpperCase() + "%' ";
            }

            if (description != null && !description.trim().equals("")) {
                selectStatement = selectStatement + "and UPPER(description) like '%" + description.toUpperCase() + "%' ";
            }

            System.out.println("searchSQL: " + selectStatement);
            getConnection();
            ArrayList<Activity> result = new ArrayList<>();
            rs = con.createStatement().executeQuery(selectStatement);
            while (rs.next()) {
                Activity ac = new Activity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7),
                        rs.getTimestamp(8), rs.getInt(9), rs.getFloat(10), rs.getFloat(11), rs.getString(12));
                result.add(ac);
            }
            releaseConnection();
            return result;
        } catch (SQLException ex) {
            releaseConnection();
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
