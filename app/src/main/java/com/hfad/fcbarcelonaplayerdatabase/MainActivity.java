package com.hfad.fcbarcelonaplayerdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText text_Name,text_Nationality,text_Position,text_Rating;
    Button btn_add,btn_lockIn;
    ListView lv_players;
    DataBaseHelper supp_Playah,Neymar,Data_Base_Object;
    ArrayAdapter playerArrayAdapter;
    Switch ib_sound;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        text_Name = findViewById(R.id.et_player_name);
        text_Nationality = findViewById(R.id.et_player_nationality);
        text_Rating = findViewById(R.id.et_player_rating);
        text_Position = findViewById(R.id.et_player_position);
        btn_add = findViewById(R.id.btn_add);
        btn_lockIn = findViewById(R.id.btn_LockIn);
        lv_players = findViewById(R.id.lv_players);
        ib_sound = findViewById(R.id.ib_sound);



        Neymar = new DataBaseHelper(MainActivity.this);
        int number_of_Players = Neymar.NumberofPlayers();

        if ( number_of_Players >=9 && number_of_Players <18)
        {
            btn_lockIn.setEnabled(true);
        }



        final MediaPlayer soundPlayer = MediaPlayer.create(this,R.raw.music);


        ib_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( soundPlayer.isPlaying())
                {
                    soundPlayer.pause();
                }
                else{
                    soundPlayer.start();
                }

            }
        });

        Data_Base_Object = new DataBaseHelper(MainActivity.this);
        List<PlayerModel> allOfThem =  Data_Base_Object.addAll();

        playerArrayAdapter = new ArrayAdapter<PlayerModel>(MainActivity.this,android.R.layout.simple_list_item_1,allOfThem);
        lv_players.setAdapter(playerArrayAdapter);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlayerModel myPlayer;

               Neymar = new DataBaseHelper(MainActivity.this);
                int number_of_Players = Neymar.NumberofPlayers();



                Toast.makeText(MainActivity.this,"Number of players: " + (number_of_Players+1) ,Toast.LENGTH_SHORT).show();

                try{
                     myPlayer = new PlayerModel(-1,text_Name.getText().toString(),text_Nationality.getText().toString(),Integer.parseInt(text_Rating.getText().toString()),text_Position.getText().toString());
                    //Toast.makeText(MainActivity.this,myPlayer.toString(),Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    //Toast.makeText(MainActivity.this,"Fail.",Toast.LENGTH_SHORT).show();
                    myPlayer = new PlayerModel(-1,"error","error",0,"error");
                }

                        DataBaseHelper new_Player = new DataBaseHelper(MainActivity.this);
                        boolean success = new_Player.addOne(myPlayer);

                         supp_Playah = new DataBaseHelper(MainActivity.this);
                        List<PlayerModel> allOfThem =  supp_Playah.addAll();

                       playerArrayAdapter = new ArrayAdapter<PlayerModel>(MainActivity.this,android.R.layout.simple_list_item_1,allOfThem);
                       lv_players.setAdapter(playerArrayAdapter);



                       if(number_of_Players>9 && number_of_Players <18){
                            btn_lockIn.setEnabled(true);
                }
                       else btn_lockIn.setEnabled(false);






            }
        });



        lv_players.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayerModel Messi = (PlayerModel) parent.getItemAtPosition(position);
                supp_Playah.deleteOne(Messi);
                List<PlayerModel> allOfThem =  supp_Playah.addAll();
                playerArrayAdapter = new ArrayAdapter<PlayerModel>(MainActivity.this,android.R.layout.simple_list_item_1,allOfThem);
                lv_players.setAdapter(playerArrayAdapter);
                Neymar = new DataBaseHelper(MainActivity.this);
                int number_of_Players = Neymar.NumberofPlayers();

                if ( number_of_Players >=9 && number_of_Players <18)
                {
                    btn_lockIn.setEnabled(true);
                }
                else{
                    btn_lockIn.setEnabled(false);
                }
                //Toast.makeText(MainActivity.this,"Delete succesful!",Toast.LENGTH_SHORT).show();


            }
        });



        btn_lockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,Formations.class);
                startActivity(intent);


            }
        });





    }
}