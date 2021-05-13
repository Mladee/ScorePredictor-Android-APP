package com.hfad.fcbarcelonaplayerdatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MatchUp extends AppCompatActivity {

    TextView UserScore,OpponentScore,bar,Match_Result;
    Button btn_PredictAgain, btn_MatchHistory;
    EditText et_UserTeamName,et_OpponentTeamName;

    DataBaseHelper Messi;
    Random rand_score_generator;

    MatchHistoryMODEL model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_up);


        UserScore = findViewById(R.id.tv_ScoreUser);
        OpponentScore = findViewById(R.id.tv_ScoreOpponent);
        bar = findViewById(R.id.tv_bar);
        Match_Result = findViewById(R.id.tv_Outcome);
        btn_PredictAgain = findViewById(R.id.btn_PredictAgain);
        btn_MatchHistory = findViewById(R.id.btn_MatchHistory);
        et_UserTeamName = findViewById(R.id.et_YourTeamName);
        et_OpponentTeamName = findViewById(R.id.et_OpponentTeamName);

        Messi = new DataBaseHelper((MatchUp.this));
        float user_team_rating = Messi.TeamRating();
        int final_user_team_rating = (int)(user_team_rating*20);
        Intent intent = getIntent();
        float opponent_Rating = intent.getFloatExtra(Formations.PackageName,0);
        int final_opponent_team_rating = (int)(opponent_Rating);
        rand_score_generator = new Random();
        if(final_user_team_rating>final_opponent_team_rating) {
            int x = final_user_team_rating - final_opponent_team_rating;

            int score_user_team = rand_score_generator.nextInt(35 + x / 3) / 10;
            int score_opponent_team = rand_score_generator.nextInt(35 - x / 3) / 10;
            UserScore.setText("" + score_user_team);
            OpponentScore.setText("" + score_opponent_team);
        }
        else
        {
            int x = final_opponent_team_rating - final_user_team_rating;

            int score_user_team = rand_score_generator.nextInt(35 - x / 3) / 10;
            int score_opponent_team = rand_score_generator.nextInt(35 + x/3) / 10;
            UserScore.setText("" + score_user_team);
            OpponentScore.setText("" + score_opponent_team);
        }



        int a = Integer.parseInt((String) UserScore.getText());
        int b = Integer.parseInt((String) OpponentScore.getText());

        if(a>b)
        {
            Match_Result.setText("VICTORY");
            Match_Result.setTextColor(Color.parseColor("#FFFF00"));
        }
        else if (a==b)
        {
            Match_Result.setText("DRAW");
            Match_Result.setTextColor(Color.parseColor("#3CB371"));
        }
        else{
            Match_Result.setText("DEFEAT");
            Match_Result.setTextColor(Color.parseColor("#191970"));
        }

        model = new MatchHistoryMODEL(-1,et_UserTeamName.getText().toString(),et_OpponentTeamName.getText().toString(),Integer.parseInt(UserScore.getText().toString()),Integer.parseInt(OpponentScore.getText().toString()),Match_Result.getText().toString());
        MatchHistoryDATABASE history_object = new MatchHistoryDATABASE(MatchUp.this);



        btn_PredictAgain.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(TextUtils.isEmpty(et_UserTeamName.getText().toString()) || TextUtils.isEmpty(et_OpponentTeamName.getText().toString()) ) {
                Toast.makeText(MatchUp.this,"Please fill in the team names.",Toast.LENGTH_SHORT).show();
            }
            else
            {
                model = new MatchHistoryMODEL(-1, et_UserTeamName.getText().toString(), et_OpponentTeamName.getText().toString(), Integer.parseInt(UserScore.getText().toString()), Integer.parseInt(OpponentScore.getText().toString()), Match_Result.getText().toString());
                MatchHistoryDATABASE history_object = new MatchHistoryDATABASE(MatchUp.this);


                boolean success = history_object.addOne(model);


                Intent intent = new Intent(MatchUp.this, MainActivity.class);
                startActivity(intent);

            }
        }
    });
        btn_MatchHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(et_UserTeamName.getText().toString()) || TextUtils.isEmpty(et_OpponentTeamName.getText().toString()) ) {
                    Toast.makeText(MatchUp.this,"To save this game into the match history,you need to provide the team names.",Toast.LENGTH_SHORT).show();
                }
                else {


                    model = new MatchHistoryMODEL(-1, et_UserTeamName.getText().toString(), et_OpponentTeamName.getText().toString(), Integer.parseInt(UserScore.getText().toString()), Integer.parseInt(OpponentScore.getText().toString()), Match_Result.getText().toString());
                    MatchHistoryDATABASE history_object = new MatchHistoryDATABASE(MatchUp.this);
                    boolean success = history_object.addOne(model);
                    Intent intent = new Intent(MatchUp.this, MatchHistory.class);
                    startActivity(intent);
                }
            }
        });


    }

    public void onBackPressed() {}
}