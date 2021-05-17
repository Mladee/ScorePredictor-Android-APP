package com.hfad.fcbarcelonaplayerdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    Button btn_Start, btn_MH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btn_Start = findViewById(R.id.btn_Start);
        btn_MH = findViewById(R.id.btn_MH);


        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);
            }


        });

        btn_MH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Menu.this, MatchHistory.class);
                startActivity(intent);
            }


        });

    }
    public void onBackPressed () {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}