package com.hcdc.navi;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        String userId = getIntent().getStringExtra("userId");
        ArrayList<String> history = HistoryManager.getInstance(this).getHistory(userId);
        Collections.reverse(history);

        ListView historyListView = findViewById(R.id.historyListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, history);
        historyListView.setAdapter(adapter);
    }
}