package com.jevents;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;

/**
 * @auther: Dante
 */
public class MySQLAccessReply extends MySQLAccess {

    private int IdParent = 0;

    public MySQLAccessReply(int IdPar) {
        IdParent = IdPar;
    }

    public int getLastId() {
        int MaxId = 0;
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT MAX(Id) as MaxId FROM sos.Replay ");
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

    public boolean checkReplayId(int Id) {
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT *  FROM sos.Replay WHERE Id= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt("Id") == Id) {
                    print("replyID contains " + resultSet.getInt(1));
                    return true;
                } else {
                    return false;
                }
            }
            print("replyID doesn't contain " + Id);
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return false;
    }

    public ArrayList<Integer> getReplayId(int Id) {
        ArrayList<Integer> resId = new ArrayList<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id  FROM sos.Replay WHERE IdParent= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    resId.add(resultSet.getInt(1));
                }
            }
            print("ReplyId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return resId;
    }

    public HashMap<Integer, Integer> getReplayUserId(int Id) {
        HashMap<Integer, Integer> res = new HashMap<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id, IdSender  FROM sos.Replay WHERE IdParent= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res.put(resultSet.getInt(1), resultSet.getInt(2));
                }
            }
            print("ReplyUserId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public int getReplayUserId1R(int Id) {
        int res = 0;
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Id, IdSender  FROM sos.Replay WHERE Id= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res = resultSet.getInt(2);
                }
            }
            print("ReplyUserId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public HashMap<Integer, Integer> getReplayReplyId(int Id) {
        HashMap<Integer, Integer> res = new HashMap<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id,IdReply  FROM sos.Replay WHERE IdParent= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res.put(resultSet.getInt(1), resultSet.getInt(2));
                }
            }
            print("ReplayReplyId under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public HashMap<Integer, String> getReplayContent(int Id) {
        HashMap<Integer, String> res = new HashMap<>();
        try {
            checkConnection();
            //int NewId=0;
            preparedStatement = connect.prepareStatement("SELECT Id,content  FROM sos.Replay WHERE IdParent= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res.put(resultSet.getInt(1), resultSet.getString(2));
                }
            }
            print("ReplayContent under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }

        return res;
    }

    public HashMap<Integer, Timestamp> getReplaySendTime(int Id) {
        HashMap<Integer, Timestamp> res = new HashMap<>();
        try {
            checkConnection();
            preparedStatement = connect.prepareStatement("SELECT Id, SendTime  FROM sos.Replay WHERE IdParent= ?");
            preparedStatement.setInt(1, Id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!resultSet.wasNull()) {
                    res.put(resultSet.getInt(1), resultSet.getTimestamp(2));
                }
            }
            print("ReplaySendTime under " + Id + " get!");
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return res;
    }

    public boolean createReply(int userId, String content, int replyId) {
        try {
            checkConnection();
            boolean checkFlag = checkReplayId(replyId);
            if (replyId != -1 && !checkFlag) {
                //print("");
                return false;
            }
            int NewId = getLastId();
            preparedStatement = connect.prepareStatement("INSERT INTO sos.Replay VALUES ( ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, NewId);
            preparedStatement.setInt(2, IdParent);
            preparedStatement.setString(4, content);
            preparedStatement.setInt(3, replyId);
            preparedStatement.setInt(5, userId);
            Date date = new Date();
            preparedStatement.setTimestamp(6, new Timestamp(date.getTime()));
            boolean rs = preparedStatement.execute();
            print("createPost " + String.valueOf(rs));
            return true;
        } catch (Exception ex) {
            printEx(ex.toString());
        }
        return false;
    }

    public void deleteReply(int Id) {
        try {
            checkConnection();
            int NewId = getLastId();
            preparedStatement = connect.prepareStatement("DELETE from sos.Replay where Id=?");
            preparedStatement.setInt(1, Id);
            boolean rs = preparedStatement.execute();
            print("delete Post " + String.valueOf(rs));
        } catch (Exception ex) {
            printEx(ex.toString());
        }
    }

    public static void main(String args[]) {
        try {
            MySQLAccessReply mysql = new MySQLAccessReply(1);
            mysql.Connect();
            //mysql.checkReplayId(1);
            //mysql.checkReplayId(2);
            //System.out.println( mysql.getLastId());
            mysql.createReply(5, "What!", -1);
            mysql.createReply(5, "Hello World!", 3);
            //mysql.deleteReply(2);
            //System.out.println(mysql.getReplayUserId(2) );
            //System.out.println(mysql.getReplayId(2) );
//            System.out.println(mysql.getReplayContent(1) );
//            System.out.println(mysql.getReplaySendTime(1) );
            mysql.Close();
        } catch (Exception ex) {
            Log.out("Post :", ex);
        }
    }
}
