package com.dashwood.neweducation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dashwood.neweducation.databinding.ActivityMainBinding;
import com.dashwood.neweducation.db.DashwoodAppDatabase;
import com.dashwood.neweducation.db.Entery;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private DashwoodAppDatabase dashwoodAppDatabase;
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        dashwoodAppDatabase =
                Room.databaseBuilder(getApplicationContext(), DashwoodAppDatabase.class, "dashwood_db").allowMainThreadQueries().build();
        handler = new MainActivityClickHandler(getApplicationContext());
        activityMainBinding.setClick(handler);
        insertSomeData();
        readAllData();
        activityMainBinding.setEntery(readAllData());
    }

    private void insertSomeData() {
        dashwoodAppDatabase.getDashwoodDAO().addUser(new Entery("Dash", "Mamad", 0));
    }

    private Entery readAllData() {
        for (Entery entery : dashwoodAppDatabase.getDashwoodDAO().enteries()) {
            return entery;
        }
        return null;
    }

    public class MainActivityClickHandler {
        private Context context;

        MainActivityClickHandler(Context context) {
            this.context = context;
        }

        public void onClick(View view) {
            Log.i("LOG", "CLICK");
        }
    }
}