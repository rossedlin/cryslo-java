package com.cryslo;

import com.cryslo.objects.Row;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Cli.write("hello world");

        Mysql mysql =  new Mysql("localhost", "root", "Passw0rd", "test");
        List rows = mysql.query("SELECT * FROM test_table");

        Cli.write(rows.size());

        for (int i=0; i < rows.size(); i++)
        {
//            Row row = (Row) rows.get(i);
//            Cli.write(row.getTotalColumns());
//            Cli.write(row.get("id"));
//            Cli.write(row.get("name"));
//            Cli.write(row.get(1));
        }
    }
}