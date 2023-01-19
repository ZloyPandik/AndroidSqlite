package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input, gender_input, lang_input, ide_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        gender_input = findViewById(R.id.gender_input);
        lang_input = findViewById(R.id.lang_input);
        ide_input = findViewById(R.id.ide_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(name_input.getText().toString().trim(),
                        gender_input.getText().toString().trim(),
                        lang_input.getText().toString().trim(),
                        ide_input.getText().toString().trim());
            }
        });
    }
}
