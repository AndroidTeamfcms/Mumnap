package fcms.crptrls.i9930.croptrailsfcms.DatsbaseHandler;

/**
 * Created by hp on 9/21/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GpsLogDb";

    // Contacts table name
    private static final String TABLE_GPS = "gps_log";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "lat_cord";
    private static final String KEY_LONG = "long_cord";
    private static final String KEY_SV_ID = "sv_id";
    private static final String KEY_DATE = "enter_date";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_GPS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LAT + " TEXT,"
                +KEY_LONG+" TEXT,"+ KEY_SV_ID + " TEXT,"+KEY_DATE+" DATE);";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GPS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void addGpsCordinates(SaveGpsGetterSetter savenoti){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
    values.put(KEY_LAT,savenoti.getLati_cord());
    values.put(KEY_LONG,savenoti.getLongi_cord());
    values.put(KEY_SV_ID,savenoti.getSv_id());
    values.put(KEY_DATE,savenoti.getEnter_date());

        // Inserting Row
        db.insert(TABLE_GPS, null, values);

        db.close();
    }

    // Deleting single contact
    public void deleteGps(SaveGpsGetterSetter gps_cords) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GPS, KEY_ID + " = ?",
                new String[] { String.valueOf(gps_cords.getId()) });
        db.close();
    }

    public void deleteoldgps_cords(String formattedString) {
        SQLiteDatabase db = this.getWritableDatabase();
/*
        db.delete(TABLE_GPS, KEY_DATE + " <= ?", new String[] { String.valueOf(formattedString.trim()) });
*/
        db.delete(TABLE_GPS, "1",null);
        db.close();
    }

    // Getting single contact
    SaveGpsGetterSetter getgpsCordinates(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GPS, new String[] { KEY_ID,
                        KEY_LAT }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SaveGpsGetterSetter saveGpsGetterSetter = new SaveGpsGetterSetter(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        // return contact
        return saveGpsGetterSetter;
    }

    // Getting All Contacts
    public List<SaveGpsGetterSetter> getallgpsCordinates() {
        List<SaveGpsGetterSetter> saveGpsGetterSetterslist = new ArrayList<SaveGpsGetterSetter>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SaveGpsGetterSetter saveGpsGetterSetter = new SaveGpsGetterSetter();
                saveGpsGetterSetter.setId(Integer.parseInt(cursor.getString(0)));
                saveGpsGetterSetter.setLati_cord(cursor.getString(1));
                saveGpsGetterSetter.setLongi_cord(cursor.getString(2));
                saveGpsGetterSetter.setSv_id(cursor.getString(3));
                saveGpsGetterSetter.setEnter_date(cursor.getString(4));
                // Adding contact to list
                saveGpsGetterSetterslist.add(saveGpsGetterSetter);
            } while (cursor.moveToNext());
        }

        // return contact list
        return saveGpsGetterSetterslist;
    }

    // Updating single contact
    public int updategps(SaveGpsGetterSetter updategps) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LAT, updategps.getLati_cord());
        values.put(KEY_LONG,updategps.getLongi_cord());

        // updating row
        return db.update(TABLE_GPS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(updategps.getId()) });
    }



    // Getting contacts Count
    public int getGpsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_GPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }



}