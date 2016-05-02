package com.example.khoavin.karaokebooking.KaraokeObject;

import com.google.gson.Gson;

/**
 * Created by KhoaVin on 27/04/2016.
 */
interface IObject
{
    Gson gson = new Gson();
    IObject convertFromJson();
}
