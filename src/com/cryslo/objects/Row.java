package com.cryslo.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ross on 02/01/2016.
 */
public class Row
{
    int i = 0;
    Map<String, Integer> indexMap = new HashMap<String, Integer>();
    List row = new ArrayList();

    /**
     *
     * @param index
     * @param value
     */
    public void add(String index, String value)
    {
        this.indexMap.put(index , i);
        this.row.add(i, value);
        this.i++;
    }

    /**
     *
     * @param index
     * @return
     */
    public String get(int index)
    {
        return this.row.get(index).toString();
    }

    /**
     *
     * @param index
     * @return
     */
    public String get(String index)
    {
        int i = indexMap.get(index);
        return row.get(i).toString();
    }
}
