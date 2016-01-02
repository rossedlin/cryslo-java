package com.cryslo;

import java.sql.*;

/**
 * Created by Ross Edlin on 02/01/2016.
 */
public class Mysql
{
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    String url = "jdbc:mysql://swc.cryslo.com:3306/cryslo_test";
    String user = "rossedlin";
    String password = "prmud982-=";

    public Mysql()
    {

    }

    /**
     * Close all DB connections
     */
    public void close()
    {
        try
        {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }
        catch (SQLException ex)
        {
            Cli.write("Ex: "+ex.getMessage());
        }
    }

    public void query(String sql)
    {
        try
        {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next())
            {
//                Cli.write(rs.get);
                Cli.write(rs.getInt("product_id"));
                Cli.write(rs.getString("name"));
            }

        }
        catch (SQLException ex)
        {
            Cli.write("Ex: "+ex.getMessage());

        }
        finally
        {
            close();
        }
    }
}
