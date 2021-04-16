package com.hfad.fcbarcelonaplayerdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper{


    public static final String BARCELONA_TABLE = "BARCELONA_TABLE";
    public static final String COLUMN_PLAYER_NAME = "PLAYER_NAME";
    public static final String COLUMN_PLAYER_NATIONALITY = "PLAYER_NATIONALITY";
    public static final String COLUMN_PLAYER_RATING = "PLAYER_RATING";
    public static final String COLUMN_PLAYER_POSITION = "PLAYER_POSITION";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context,"Barcelona.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = " CREATE TABLE " + BARCELONA_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PLAYER_NAME + " TEXT, " + COLUMN_PLAYER_NATIONALITY + " TEXT, " + COLUMN_PLAYER_RATING + " INT, " + COLUMN_PLAYER_POSITION + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(PlayerModel new_Player)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PLAYER_NAME,new_Player.getName());
        cv.put(COLUMN_PLAYER_NATIONALITY,new_Player.getNationality());
        cv.put(COLUMN_PLAYER_RATING,new_Player.getRating());
        cv.put(COLUMN_PLAYER_POSITION,new_Player.getPosition());

        long insert = db.insert(BARCELONA_TABLE,null,cv);

        if(insert == -1)
        {
            return false;

        }
        else{
            return true;
        }

    }


    public List<PlayerModel> addAll()
    {
        List<PlayerModel> returnList = new ArrayList<PlayerModel>();
        String TheQueryString = " SELECT * FROM " + BARCELONA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(TheQueryString,null);

        if(cursor.moveToFirst())
        {
            do{
                int PlayerID = cursor.getInt(0);
                String PlayerName = cursor.getString(1);
                String PlayerNationality = cursor.getString(2);
                int PlayerRating = cursor.getInt(3);
                String PlayerPosition = cursor.getString(4);

                PlayerModel anothaPlayah = new PlayerModel(PlayerID,PlayerName,PlayerNationality,PlayerRating,PlayerPosition);
                returnList.add(anothaPlayah);

            }while(cursor.moveToNext());

        }
        else{}

        db.close();
        cursor.close();

        return returnList;

    }

    public boolean deleteOne(PlayerModel playerObject)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " DELETE FROM " + BARCELONA_TABLE + " WHERE " + COLUMN_ID + " = " + playerObject.getId();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }
    }


    public float TeamRating(){
        float rating=0;
        int number=0;
        float sum=0;

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_PLAYER_RATING + " FROM " + BARCELONA_TABLE;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()) {


            while (cursor.moveToNext()) {

                int PlayerRating = cursor.getInt(0);
                number = number + 1;
                sum = sum + PlayerRating;

            }

            rating = ((sum / number)/20);
        }
        else{

        }

        db.close();
        cursor.close();
        return rating;


    }


    public int NumberofPlayers(){

        int number=0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT COUNT(*) FROM " + BARCELONA_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        number = cursor.getInt(0);


            db.close();
            cursor.close();
            return number;
    }


}
