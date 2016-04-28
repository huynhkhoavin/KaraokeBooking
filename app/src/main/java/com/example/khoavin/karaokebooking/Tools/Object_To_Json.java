package com.example.khoavin.karaokebooking.Tools;

import com.google.gson.Gson;

public class Object_To_Json{
    public static String convertToJson(Object obj)
    {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
    public Object convertFromJson(String jsonString)
    {
        Gson gson = new Gson();
        Object obj = gson.fromJson(jsonString,Object.class);
        return obj;
    }
}
