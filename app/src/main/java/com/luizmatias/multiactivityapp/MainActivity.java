package com.luizmatias.multiactivityapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextFirstRating, editTextSecondRating, editTextFrequency;
    private Button buttonProceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();

        buttonProceed.setOnClickListener(view -> {
            if (allFieldsAreValid()) {
                navigateToResults();
            }
        });

    }

    private boolean allFieldsAreValid() {

        if (editTextName.getText().toString().isEmpty()) {
            editTextName.setError(getString(R.string.invalid_name));
            return false;
        }

        try {
            float firstRating = Float.parseFloat(editTextFirstRating.getText().toString());

            if (!(firstRating >= 0 && firstRating <= 10)) {
                editTextFirstRating.setError(getString(R.string.invalid_rating));
                return false;
            }
        } catch (Exception e) {
            editTextFirstRating.setError(getString(R.string.invalid_rating));
            return false;
        }

        try {
            float secondRating = Float.parseFloat(editTextSecondRating.getText().toString());

            if (!(secondRating >= 0 && secondRating <= 10)) {
                editTextSecondRating.setError(getString(R.string.invalid_rating));
                return false;
            }
        } catch (Exception e) {
            editTextSecondRating.setError(getString(R.string.invalid_rating));
            return false;
        }

        try {
            int frequency = Integer.parseInt(editTextFrequency.getText().toString());

            if (!(frequency >= 0 && frequency <= 100)) {
                editTextFrequency.setError(getString(R.string.invalid_frequency));
                return false;
            }
        } catch (Exception e) {
            editTextFrequency.setError(getString(R.string.invalid_frequency));
            return false;
        }

        return true;
    }

    private void navigateToResults() {
        Bundle params = new Bundle();

        String name = editTextName.getText().toString();
        float firstRating = Float.parseFloat(editTextFirstRating.getText().toString());
        float secondRating = Float.parseFloat(editTextSecondRating.getText().toString());
        int frequency = Integer.parseInt(editTextFrequency.getText().toString());

        params.putString("name", name);
        params.putFloat("first_rating", firstRating);
        params.putFloat("second_rating", secondRating);
        params.putInt("frequency", frequency);

        startActivity(new Intent(this, ResultActivity.class).putExtras(params));
    }

    private void bind() {
        editTextName = findViewById(R.id.editTextName);
        editTextFirstRating = findViewById(R.id.editTextFirstRating);
        editTextSecondRating = findViewById(R.id.editTextSecondRating);
        editTextFrequency = findViewById(R.id.editTextFrequency);
        buttonProceed = findViewById(R.id.buttonProceed);
    }
}