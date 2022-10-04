package com.yixiao.nycschools;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yixiao.nycschools.network.model.School;
import com.yixiao.nycschools.ui.SchoolDirectoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    NYCSchoolsViewModel mNYCSchoolsViewModel;
    SchoolDirectoryAdapter mAdapter;
    ArrayList<School> mSchools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.school_list);
        mNYCSchoolsViewModel = new ViewModelProvider(this).get(NYCSchoolsViewModel.class);
        mNYCSchoolsViewModel.init();
        mSchools = new ArrayList<>();

        initRecyclerView();
    }

    private void initRecyclerView() {
        if (mAdapter != null) {
            return;
        }

        requestData();
        mAdapter = new SchoolDirectoryAdapter(mSchools, school -> {
            Intent intent = new Intent(MainActivity.this, SatResultsActivity.class);
            intent.putExtra("dbn", school.dbn);
            intent.putExtra("name", school.schoolName);
            intent.putExtra("description", school.overviewParagraph);
            startActivity(intent);
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    requestData();
                }
            }
        });

    }

    private void requestData() {
        mNYCSchoolsViewModel.getSchoolDirectory(mSchools.size()).observe(this, schools -> {
            if (!schools.isEmpty()) {
                mSchools.addAll(schools);
                mAdapter.notifyDataSetChanged();
            } else {
                ((TextView)findViewById(R.id.error_message)).setVisibility(View.VISIBLE);
            }
        });
    }
}