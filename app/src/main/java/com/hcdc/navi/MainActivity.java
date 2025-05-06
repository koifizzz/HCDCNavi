package com.hcdc.navi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button move,buttonLogout;
    TextView textWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainact);

        buttonLogout = findViewById(R.id.btnLogout);
        textWelcome = findViewById(R.id.txtWelcome);

        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String currentUserId = prefs.getString("current_user_id", "Unknown");

        textWelcome.setText("Welcome, " + currentUserId + "!");

        buttonLogout.setOnClickListener(v -> {
            prefs.edit().remove("current_user_id").apply();
            startActivity(new Intent(MainActivity.this, LoginPage.class));
            finish();
        });
        move=findViewById(R.id.btnAbout);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAlertDialog();
            }
        });
        move=findViewById(R.id.btnDirections);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Directions.class);
                startActivity(intent);
            }
        });
        move=findViewById(R.id.btnHistory);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                String currentUserId = prefs.getString("current_user_id", null);
                intent.putExtra("userId", currentUserId);
                startActivity(intent);
            }
        });
    }
    private void showAlertDialog(){
        View view= LayoutInflater.from(this).inflate(R.layout.about_dialog,null,false);
        AlertDialog alertDialog=new AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();
        Button btnClose=view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}