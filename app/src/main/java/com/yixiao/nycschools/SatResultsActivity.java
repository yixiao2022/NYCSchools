package com.yixiao.nycschools;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.yixiao.nycschools.network.model.SatResult;

public class SatResultsActivity extends AppCompatActivity {
    NYCSchoolsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sat_results);

        String dbn = getIntent().getStringExtra("dbn");
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");

        ((TextView)findViewById(R.id.school_name)).setText(name);
        ((TextView)findViewById(R.id.school_description)).setText(description);

        mViewModel = new ViewModelProvider(this).get(NYCSchoolsViewModel.class);
        mViewModel.init();
        mViewModel.getSATResults(dbn).observe(SatResultsActivity.this, satResults -> {
            if (!satResults.isEmpty()) {
                SatResult satResult = satResults.get(0);
                ((TextView)findViewById(R.id.num_of_sat_test_takers)).setText(String.valueOf(satResult.numOfSatTestTakers));
                ((TextView)findViewById(R.id.sat_critical_reading_avg_score)).setText(String.valueOf(satResult.satCriticalReadingAvgScore));
                ((TextView)findViewById(R.id.sat_math_avg_score)).setText(String.valueOf(satResult.satMathAvgScore));
                ((TextView)findViewById(R.id.sat_writing_avg_score)).setText(String.valueOf(satResult.satWritingAvgScore));
            } else {
                ((TextView)findViewById(R.id.num_of_sat_test_takers)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.sat_critical_reading_avg_score)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.sat_math_avg_score)).setText(R.string.unknown);
                ((TextView)findViewById(R.id.sat_writing_avg_score)).setText(R.string.unknown);
            }
        });
    }

}
