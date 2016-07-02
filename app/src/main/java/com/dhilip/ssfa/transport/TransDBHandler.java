package com.dhilip.ssfa.transport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TransDBHandler extends SQLiteOpenHelper
{

    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "guestsInfo";

    // Contacts table name
    private static final String TABLE_GUESTS = "guestsDetails";


    // Guests Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_OTHER_COUNT = "othersCount";
    private static final String KEY_OTHER_DETAILS = "otherDetails";
    private static final String KEY_HOMETOWN = "hometown";
    private static final String KEY_ARR_DEP_MODE = "arrivalModeOfTravel";
    private static final String KEY_ARR_DEP_TIME = "arrivalTime";
    private static final String KEY_ARR_DEP_DETAILS = "arrivalDetails";
    private static final String KEY_STAY = "placeOfStay";
    private static final String KEY_CONTACT = "contactNo";
    private static final String KEY_MAIL_ID = "emailID";
    private static final String KEY_IS_ARTIST = "isArtist";
    private static final String KEY_IS_DEPARTURE = "isDeparture";
    private static final String KEY_IS_DONE = "isDone";

    public TransDBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUESTS);
        String CREATE_ARR_DEP_TABLE = "CREATE TABLE " + TABLE_GUESTS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_OTHER_COUNT + " INTEGER," +
                KEY_OTHER_DETAILS + " TEXT," +
                KEY_HOMETOWN + " TEXT," +
                KEY_STAY + " TEXT," +
                KEY_ARR_DEP_MODE + " TEXT," +
                KEY_ARR_DEP_TIME + " TEXT," +
                KEY_ARR_DEP_DETAILS + " TEXT," +
                KEY_CONTACT + " TEXT," +
                KEY_MAIL_ID + " TEXT," +
                KEY_IS_ARTIST + " TEXT," +
                KEY_IS_DEPARTURE + " TEXT," +
                KEY_IS_DONE + " TEXT" + ")";
        db.execSQL(CREATE_ARR_DEP_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUESTS);

        // Creating tables again
        onCreate(db);
    }

    // Adding new guest
    public boolean addGuest(Guest guest)
    {
        boolean result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, guest.getName()); // Guest Name
        values.put(KEY_OTHER_COUNT, guest.getOthersCount()); // Guest Phone Number
        values.put(KEY_OTHER_DETAILS, guest.getOtherDetails());
        values.put(KEY_HOMETOWN, guest.getHometown());
        values.put(KEY_STAY, guest.getPlaceOfStay());
        values.put(KEY_ARR_DEP_MODE, guest.getModeOfTravel());
        values.put(KEY_ARR_DEP_TIME, guest.getTimeOfTravel());
        values.put(KEY_ARR_DEP_DETAILS, guest.getDetailsOfTravel());
        values.put(KEY_CONTACT, guest.getContactNo());
        values.put(KEY_MAIL_ID, guest.getEmailID());
        values.put(KEY_IS_ARTIST, guest.IsArtist());
        values.put(KEY_IS_DEPARTURE, guest.isDeparture());
        values.put(KEY_IS_DONE, guest.isDone());
        // Inserting Row
        result = (db.insert(TABLE_GUESTS, null, values) != -1);

        db.close();
        return result;
    }

    // Getting one guest
    public List<Guest> getGuest(int guestID)
    {
        List<Guest> guestList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        int guestID1;
        int guestID2;

        if ((guestID % 2) == 0)
        {
            guestID1 = guestID - 1;
            guestID2 = guestID;
        } else
        {
            guestID1 = guestID;
            guestID2 = guestID + 1;
        }

        String selectGuestQuery = "SELECT  * FROM " + TABLE_GUESTS + " WHERE " + KEY_ID + " in (" + guestID1 + ", " + guestID2 + ")";
        Cursor cursor = db.rawQuery(selectGuestQuery, null);

        if (cursor == null)
            return null;
        if (cursor.moveToFirst())
        {
            do
            {
                Guest guest = new Guest();
                guest.setId(Integer.parseInt(cursor.getString(0)));
                guest.setName(cursor.getString(1));
                guest.setOthersCount(cursor.getString(2));
                guest.setOtherDetails(cursor.getString(3));
                guest.setHometown(cursor.getString(4));
                guest.setPlaceOfStay(cursor.getString(5));
                guest.setModeOfTravel(cursor.getString(6));
                guest.setTimeOfTravel(cursor.getString(7));
                guest.setDetailsOfTravel(cursor.getString(8));
                guest.setContactNo(cursor.getString(9));
                guest.setEmailID(cursor.getString(10));
                guest.setIsArtist(Integer.parseInt(cursor.getString(11)) == 1);
                guest.setIsDeparture(Integer.parseInt(cursor.getString(12)) == 1);
                guest.setIsDone(Integer.parseInt(cursor.getString(13)) == 1);

                // Adding guest to list
                guestList.add(guest);
            } while (cursor.moveToNext());
        }

        // return guest list
        return guestList;
    }

    // Getting All Guests
    public List<Guest> getAllGuests()
    {
        List<Guest> guestList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GUESTS + " ORDER BY datetime(" + KEY_ARR_DEP_TIME + ") ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Guest guest = new Guest();
                guest.setId(Integer.parseInt(cursor.getString(0)));
                guest.setName(cursor.getString(1));
                guest.setOthersCount(cursor.getString(2));
                guest.setOtherDetails(cursor.getString(3));
                guest.setHometown(cursor.getString(4));
                guest.setPlaceOfStay(cursor.getString(5));
                guest.setModeOfTravel(cursor.getString(6));
                guest.setTimeOfTravel(cursor.getString(7));
                guest.setDetailsOfTravel(cursor.getString(8));
                guest.setContactNo(cursor.getString(9));
                guest.setEmailID(cursor.getString(10));
                guest.setIsArtist(Integer.parseInt(cursor.getString(11)) == 1);
                guest.setIsDeparture(Integer.parseInt(cursor.getString(12)) == 1);
                guest.setIsDone(Integer.parseInt(cursor.getString(13)) == 1);

                // Adding contact to list
                guestList.add(guest);
            } while (cursor.moveToNext());
        }

        // return contact list
        return guestList;
    }

    // Getting guests Count
    public int getGuestsCount()
    {
        String countQuery = "SELECT  * FROM " + TABLE_GUESTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating a guest
    public int updateGuest(Guest guest)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, guest.getName());
        values.put(KEY_OTHER_COUNT, guest.getOthersCount());
        values.put(KEY_OTHER_DETAILS, guest.getOtherDetails());
        values.put(KEY_HOMETOWN, guest.getHometown());
        values.put(KEY_STAY, guest.getPlaceOfStay());
        values.put(KEY_ARR_DEP_MODE, guest.getModeOfTravel());
        values.put(KEY_ARR_DEP_TIME, guest.getTimeOfTravel());
        values.put(KEY_ARR_DEP_DETAILS, guest.getDetailsOfTravel());
        values.put(KEY_CONTACT, guest.getContactNo());
        values.put(KEY_MAIL_ID, guest.getEmailID());
        values.put(KEY_IS_ARTIST, guest.IsArtist());
        values.put(KEY_IS_DEPARTURE, guest.isDeparture());
        values.put(KEY_IS_DONE, guest.isDone());

        // updating row
        return db.update(TABLE_GUESTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(guest.getId())});
    }

    // Deleting a guest
    public void deleteGuest(Guest guest)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GUESTS, KEY_ID + " = ?",
                new String[]{String.valueOf(guest.getId())});
        db.close();
    }

    public List<String> getDepartureDistinctTime()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String distinctDepartureTimeQuery = "SELECT DISTINCT datetime(" + KEY_ARR_DEP_TIME + ") FROM " + TABLE_GUESTS + " WHERE " +
                KEY_IS_DEPARTURE + " = '" + 1 + "' AND " + KEY_IS_DONE + " = '" + 0 + "' ORDER BY datetime(" + KEY_ARR_DEP_TIME + ") ASC";

        Cursor cursor = db.rawQuery(distinctDepartureTimeQuery, null);
        List<String> distinctDepartureTimeArrayList = new ArrayList<>();

        if (cursor != null)
        {
            while (cursor.moveToNext())
                distinctDepartureTimeArrayList.add(cursor.getString(0));
            cursor.close();
        }
        return distinctDepartureTimeArrayList;
    }

    public List<String> getArrivalDistinctTime()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String distinctArrivalTimeQuery = "SELECT DISTINCT datetime(" + KEY_ARR_DEP_TIME + ") FROM " + TABLE_GUESTS + " WHERE " +
                KEY_IS_DEPARTURE + " = '" + 0 + "' AND " + KEY_IS_DONE + " = '" + 0 + "' ORDER BY datetime(" + KEY_ARR_DEP_TIME + ") ASC";

        Cursor cursor = db.rawQuery(distinctArrivalTimeQuery, null);
        List<String> distinctArrivalTimeArrayList = new ArrayList<>();

        if (cursor != null)
        {
            while (cursor.moveToNext())
                distinctArrivalTimeArrayList.add(cursor.getString(0));
            cursor.close();
        }
        return distinctArrivalTimeArrayList;
    }

    public List<Guest> getGuests(String dateTime, int isDeparture, int isDone)
    {
        List<Guest> guestListAtGivenTime = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_GUESTS + " WHERE " + KEY_ARR_DEP_TIME + " = '" + dateTime + "' AND " +
                KEY_IS_DEPARTURE + " = '" + isDeparture + "' AND " + KEY_IS_DONE + " = '" + isDone + "' ORDER BY " + KEY_IS_ARTIST + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Guest guest = new Guest();
                guest.setId(Integer.parseInt(cursor.getString(0)));
                guest.setName(cursor.getString(1));
                guest.setOthersCount(cursor.getString(2));
                guest.setOtherDetails(cursor.getString(3));
                guest.setHometown(cursor.getString(4));
                guest.setPlaceOfStay(cursor.getString(5));
                guest.setModeOfTravel(cursor.getString(6));
                guest.setTimeOfTravel(cursor.getString(7));
                guest.setDetailsOfTravel(cursor.getString(8));
                guest.setContactNo(cursor.getString(9));
                guest.setEmailID(cursor.getString(10));
                guest.setIsArtist(Integer.parseInt(cursor.getString(11)) == 1);
                guest.setIsDeparture(Integer.parseInt(cursor.getString(12)) == 1);
                guest.setIsDone(Integer.parseInt(cursor.getString(13)) == 1);

                // Adding contact to list
                guestListAtGivenTime.add(guest);
            } while (cursor.moveToNext());
        }

        // return contact list
        return guestListAtGivenTime;
    }
}