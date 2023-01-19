package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, gender_input, lang_input, ide_input;
    Button update_button, delete_button;

    String id, name, gender, lang, ide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        gender_input = findViewById(R.id.gender_input2);
        lang_input = findViewById(R.id.lang_input2);
        ide_input = findViewById(R.id.ide_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                gender = gender_input.getText().toString().trim();
                lang = lang_input.getText().toString().trim();
                ide = ide_input.getText().toString().trim();
                myDB.updateData(id, name, gender, lang, ide);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("gender") &&
                getIntent().hasExtra("lang") && getIntent().hasExtra("ide")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            gender = getIntent().getStringExtra("gender");
            lang = getIntent().getStringExtra("lang");
            ide = getIntent().getStringExtra("ide");

            //Setting Intent Data
            name_input.setText(name);
            gender_input.setText(gender);
            lang_input.setText(lang);
            ide_input.setText(ide);
            Log.d("stev", name+" "+gender+" "+lang+" "+ide);
        }else{
            Toast.makeText(this, "Нет данных.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить " + name + " ?");
        builder.setMessage("Вы точно хотите удалить " + name + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
