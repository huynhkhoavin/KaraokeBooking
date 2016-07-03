package com.example.khoavin.karaokebooking.Tools;

import com.google.gson.Gson;

import java.util.HashMap;

public class Object_To_Json{
    static Gson gson = new Gson();
    public static String convertToJson(Object obj)
    {

        String json = gson.toJson(obj);
        return json;
    }
    public static Object convertFromJson(String jsonString)
    {
        Object obj = gson.fromJson(jsonString, Object.class);
        return obj;
    }
    public static String HashMapToJson(Object obj){
        return gson.toJson(obj);
    }
}
