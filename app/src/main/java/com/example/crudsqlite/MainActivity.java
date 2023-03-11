package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.crudsqlite.ui.employee.AddEmployeeActivity;
import com.example.crudsqlite.ui.employee.ListEmployeeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showForm(View view) {

        Intent intent = new Intent(getApplicationContext(), AddEmployeeActivity.class);

        startActivity(intent);

    }

    public void showList(View view) {

        Intent intent = new Intent(getApplicationContext(), ListEmployeeActivity.class);

        startActivity(intent);

    }
}