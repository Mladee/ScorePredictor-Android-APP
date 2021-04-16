package com.hfad.fcbarcelonaplayerdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MatchHistoryDATABASE extends SQLiteOpenHelper {

    public static final String MATCH_HISTORY_TABLE = "MATCH_HISTORY_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String USER_TEAM_NAME = "USER_TEAM_NAME";
    public static final String OPPONENT_TEAM_NAME = "OPPONENT_TEAM_NAME";
    public static final String USER_TEAM_RESULT = "USER_TEAM_RESULT";
    public static final String OPPONENT_TEAM_RESULT = "OPPONENT_TEAM_RESULT";
    public static final String MATCH_RESULT = "MATCH_RESULT";

    public MatchHistoryDATABASE(@Nullable Context context) {
        super(context, "matchHistory.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createMatchHistory = " CREATE TABLE " + MATCH_HISTORY_TABLE + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_TEAM_NAME + " TEXT, " + OPPONENT_TEAM_NAME + " TEXT, " + USER_TEAM_RESULT + " INT, " + OPPONENT_TEAM_RESULT + " INT, " + MATCH_RESULT + " TEXT) ";

        db.execSQL(createMatchHistory);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addOne(MatchHistoryMODEL obj)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_TEAM_NAME,obj.getUserTeamName());
        cv.put(OPPONENT_TEAM_NAME,obj.getOpponentTeamName());
        cv.put(USER_TEAM_RESULT,obj.getUserResult());
        cv.put(OPPONENT_TEAM_RESULT,obj.getOpponentResult());
        cv.put(MATCH_RESULT,obj.getMatchResult());

        long insert = db.insert(MATCH_HISTORY_TABLE,null,cv);

        if(insert == -1){return false;}
        else{
            return true;
        }


    }

    public List<MatchHistoryMODEL> getAll()
    {
        List<MatchHistoryMODEL> returnList = new ArrayList<>();
        String query = "SELECT * FROM " + MATCH_HISTORY_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst())
        {
            do{
                int match_id = cursor.getInt(0);
                String team_name_u = cursor.getString(1);
                String team_name_op = cursor.getString(2);
                int score_u = cursor.getInt(3);
                int score_op = cursor.getInt(4);
                String match_result = cursor.getString(5);


                MatchHistoryMODEL match_obj = new MatchHistoryMODEL(match_id,team_name_u,team_name_op,score_u,score_op,match_result);
                returnList.add(match_obj);

            }while(cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return returnList;

    }





}
