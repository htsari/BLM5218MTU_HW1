package com.practice.mypractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Onclick();

    }

    private void init(){
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.login);
    }

    private void Onclick() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().isEmpty()) {
                    userName.setError("Please Enter UserName");
                }else if (password.getText().toString().isEmpty()) {
                    password.setError("Please Enter Password");
                }else if (!userName.getText().toString().equals("admin") || !password.getText().toString().equals("1234")) {
                    showAlert();
                }else {
                    finish();
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                }
            }
        });
    }

    private void showAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Please Enter UserName and Password")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
