package com.jevents;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;

/**
 * @auther: Dante
 */
public class MySQLAccessPost extends MySQLAccess {

    private int IdActivity = 0;

    public MySQLAccessPost(int IdAct) {
        IdActivity = IdAct;
    }

    public int getLastId() {
        int MaxId = 0;
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT MAX(Id) as MaxId From sos.Post ");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                print("maxID  " + resultSet.getInt(1));
                return resultSet.getInt("MaxId") + 1;
            }
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return MaxId;
    }

    public ArrayList<Integer> getPostId(int Id) {
        ArrayList<Integer> resId = new ArrayList<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id From sos.Post where IdAct=?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    resId.add(resultSet.getInt(1));
                }
            }
            print("PostId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return resId;
    }

    public int getPostId1R(int Id) {
        int resId = 0;
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id From sos.Post where Id=?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    resId = resultSet.getInt(1);
                }
            }
            print("PostId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return resId;
    }

    public HashMap<Integer, Integer> getPostUserId(int Id) {
        HashMap<Integer, Integer> res = new HashMap<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id,IdSender From sos.Post where IdAct=?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res.put(resultSet.getInt(1), resultSet.getInt(2));
                }
            }
            print("PostUserId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public int getPostUserId1R(int Id) {
        int res = 0;
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Id,IdSender From sos.Post where Id=?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res = resultSet.getInt(2);
                }
            }
            print("PostUserId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }

        return res;
    }

    public HashMap<Integer, String> getPostContent(int Id) {
        HashMap<Integer, String> res = new HashMap<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id,content From sos.Post where IdAct= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res.put(resultSet.getInt(1), resultSet.getString(2));
                }
            }
            print("PostContent under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public String getPostContent1R(int Id) {
        String res = "";
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Id,content From sos.Post where Id= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res = resultSet.getString(2);
                }
            }
            print("PostContent under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public HashMap<Integer, Timestamp> getPostSendTime(int Id) {
        HashMap<Integer, Timestamp> res = new HashMap<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id, SendTime From sos.Post where IdAct= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res.put(resultSet.getInt(1), resultSet.getTimestamp(2));
                }
            }
            print("PostSendTime under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public Timestamp getPostSendTime1R(int Id) {
        Timestamp res = null;
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Id, SendTime From sos.Post where IdAct= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res = resultSet.getTimestamp(2);
                }
            }
            print("PostSendTime under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public boolean createPost(int UserId, String content) {
        try {
            checkConnection();
            int NewId = getLastId();
            preparedStatement = connect.prepareStatement("INSERT INTO sos.post VALUES ( ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, NewId);
            preparedStatement.setInt(2, IdActivity);
            preparedStatement.setString(3, content);
            preparedStatement.setInt(4, UserId);
            Date date = new Date();
            preparedStatement.setTimestamp(5, new Timestamp(date.getTime()));
            boolean rs = false;
            try {
                rs = preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            print("createPost " + String.valueOf(rs));
            return true;
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return false;
    }

    public void deletePost(int Id) {
        try {
            checkConnection();
            System.out.println("Delete Post  "+Id);
            preparedStatement = connect.prepareStatement("DELETE from sos.Replay where IdParent=?");
            preparedStatement.setInt(1, Id);
            boolean rs = false;
            try {
                rs = preparedStatement.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            

            print("delete Reply uder this post " + String.valueOf(rs));
            preparedStatement = connect.prepareStatement("DELETE from sos.Post where Id=?");
            preparedStatement.setInt(1, Id);
            try {
                rs = preparedStatement.execute();
            } catch (Exception e) {
                System.out.println("Delete Post");
                e.printStackTrace();
            }
            
            print("delete Post " + String.valueOf(rs));
        } catch (Exception ex) {
            printEx(ex.toString());
        }
    }

    public static void main(String args[]) {
        try {
            MySQLAccessPost mysql = new MySQLAccessPost(1);
            mysql.Connect();
//            System.out.println( mysql.getLastId());
//            mysql.createPost(5,"Not Hello World!");
            //mysql.deletePost(1);
//            System.out.println(mysql.getPostContent(1) );
//            System.out.println(mysql.getPostId(1) );
//            System.out.println(mysql.getPostSendTime(1) );
//            System.out.println(mysql.getPostUserId(1) );
            System.out.println(mysql.getPostId1R(1));
            System.out.println(mysql.getPostContent1R(1));
            System.out.println(mysql.getPostUserId1R(1));
            mysql.Close();
        } catch (Exception ex) {
            Log.out("Post :", ex);
        }
    }
}
