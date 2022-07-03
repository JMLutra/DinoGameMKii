package de.jmlutra.dinogamemkii.util;
import java.sql.*;

public class Database {
    //Database Configs
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://server8.febas.net:3306/DinoGame";

    static final String USER = "DinoGame";
    static final String PASS = "DinoGame1";
    //Database Objects
    static Connection conn = null;
    static Statement statement = null;
    static boolean connReady = false;

    public Database()
    {
        connect();
    }

    public boolean connect()
    {
        boolean h = false;
        try
        {
            System.out.println("Connecting to the Database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to the Database");

            connReady = true;
            h = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            connReady = false;
        } catch (Exception e) {
            e.printStackTrace();
            connReady = false;
        } finally {
            System.out.println("Connection ready");
            return h;
        }
    }

    public boolean disconnect()
    {
        connReady = false;
        return true;
    }

    public boolean getScores()
    {
        return true;
    }

    public boolean pushScores()
    {
        return true;
    }
}
