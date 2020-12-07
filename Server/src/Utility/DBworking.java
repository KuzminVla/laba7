package Utility;

import Stuff.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DBworking {
    private static final String url = "jdbc:postgresql://pg:5432/studs";
    private static final String user = "s285645";
    private static final String password = "jek534";
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    public static Boolean ConnectionToDB() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            throw e;

        }
    }

    public static Boolean addNewUser(String user, String password) {
        try {
            preparedStatement = connection.prepareStatement("insert into userdb values (?,?)");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static Boolean userExist(String user, String password) {

        try {
            preparedStatement = connection.prepareStatement("select *  from userdb where login = ? and password= ?");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void uploadAllLabs() {
        try {
            stmt = connection.createStatement();
            stmt.execute("TRUNCATE labwork");
            Set<LabWork> labWorks = LabWorkCollection.getCollection();
            labWorks.forEach(x -> {
                try {
                    preparedStatement = connection.prepareStatement("INSERT into labwork values(?,?,?,?,?,?,?,?,?,?)");
                    preparedStatement.setLong(1, x.getId());
                    preparedStatement.setString(2, x.getName());
                    preparedStatement.setDouble(3, x.getCoordinates().getX());
                    preparedStatement.setDouble(4, x.getCoordinates().getY());
                    preparedStatement.setTimestamp(5, x.getCreationDate());
                    preparedStatement.setDouble(6, x.getMinimalPoint());
                    preparedStatement.setString(7, x.getDifficulty().toString());
                    preparedStatement.setString(8, x.getDiscipline().getName());
                    preparedStatement.setInt(9, x.getDiscipline().getSelfStudyHours());
                    preparedStatement.setString(10, x.getUser());

                    preparedStatement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadAllLabs() {
        try {
            try {
               LabWorkCollection.clear();
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select * from labwork");
            while (rs.next()) {
                LabWork labWork = new LabWork();
                labWork.setId(rs.getInt(1));
                labWork.setName(rs.getString(2));
                Coordinates coordinates = new Coordinates();
                coordinates.setX((float)rs.getDouble(3));
                coordinates.setY((float)rs.getDouble(4));
                labWork.setCoordinates(coordinates);
                labWork.setCreationDate(rs.getTimestamp(5));
                labWork.setMinimalPoint(rs.getDouble(6));
                labWork.setDifficulty(Difficulty.valueOf(rs.getString(7)));
                Discipline discipline = new Discipline();
                discipline.setName(rs.getString(8));
                discipline.setSelfStudyHours(rs.getInt(9));
                labWork.setDiscipline(discipline);
                labWork.setUser(rs.getString(10));
                LabWorkCollection.getCollection().add(labWork);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
