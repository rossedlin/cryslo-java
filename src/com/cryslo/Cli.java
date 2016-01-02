package com.cryslo;

/**
 * Created by Ross Edlin on 02/01/2016.
 */
public class Cli
{
    public static void write(int num)
    {
        write(Integer.toString(num));
    }

    public static void write(String msg)
    {
        System.out.println(msg);
    }
}
