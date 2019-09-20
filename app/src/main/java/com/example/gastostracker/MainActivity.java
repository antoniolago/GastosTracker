package com.example.gastostracker;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

        @Overrides
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

/*teste

 */