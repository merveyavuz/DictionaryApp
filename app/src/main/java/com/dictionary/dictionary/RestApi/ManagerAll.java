package com.dictionary.dictionary.RestApi;

import com.dictionary.dictionary.models.DictBaseObject;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {
    private static ManagerAll ourInstance=new ManagerAll();

    public static synchronized ManagerAll getInstance(){
        return ourInstance;
    }

    public Call<List<DictBaseObject>> getirBilgileri(){
        //Call<List<DictBaseObject>> call= getRestApiClient().bilgiGetir();
        return null;
    }
}
