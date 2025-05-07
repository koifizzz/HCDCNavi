package com.hcdc.navi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class Register extends AppCompatActivity {
    EditText editTextNewUserID;
    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNewUserID = findViewById(R.id.txtRegister);
        buttonRegister = findViewById(R.id.btnRegisterGo);

        buttonRegister.setOnClickListener(v -> {
            String newUserId = editTextNewUserID.getText().toString().trim();

            if (newUserId.isEmpty()) {
                editTextNewUserID.setError("User ID is required");
                return;
            }
            if (newUserId.length()<8) {
                editTextNewUserID.setError("User ID must be at least 8 characters");
                return;
            }
            if (!newUserId.startsWith("598")) {
                editTextNewUserID.setError("Invalid User ID");
                return;
            }
            SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
            Set<String> userIds = prefs.getStringSet("saved_user_ids", new HashSet<>());

            if (userIds.contains(newUserId)) {
                editTextNewUserID.setError("User ID already exists");
                return;
            } else userIds = new HashSet<>(userIds); // Create modifiable copy
            userIds.add(newUserId);
            prefs.edit().putStringSet("saved_user_ids", userIds).apply();

            Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Register.this, LoginAsStudent.class));
            finish();
        });
    }
}