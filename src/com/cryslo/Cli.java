package com.cryslo;

/**
 * Created by Ross Edlin on 02/01/2016.
 */
public class Cli
{
    /**
     *
     * @param num Will print out to the system an integer
     */
    public static void write(int num)
    {
        write(Integer.toString(num));
    }

    /**
     *
     * @param str Will print out to the system a string
     */
    public static void write(String str)
    {
        System.out.println(str);
    }
}
