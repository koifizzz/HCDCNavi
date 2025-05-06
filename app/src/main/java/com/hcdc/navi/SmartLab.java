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

public class SmartLab extends AppCompatActivity {
    TextView details;
    int currentImageIndex = 0;
    int[]images={
            R.drawable.a,
            R.drawable.c,
            R.drawable.d
    };
    String[]detailsText={
            "Take the left side elevator from the main entrance to 5th floor",
            "Upon exiting the elevator, turn left",
            "Walk straight ahead until you reach the Smart Lab Room"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_lab);

        ImageView imageView = findViewById(R.id.smartLabImage);
        Button changePhotoButton = findViewById(R.id.btnNext);
        details = findViewById(R.id.txtDetails);

        imageView.setImageResource(images[currentImageIndex]);
        details.setText(detailsText[currentImageIndex]);

        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex++;
                if (currentImageIndex < 3) {
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