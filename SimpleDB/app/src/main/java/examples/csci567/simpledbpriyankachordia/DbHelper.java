package examples.csci567.simpledbpriyankachordia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Piya on 2/26/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String db = "Priyanka.db";
    public static final int version = 1;
    private final String TABLE = "PCTable";
    
    Context context;

    public DbHelper(Context context) {
        super(context, db, null, version);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + " (text VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean compare(String str){
        try{
            SQLiteDatabase qdb = this.getReadableDatabase();
            qdb.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + " (text VARCHAR);");
            Cursor c = qdb.rawQuery("SELECT * FROM " +
                    TABLE, null);

            if (c != null ) {

                if (c.moveToFirst()) {

                    do {
                        String text = c.getString(c.getColumnIndex("text"));

                        if (str.equals(text)){
                            return false;
                        }

                    }
                    while (c.moveToNext());
                }
            }
            qdb.close();
        }
        catch(SQLiteException se){
            Log.d("DB Select Error: ",se.toString());
            return false;
        }

        return true;
    }

    public boolean insertText(String text){
        try{

            if (this.compare(text)) {
                SQLiteDatabase qdb = this.getWritableDatabase();
                Log.d("DB Insert: ", "INSERT OR REPLACE INTO " +
                        TABLE + " (text) Values (" + text + ");");
                qdb.execSQL("INSERT OR REPLACE INTO " +
                        TABLE + " (text) Values (\"" + text + "\");");
                qdb.close();
            }
            else
                return false;
        }
        catch(SQLiteException se){
            Log.d("DB Insert Error: ",se.toString());
            return false;
        }

        return true;
    }


    public String getText(){
        String toReturn = "";
        try{
            SQLiteDatabase qdb = this.getReadableDatabase();
            qdb.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + " (text VARCHAR);");
            Cursor c = qdb.rawQuery("SELECT * FROM " +
                    TABLE, null);

            if (c != null ) {

                if  (c.moveToFirst()) {

                    do {
                        String text = c.getString(c.getColumnIndex("text"));
                        toReturn += text + "\n";
                    }
                    while (c.moveToNext());
                }

            }
            qdb.close();
        }
        catch(SQLiteException e){
            Log.d("DB Error: ",e.toString());
            return "";
        }

        return toReturn;
    }

}

