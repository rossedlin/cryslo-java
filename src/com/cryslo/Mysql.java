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

    private String ip;
    private String username;
    private String password;
    private String database;

    public Mysql(String ip, String username, String password, String database)
    {
        this(ip, username, password);
        setDatabase(database);
    }

    public Mysql(String ip, String username, String password)
    {
        setIp(ip);
        setUsername(username);
        setPassword(password);
    }

    public List query(String sql)
    {
        //just check we're all good before running any SQL
        if (getIp() == null) return new ArrayList();
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
            String url = "jdbc:mysql://" + getIp() + ":3306/" + getDatabase();

            //run the SQl commands
            con = DriverManager.getConnection(url, getUsername(), getPassword());
            st = con.createStatement();
            rs = st.executeQuery(sql);

            //loop through each row
            while (rs.next())
            {
                row = new Row();

                //loop through each column and buld a Row Object
                rsmd = rs.getMetaData();
                for (int i=1; i <= rsmd.getColumnCount(); i++)
                {
                    row.add(rsmd.getColumnName(i), rs.getString(i));
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
            Cli.write("Ex: "+ex.getMessage());
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
