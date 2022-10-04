package com.yixiao.nycschools.network;

import androidx.lifecycle.MutableLiveData;

import com.yixiao.nycschools.network.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolDirectoryRepository {

    private static final int SIZE = 30;

    private static SchoolDirectoryRepository mRepository;
    private NYCSchoolsService mService;

    public SchoolDirectoryRepository() {
        mService = NYCSchoolsService.create();
    }

    public static SchoolDirectoryRepository getInstance() {
        if (mRepository == null) {
            mRepository = new SchoolDirectoryRepository();
        }
        return mRepository;
    }

    public MutableLiveData<List<School>> requestSchoolDirectory(int offset) {
        MutableLiveData<List<School>> data = new MutableLiveData<>();

        mService.getSchools(offset, SIZE).enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(Call<List<School>> call, Response<List<School>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<School>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

}
