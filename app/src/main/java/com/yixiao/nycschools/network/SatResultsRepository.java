package com.yixiao.nycschools.network;

import androidx.lifecycle.MutableLiveData;

import com.yixiao.nycschools.network.model.SatResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SatResultsRepository {
    private static SatResultsRepository mRepository;
    private NYCSchoolsService mService;

    public SatResultsRepository() {
        mService = NYCSchoolsService.create();
    }

    public static SatResultsRepository getInstance() {
        if (mRepository == null) {
            mRepository = new SatResultsRepository();
        }
        return mRepository;
    }

    public MutableLiveData<List<SatResult>> requestSATResults(String dbn) {
        MutableLiveData<List<SatResult>> data = new MutableLiveData<>();

        mService.getSATResults(dbn).enqueue(new Callback<List<SatResult>>() {
            @Override
            public void onResponse(Call<List<SatResult>> call, Response<List<SatResult>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<SatResult>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
