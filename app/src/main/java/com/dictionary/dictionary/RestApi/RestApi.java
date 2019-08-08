package com.dictionary.dictionary.RestApi;
import com.dictionary.dictionary.models.DictBaseObject;
import com.dictionary.dictionary.models.TranslateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    @GET("/gapi/translate")
    Call<TranslateResponse>bilgiGetir(@Query("from") String from, @Query("dest") String dest, @Query("format") String format, @Query("phrase") String phrase);

}
