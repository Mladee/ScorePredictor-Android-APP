package com.hfad.fcbarcelonaplayerdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.hfad.fcbarcelonaplayerdatabase.DataBaseHelper.BARCELONA_TABLE;
import static com.hfad.fcbarcelonaplayerdatabase.DataBaseHelper.COLUMN_PLAYER_RATING;

public class Formations extends AppCompatActivity {

    public static final String PackageName = "com.hfad.fcbarcelonaplayerdatabase.PackageName";

    TextView tv_squadOverview,tv_opponentDifficulty,tv_matchedOpponent,tv_YourRating;
    ListView lv_lockedInList;
    RatingBar rb_Opponent,rb_UserTeam;
    Button btn_FindOponent;
    Timer timer;



    public Formations() {}

    DataBaseHelper my_db_helper;
    DataBaseHelper anotha_helper;
    ArrayAdapter my_array_adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);





        tv_squadOverview = findViewById(R.id.tv_SquadOverview);
        tv_opponentDifficulty = findViewById(R.id.tv_Opponent_Difficulty);
        tv_matchedOpponent = findViewById(R.id.tv_FoundOpponent);
        tv_YourRating = findViewById(R.id.tv_YourSquadRating);
        btn_FindOponent = findViewById(R.id.btn_FindOponent);
        lv_lockedInList = findViewById(R.id.lv_LockedInPlayers);
        rb_Opponent = findViewById(R.id.rb_OpponentDifficulty);
        rb_UserTeam = findViewById(R.id.rb_YourRating);



        my_db_helper = new DataBaseHelper(Formations.this);
        List<PlayerModel> saved_List = my_db_helper.addAll();
        my_array_adapter = new ArrayAdapter<PlayerModel>(Formations.this,android.R.layout.simple_list_item_1,saved_List);
        lv_lockedInList.setAdapter(my_array_adapter);



        anotha_helper = new DataBaseHelper(Formations.this);
        float my_rating = anotha_helper.TeamRating();


        if(my_rating<=5 && my_rating>=4.5)
        {
            rb_UserTeam.setRating(5);
        }
        if(my_rating<4.5 && my_rating>=4)
        {
            rb_UserTeam.setRating((float) (4.5));
        }
        if(my_rating<4 && my_rating>=3.75)
        {
            rb_UserTeam.setRating((float) (4));
        }
        if(my_rating<3.75 && my_rating>=3.5)
        {
            rb_UserTeam.setRating((float) (3.5));
        }
        if(my_rating<3.5 && my_rating>=3)
        {
            rb_UserTeam.setRating((float) (3));
        }
        if(my_rating<3 && my_rating>=2.75)
        {
            rb_UserTeam.setRating((float) (2.5));
        }
        if(my_rating<2.75 && my_rating>=2.5)
        {
            rb_UserTeam.setRating((float) (2));
        }
        if(my_rating<2.5 && my_rating>=2.25)
        {
            rb_UserTeam.setRating((float) (1.5));
        }
        if(my_rating<2.25 && my_rating>=1.75)
        {
            rb_UserTeam.setRating((float) (1));
        }
        if(my_rating<1.75)
        {
            rb_UserTeam.setRating((float) 0.5);
        }







        btn_FindOponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                float Opponent_Rating = rb_Opponent.getRating();
                float TeamRating = Opponent_Rating * 20;

                if(TeamRating != 0 ) {


                    tv_matchedOpponent.setText("Generating predicted result against a team which has an average rating of: " + TeamRating + "... ");

                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Formations.this, MatchUp.class);
                            intent.putExtra(PackageName, TeamRating);
                            startActivity(intent);

                        }
                    }, 3000);
                }
                else{
                    Toast.makeText(Formations.this,"Please Select Opponent Team Value!",Toast.LENGTH_SHORT).show();
                }

            }
        });






    }







    }




