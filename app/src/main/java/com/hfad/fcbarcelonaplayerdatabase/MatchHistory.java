package com.hfad.fcbarcelonaplayerdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MatchHistory extends AppCompatActivity {
    
   ListView History_List;
    TextView text;
    Button btn_Predict;


    MatchHistoryDATABASE database_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);
        
        
        text = findViewById(R.id.tv_History);
        History_List = findViewById(R.id.lv_History);
        btn_Predict = findViewById(R.id.btn_PredictAgain);



        database_object = new MatchHistoryDATABASE(MatchHistory.this);
        List<MatchHistoryMODEL> everyone = database_object.getAll();

        ArrayAdapter array_List = new ArrayAdapter<MatchHistoryMODEL>(MatchHistory.this,android.R.layout.simple_list_item_1,everyone);

        History_List.setAdapter(array_List);


        btn_Predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MatchHistory.this,MainActivity.class);
                startActivity(intent);
            }
        });


        
        
        
    }
    public void onBackPressed() {}
}