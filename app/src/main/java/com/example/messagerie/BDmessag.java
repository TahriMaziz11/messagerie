package com.example.messagerie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BDmessag extends SQLiteOpenHelper {
private static final String DB_NAME="dbmessag";
private static final String TB_NAME="message";
private static final int version=1;


 public BDmessag(Context context){super(context,DB_NAME,null,version);}


    @Override
    public void onCreate(SQLiteDatabase db) {
    String create="CREATE TABLE "+TB_NAME+" (id integer PRIMARY KEY,message VARCHAR,sender VARCHAR,time VARCHAR)";
     db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       String delete ="DROP TABLE IF EXISTS "+TB_NAME;
       db.execSQL(delete);
       onCreate(db);
    }
    public void AjoutMessag (Message message)
   {
      SQLiteDatabase db =getWritableDatabase();
      ContentValues values =new ContentValues();
       values.put("message",message.getMess());
       values.put("sender",message.getSender());
      db.insert(TB_NAME,null,values);

   }
public ArrayList<Message> getAllMessag()
{
   ArrayList<Message> messages =new ArrayList<>();
   String query=" SELECT * FROM "+TB_NAME+";";

   SQLiteDatabase db = this.getReadableDatabase();
   Cursor cursor = db.rawQuery(query,null);
   if(cursor.moveToFirst())
   {
      do {

         int id = cursor.getInt(cursor.getColumnIndex("id"));
         String mess = cursor.getString(cursor.getColumnIndex("message"));
         String sender = cursor.getString(cursor.getColumnIndex("sender"));
         String time=cursor.getString(cursor.getColumnIndex("time"));
         Message message = new Message(mess,sender,time);

         messages.add(message);

      }while (cursor.moveToNext());
   }

   return messages;
}





   public Message getMessagById(int id)
   {
      SQLiteDatabase db = this.getReadableDatabase();
      String query=" SELECT * FROM "+TB_NAME+" WHERE id = "+id;
      Cursor cursor = db.rawQuery(query,null);
      Message message =null ;
      if (cursor.moveToFirst())
      {
         String mess_c=cursor.getString(cursor.getColumnIndex("message"));
         String sender_c=cursor.getString(cursor.getColumnIndex("sender"));
         String time=cursor.getString(cursor.getColumnIndex("time"));
         message= new Message(mess_c,sender_c,time,id);
      }

      return message;
   }
   public Message UpdateMessag(Message message)
   {

      SQLiteDatabase db = getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put("message",message.getMess());
      values.put("sender",message.getSender());
      values.put("time",message.getTime());

      db.update(TB_NAME,values,"id=?",new String[]{String.valueOf(message.getId())});

      return message;
   }

   public void DeleteMessag(int id)
   {
      SQLiteDatabase db = this.getWritableDatabase();

      db.delete(TB_NAME,"id=?",new String[]{String.valueOf(id)});

   }



}




