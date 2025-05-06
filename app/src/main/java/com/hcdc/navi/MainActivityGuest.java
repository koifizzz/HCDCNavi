package com.hcdc.navi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityGuest extends AppCompatActivity {
    Button move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainact_guest);

        move = findViewById(R.id.btnLogout2);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityGuest.this, LoginPage.class);
                startActivity(intent);
            }
        });
        move=findViewById(R.id.btnAbout2);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                showAlertDialog();
            }
        });
        move=findViewById(R.id.btnDirections2);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityGuest.this, DirectionsGuest.class);
                startActivity(intent);
            }
        });
        move=findViewById(R.id.btnHistory2);
        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivityGuest.this, "You need to login to access history.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showAlertDialog(){
        View view= LayoutInflater.from(this).inflate(R.layout.about_dialog,null,false);
        AlertDialog alertDialog=new AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();

        Button btnClose=alertDialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}