package com.cryslo;

import com.cryslo.objects.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ross Edlin on 02/01/2016.
 */
public class Mysql
{
    public static final String DEFAULT_IP = "localhost";
    public static final int DEFAULT_PORT = 3306;

    private String ip;
    private int port;
    private String username;
    private String password;
    private String database;

    /**
     * Full set of config
     *
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param database
     */
    public Mysql(String ip, int port, String username, String password, String database)
    {
        setIp(ip);
        setPort(port);
        setUsername(username);
        setPassword(password);
        setDatabase(database);
    }

    /**
     * Partial set of config
     *
     * @param ip
     * @param username
     * @param password
     * @param database
     */
    public Mysql(String ip, String username, String password, String database)
    {
        this(ip, DEFAULT_PORT, username, password, database);
    }

    /**
     * Partial set of config
     *
     * @param username
     * @param password
     * @param database
     */
    public Mysql(String username, String password, String database)
    {
        this(DEFAULT_IP, DEFAULT_PORT, username, password, database);
    }

    /**
     * Query the DB
     *
     * @param sql An SQL statement
     * @return
     */
    public List query(String sql)
    {
        //just check we're all good before running any SQL
        if (getIp() == null) setIp(DEFAULT_IP);
        if (getPort() <= 0) setPort(DEFAULT_PORT);
        if (getUsername() == null) return new ArrayList();
        if (getPassword() == null) return new ArrayList();
        if (getDatabase() == null) return new ArrayList();

        //init the vars
        Connection con;
        Statement st;
        ResultSet rs;
        ResultSetMetaData rsmd;

        List rows = new ArrayList();
        Row row;

        try
        {
            //build the SQL url
            String url = "jdbc:mysql://" + getIp() + ":" + getPort() + "/" + getDatabase();

            //run the SQl commands
            con = DriverManager.getConnection(url, getUsername(), getPassword());
            st = con.createStatement();
            rs = st.executeQuery(sql);

            //loop through each row
            while (rs.next())
            {
                row = new Row();

                //set some init data in row
                rsmd = rs.getMetaData();
                row.setTotalColumns(rsmd.getColumnCount());

                //loop through each column and build a Row Object
                for (int i=1; i <= rsmd.getColumnCount(); i++)
                {
                    String val = rs.getString(i);
                    if (rs.wasNull()) //gotta do it this way, because you've got to pull the value and store it in "val" before you can check if it's null
                    {
                        row.add(rsmd.getColumnName(i), "");
                        continue;
                    }

                    row.add(rsmd.getColumnName(i), val);
                }

                //add the row object to our rows list
                rows.add(row);
            }

            //gotta make sure we close all our connections
            rs.close();
            st.close();
            con.close();
        }
        catch (SQLException ex)
        {
            Cli.write("SQL Exception: " + ex.getMessage());
        }

        return rows;
    }


    //*********************************************************************************************
    //Getters & Setters
    //*********************************************************************************************


    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDatabase()
    {
        return database;
    }

    public void setDatabase(String database)
    {
        this.database = database;
    }
}
