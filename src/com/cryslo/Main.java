package com.cryslo;

public class Main
{
    public static void main(String[] args)
    {
        Cli.write("hello world");

        Mysql mysql =  new Mysql();
        mysql.query("SELECT * FROM cry_product");
    }
}
