package com.hcdc.navi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Directions extends AppCompatActivity {
    private String savedUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_directions);
        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String currentUserId = prefs.getString("current_user_id", "Unknown");
        savedUserId= currentUserId;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.historyLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        for (int i = 1; i <= 5; i++) {
            int buttonId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            ImageButton button = findViewById(buttonId);
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            String action = button.getContentDescription().toString() + " " + currentDate;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Save action to history
                    HistoryManager.getInstance(Directions.this).addHistory(savedUserId, action);

                    if (button==findViewById(R.id.btn1)) {
                        Intent intent = new Intent(Directions.this, SmartLab.class);
                        intent.putExtra("userId", savedUserId);
                        startActivity(intent);
                    } else if (button==findViewById(R.id.btn2)) {
                        Intent intent = new Intent(Directions.this, AdmissionOffice.class);
                        intent.putExtra("userId", savedUserId);
                        startActivity(intent);
                    } else if (button==findViewById(R.id.btn3)) {
                        Intent intent = new Intent(Directions.this, OSA.class);
                        intent.putExtra("userId", savedUserId);
                        startActivity(intent);
                    } else if (button==findViewById(R.id.btn4)) {
                        Intent intent = new Intent(Directions.this, Restroom.class);
                        intent.putExtra("userId", savedUserId);
                        startActivity(intent);
                    } else if (button==findViewById(R.id.btn5)) {
                        Intent intent = new Intent(Directions.this, Gym.class);
                        intent.putExtra("userId", savedUserId);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}