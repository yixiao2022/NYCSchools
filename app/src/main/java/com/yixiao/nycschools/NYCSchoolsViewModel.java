package com.yixiao.nycschools;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yixiao.nycschools.network.SatResultsRepository;
import com.yixiao.nycschools.network.SchoolDirectoryRepository;
import com.yixiao.nycschools.network.model.SatResult;
import com.yixiao.nycschools.network.model.School;

import java.util.List;

public class NYCSchoolsViewModel extends ViewModel {
    private SchoolDirectoryRepository mSchoolDirectoryRepository;
    private SatResultsRepository mSatResultsRepository;

    public void init() {
        if (mSchoolDirectoryRepository == null) {
            mSchoolDirectoryRepository = SchoolDirectoryRepository.getInstance();
        }

        if (mSatResultsRepository == null) {
            mSatResultsRepository = SatResultsRepository.getInstance();
        }
    }

    public LiveData<List<School>> getSchoolDirectory(int offset) {
        return mSchoolDirectoryRepository.requestSchoolDirectory(offset);
    }

    public LiveData<List<SatResult>> getSATResults(String dbn) {
        return mSatResultsRepository.requestSATResults(dbn);
    }
}
