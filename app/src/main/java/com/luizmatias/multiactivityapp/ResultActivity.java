package com.luizmatias.multiactivityapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    TextView textViewInfo, textViewResult, textViewAverageRating, textViewFrequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bind();
        fillFields();
    }

    private void fillFields() {
        Bundle params = getIntent().getExtras();

        if (params != null) {
            textViewInfo.setText(String.format(Locale.getDefault(), getString(R.string.you_are), params.getString("name")));

            float averageRating = (params.getFloat("first_rating") + params.getFloat("second_rating")) / 2;
            int frequency = params.getInt("frequency");

            if (averageRating >= 7 && frequency >= 75) {
                textViewResult.setText(getString(R.string.approved));
            }

            if (averageRating >= 4 && averageRating < 7 && frequency >= 75) {
                textViewResult.setText(getString(R.string.final_exam));
            }

            if (averageRating < 4) {
                textViewResult.setText(getString(R.string.disapproved_by_rating));
            }

            if (frequency < 75) {
                textViewResult.setText(getString(R.string.disapproved_by_frequency));
            }

            textViewAverageRating.setText(String.format(Locale.getDefault(), getString(R.string.your_average_rating_was), averageRating));
            textViewFrequency.setText(String.format(Locale.getDefault(), getString(R.string.your_frequency_was), frequency));
        }
    }

    private void bind() {
        textViewInfo = findViewById(R.id.textViewInfo);
        textViewResult = findViewById(R.id.textViewResult);
        textViewAverageRating = findViewById(R.id.textViewAverageRating);
        textViewFrequency = findViewById(R.id.textViewFrequency);
    }
}