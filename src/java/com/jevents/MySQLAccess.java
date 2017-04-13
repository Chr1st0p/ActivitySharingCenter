package com.jevents;

/**
 *
 * @author FENG0
 */
import com.jevents.utils.GeoCoding;
import com.jevents.utils.Location;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MySQLAccess {

    private final boolean debuging = false;
    private static String Salt;

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3308/sos";

    // Database credentials
    static final String DB_USER = "root";
    static final String DB_PASS = "password";

    protected Connection connect = null;
    protected Statement statement = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;

    public MySQLAccess() {
        Salt = this.GenerateSalt(10);
    }

    private String GenerateSalt(int length) {
        final String allowedChars = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ0123456789";
        Random randNum = new Random();
        char[] chars = new char[length];
        int allowedCharCount = allowedChars.length();
        for (int i = 0; i < 10; i++) {
            chars[i] = allowedChars.charAt((int) Math.floor(allowedCharCount * randNum.nextFloat()));
        }
        return new String(chars);
    }

    public void Connect() {
        try {
            Class.forName(JDBC_DRIVER);
            // Setup the connection with the DB
            connect = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            checkConnection();
            print("Connected to Database.");
        } catch (SQLException | ClassNotFoundException ex) {
            printEx(ex);
        }
    }

    protected void checkConnection() throws SQLException {
        statement.executeQuery("select 1");
    }

    public boolean checkEmail(String Username) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT * FROM sos.User WHERE LOWER(Username) = LOWER(?)");
            preparedStatement.setString(1, Username);
            resultSet = preparedStatement.executeQuery();
            boolean Username_exist = resultSet.next();
            print("checkEmail " + String.valueOf(Username_exist));
            return Username_exist;
        } catch (Exception ex) {
            printEx(ex.toString());
            return false;
        }
    }

    public boolean Signup(String Username, String Password, String Fullname, String Phone) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("INSERT INTO sos.User VALUES (default, ?, MD5(?), ?, ?, default, default, default, ?)");
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password + Salt);
            preparedStatement.setString(3, Fullname);
            preparedStatement.setString(4, Phone);
            preparedStatement.setString(5, Salt);
            boolean rs = preparedStatement.execute();
            print("Signup " + String.valueOf(rs));
            return rs;
        } catch (Exception ex) {
            printEx(ex.toString());
            return false;
        }
    }

    private String getSalt(String Username) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Salt FROM sos.User WHERE LOWER(Username) = LOWER(?)");
            preparedStatement.setString(1, Username);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("getSalt " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getString("Salt");
            } else {
                return "not exist";
            }
        } catch (Exception ex) {
            print("getSalt Err");
            printEx(ex.toString());
            return "not exist";
        }
    }

    public int Login(String Username, String password) {
        try {
            checkConnection();
            String salt = getSalt(Username);
            preparedStatement = connect.prepareStatement("SELECT UserId FROM sos.User WHERE LOWER(Username) = LOWER(?) AND password = MD5(?)");
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, password + salt);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("Login " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getInt("UserId");
            } else {
                return -1;
            }
        } catch (Exception ex) {
            print("Login Err");
            ex.printStackTrace();
            return -1;
        }
    }

    public void setLast_login(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("UPDATE sos.User SET Last_Login = now() WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            boolean rs = preparedStatement.execute();
            print("setLast_login " + String.valueOf(rs));
        } catch (Exception ex) {
            print("setLast_login Err");
            printEx(ex.toString());
        }
    }

    public void Logout(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("UPDATE sos.User SET Last_Login = now() WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            preparedStatement.execute();
            clearLast_login(UserId);
        } catch (Exception ex) {
            print("Logout Err");
            printEx(ex.toString());
        }
    }

    private void clearLast_login(int UserId) {
        try {
            preparedStatement = connect.prepareStatement("UPDATE sos.User SET Last_Login = NULL WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            preparedStatement.execute();
        } catch (Exception ex) {
            print("setLast_login Err");
            printEx(ex.toString());
        }
    }

    private void checkProfile(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT * FROM sos.User_Profile WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean Profile_exist = resultSet.next();
            print("checkProfile " + String.valueOf(Profile_exist));
            if (!Profile_exist) {
                createProfile(UserId);
            }
        } catch (Exception ex) {
            printEx(ex.toString());
        }
    }

    private void createProfile(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("INSERT INTO sos.User_Profile VALUES (default, ?, ?, ?, ?, ?, ?, default, default)");
            preparedStatement.setString(1, null);
            preparedStatement.setInt(2, 0);
            preparedStatement.setDate(3, null);
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.setInt(6, UserId);
            boolean rs = preparedStatement.execute();
            print("createProfile " + String.valueOf(rs));
        } catch (Exception ex) {
            printEx(ex.toString());
        }
    }

    public boolean checkNickname(int UserId, String Nickname) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT * FROM sos.User_Profile WHERE LOWER(Nickname) = LOWER(?) AND UserId != ?");
            preparedStatement.setString(1, Nickname);
            preparedStatement.setInt(2, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean Nickname_exist = resultSet.next();
            print("checkNickname " + String.valueOf(Nickname_exist));
            return Nickname_exist;
        } catch (Exception ex) {
            printEx(ex.toString());
            return false;
        }
    }

    public boolean updateProfile(int UserId, String Nickname, int Gender, String DOB, String Nationality, String Address) {
        checkProfile(UserId);
        Location loc = GeoCoding.GetLatLng(Address);
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("UPDATE sos.User_Profile SET Nickname = ?, Gender = ?, DOB = ?, Nationality = ?, Address = ?, Lat = ?, Lng = ? WHERE UserId = ?");
            if (Nickname.length() > 0)
                preparedStatement.setString(1, Nickname);
            else
                preparedStatement.setString(1, null);
            preparedStatement.setInt(2, Gender);
            if (DOB.length() > 0)
                preparedStatement.setString(3, DOB);
            else
                preparedStatement.setString(3, null);
            if (Nationality.length() > 0)
                preparedStatement.setString(4, Nationality);
            else
                preparedStatement.setString(4, null);
            if (Address.length() > 0) {
                preparedStatement.setString(5, Address);
                preparedStatement.setDouble(6, loc.getLat());
                preparedStatement.setDouble(7, loc.getLng());
            } else {
                preparedStatement.setString(5, null);
                preparedStatement.setDouble(6, 0.0);
                preparedStatement.setDouble(7, 0.0);
            }

            preparedStatement.setInt(8, UserId);
            boolean rs = preparedStatement.execute();
            print("updateProfile " + String.valueOf(rs));
            return rs;
        } catch (Exception ex) {
            print("updateProfile Err");
            printEx(ex.toString());
            return false;
        }
    }

    public String getNickname(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Nickname FROM sos.User_Profile WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("getNickname " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getString("Nickname");
            } else {
                return "";
            }
        } catch (Exception ex) {
            print("getNickname Err");
            ex.printStackTrace();
            return "";
        }
    }

    public String getGender(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Gender FROM sos.User_Profile WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("getGender " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getString("Gender");
            } else {
                return "";
            }
        } catch (Exception ex) {
            print("getGender Err");
            ex.printStackTrace();
            return "";
        }
    }

    public String getDOB(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT DOB FROM sos.User_Profile WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("getDOB " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getString("DOB");
            } else {
                return "";
            }
        } catch (Exception ex) {
            print("getDOB Err");
            ex.printStackTrace();
            return "";
        }
    }

    public String getNationality(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Nationality FROM sos.User_Profile WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("getNationality " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getString("Nationality");
            } else {
                return "";
            }
        } catch (Exception ex) {
            print("getNationality Err");
            ex.printStackTrace();
            return "";
        }
    }

    public String getAddress(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Address FROM sos.User_Profile WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("getAddress " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getString("Address");
            } else {
                return "";
            }
        } catch (Exception ex) {
            print("getAddress Err");
            ex.printStackTrace();
            return "";
        }
    }

    public String getFullname(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Fullname FROM sos.User WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean user_exist = resultSet.next();
            print("getFullname " + String.valueOf(user_exist));
            if (user_exist) {
                return resultSet.getString("Fullname");
            } else {
                return "";
            }
        } catch (Exception ex) {
            print("getFullname Err");
            ex.printStackTrace();
            return "";
        }
    }

    public int getUserGroup(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT User_Group FROM sos.User WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean success = resultSet.next();
            print("getUserGroup " + String.valueOf(success));
            if (success) {
                return resultSet.getInt("User_Group");
            } else {
                return -1;
            }
        } catch (Exception ex) {
            print("getUserGroup Err");
            ex.printStackTrace();
            return -1;
        }
    }

    public int getPendingCount() {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT COUNT(id) AS 'PendingCount' FROM sos.activity WHERE status = 0");
            resultSet = preparedStatement.executeQuery();
            boolean success = resultSet.next();
            print("getPendingCount " + String.valueOf(success));
            if (success) {
                return resultSet.getInt("PendingCount");
            } else {
                return -1;
            }
        } catch (Exception ex) {
            print("getPendingCount Err");
            ex.printStackTrace();
            return -1;
        }
    }

    public List getPendingActivities() {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT a.id, time, Fullname, c.name, location FROM sos.activity AS a INNER JOIN sos.user AS u on u.UserId = a.Creator INNER JOIN sos.category AS c on c.id = a.category WHERE status = 0");
            resultSet = preparedStatement.executeQuery();
            List<AdminActivity> activities = new ArrayList();
            while (resultSet.next()) {
                AdminActivity activity = new AdminActivity(String.valueOf(resultSet.getInt("id")), resultSet.getDate("time"), resultSet.getString("Fullname"), resultSet.getString("name"), resultSet.getString("location"), 0);
                activities.add(activity);
            }
            return activities;
        } catch (Exception ex) {
            print("getPendingCount Err");
            ex.printStackTrace();
            return null;
        }
    }

    public List getAllActivities() {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT a.id, time, Fullname, c.name, location, status FROM sos.activity AS a INNER JOIN sos.user AS u on u.UserId = a.Creator INNER JOIN sos.category AS c on c.id = a.category");
            resultSet = preparedStatement.executeQuery();
            List<AdminActivity> activities = new ArrayList();
            while (resultSet.next()) {
                AdminActivity activity = new AdminActivity(String.valueOf(resultSet.getInt("id")), resultSet.getDate("time"), resultSet.getString("Fullname"), resultSet.getString("name"), resultSet.getString("location"), resultSet.getInt("status"));
                activities.add(activity);
            }
            return activities;
        } catch (Exception ex) {
            print("getPendingCount Err");
            ex.printStackTrace();
            return null;
        }
    }

    public UserProfile getUserProfile(int UserId) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT * FROM sos.user_profile WHERE UserId = ?");
            preparedStatement.setInt(1, UserId);
            resultSet = preparedStatement.executeQuery();
            boolean success = resultSet.next();
            print("getUserProfile " + String.valueOf(success));
            if (success) {
                return new UserProfile(resultSet.getInt("UserProfileId"), resultSet.getString("Nickname"), resultSet.getInt("Gender"), resultSet.getDate("DOB"), resultSet.getString("Nationality"), resultSet.getString("Address"));
            } else {
                return null;
            }
        } catch (Exception ex) {
            print("getPendingCount Err");
            ex.printStackTrace();
            return null;
        }
    }

    public void Close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
        print("Database Disconnected.");
    }

    protected void print(Object o) {
        if (debuging) {
            System.out.println(o.toString());
        }
    }

    protected void printEx(Object o) {
        if (debuging) {
            System.out.println("MySQLAccess    " + o.toString());
        }
    }
}
