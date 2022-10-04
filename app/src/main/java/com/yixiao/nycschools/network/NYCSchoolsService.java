package com.yixiao.nycschools.network;

import com.yixiao.nycschools.network.model.SatResult;
import com.yixiao.nycschools.network.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NYCSchoolsService {
    @GET("s3k6-pzi2.json")
    Call<List<School>> getSchools(@Query("$offset") int offset, @Query("$limit") int limit);

    @GET("f9bf-2cp4.json")
    Call<List<SatResult>> getSATResults(@Query("dbn") String dbn);

    static NYCSchoolsService create() {
        final String BASE_URL = "https://data.cityofnewyork.us/resource/";

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NYCSchoolsService.class);
    }
}
