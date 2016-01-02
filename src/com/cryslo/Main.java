package com.cryslo;

import com.cryslo.objects.Row;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Cli.write("hello world");

        Mysql mysql =  new Mysql("swc.cryslo.com", "rossedlin", "prmud982-=", "cryslo_test");
        List rows = mysql.query("SELECT * FROM cry_product");

        for (int i=0; i < rows.size(); i++)
        {
            Row row = (Row) rows.get(i);
//            Cli.write(row.getTotalColumns());
            Cli.write(row.get("product_id"));
            Cli.write(row.get("name"));
            Cli.write(row.get(1));
        }
    }
}