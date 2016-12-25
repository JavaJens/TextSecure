package org.thoughtcrime.securesms.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.whispersystems.signalservice.api.util.InvalidNumberException;
import org.whispersystems.signalservice.api.util.PhoneNumberFormatter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains the contact phone numbers which should be sent to the server
 */
public class CustomContactDirectory {

    private static final String DATABASE_NAME    = "whitelist_directory.db";
    private static final int    DATABASE_VERSION = 1;

    private static final String TABLE_NAME   = "directory";
    private static final String ID           = "_id";
    private static final String NUMBER       = "number";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY, " +
            NUMBER       + " TEXT UNIQUE);";

    private static final Object instanceLock = new Object();
    private static volatile CustomContactDirectory instance;

    public static CustomContactDirectory getInstance(Context context) {
        if (instance == null) {
            synchronized (instanceLock) {
                if (instance == null) {
                    instance = new CustomContactDirectory(context.getApplicationContext());
                }
            }
        }

        return instance;
    }

    private final DatabaseHelper databaseHelper;
    private final Context        context;

    private CustomContactDirectory(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Use this function to get the selected phone numbers
     * @return set of phone numbers
     */
    public Set<String> getAllNumbers() {
        Set<String> result = new HashSet<>();

        Cursor cursor = databaseHelper.getReadableDatabase().query(
                TABLE_NAME, new String[]{NUMBER}, null, null, null, null, null
        );

        try {
            if(cursor != null && cursor.moveToFirst()) {
                do {
                    result.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return Collections.unmodifiableSet(result);
    }

    public Set<String> getAllNumbersAsE164(String localNumber) {
        return getE164Numbers(getAllNumbers(), localNumber);
    }

    private static Set<String> getE164Numbers(Set<String> numbers, String localNumber) {
        Set<String> result = new HashSet<>();

        for(String rawNumber : numbers) {
            try {
                final String e164Number = PhoneNumberFormatter.formatNumber(rawNumber, localNumber);
                result.add(e164Number);
            } catch (InvalidNumberException e) {
                Log.w("Directory", "Invalid number: " + rawNumber);
            }
        }

        return result;
    }

    public void removeAllNumbers() {
        databaseHelper.getWritableDatabase().delete(TABLE_NAME, "1 = 1", null);
    }

    public void addNumbers(Set<String> numbers) {
        for(String number : numbers) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NUMBER, number);

            databaseHelper.getWritableDatabase().insert(
                    TABLE_NAME, null, contentValues
            );
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory,
                              int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
