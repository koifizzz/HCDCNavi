package com.hcdc.navi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;
import java.util.Set;

public class LoginAsStudent extends AppCompatActivity {
    EditText editTextUserID;
    Button buttonLogin, buttonToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_student);

        editTextUserID = findViewById(R.id.txtLogin);
        buttonLogin = findViewById(R.id.btnGo);
        buttonToRegister = findViewById(R.id.btnRegister);

        buttonLogin.setOnClickListener(v -> {
            String enteredId = editTextUserID.getText().toString().trim();
            SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
            Set<String> userIds = prefs.getStringSet("saved_user_ids", new HashSet<>());

            if (userIds.contains(enteredId)) {
                // Save current session (optional)
                prefs.edit().putString("current_user_id", enteredId).apply();
                startActivity(new Intent(LoginAsStudent.this, MainActivity.class));
                finish();
            } else {
                editTextUserID.setError("ID not found");
                return;
            }
        });

        buttonToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginAsStudent.this, Register.class));
        });
    }
}