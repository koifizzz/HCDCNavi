package com.hcdc.navi;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HistoryManager {
    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String HISTORY_KEY_PREFIX = "history_";
    private static HistoryManager instance;
    private SharedPreferences sharedPreferences;

    private HistoryManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized HistoryManager getInstance(Context context) {
        if (instance == null) {
            instance = new HistoryManager(context.getApplicationContext());
        }
        return instance;
    }

    public void addHistory(String userId, String action) {
        if (userId == null || userId.isEmpty()) return;

        String key = HISTORY_KEY_PREFIX + userId;
        String existingHistory = sharedPreferences.getString(key, "");
//        String updatedHistory = existingHistory.isEmpty() ? action : existingHistory + "," + action;
        String[] historyArray = existingHistory.isEmpty() ? new String[0] : existingHistory.split(",");

        ArrayList<String> historyList = new ArrayList<>(List.of(historyArray));
        historyList.add(action);

        if (historyList.size() > 20) {
            historyList = new ArrayList<>(historyList.subList(historyList.size() - 20, historyList.size()));
        }

        String updatedHistory = String.join(",", historyList);
        sharedPreferences.edit().putString(key, updatedHistory).apply();
    }

    public ArrayList<String> getHistory(String userId) {
        if (userId == null || userId.isEmpty()) return new ArrayList<>();

        String key = HISTORY_KEY_PREFIX + userId;
        String serializedHistory = sharedPreferences.getString(key, "");
        if (serializedHistory == null || serializedHistory.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String[] historyArray = serializedHistory.split(",");
        return new ArrayList<>(List.of(historyArray));
    }
    public Map<String, ?> getAllHistory() {
        return sharedPreferences.getAll();
    }
}