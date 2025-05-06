package com.hcdc.navi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Restroom extends AppCompatActivity {
    TextView details;
    int currentImageIndex = 0;
    int[]images={
            R.drawable.aaa,
            R.drawable.aab,
            R.drawable.aac,
            R.drawable.aad
    };
    String[]detailsText={
            "From the main entrance, take the left corridor",
            "Head straight down the corridor until you reach the elevator",
            "Take a left turn, then head straight",
            "Go straight until you reach the end of the corridor where the Restroom is located"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restroom);

        ImageView imageView = findViewById(R.id.smartLabImage);
        Button changePhotoButton = findViewById(R.id.btnNext);
        details = findViewById(R.id.txtDetails);

        imageView.setImageResource(images[currentImageIndex]);
        details.setText(detailsText[currentImageIndex]);

        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex++;
                if (currentImageIndex < 4) {
                    imageView.setImageResource(images[currentImageIndex]);
                    details.setText(detailsText[currentImageIndex]);
                }
                else {
                    finish();
                }
            }
        });
    }
}