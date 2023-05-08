package com.example.trydesign;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    public static final String DB_NAME = "Shop Database";

    // below int is our database version
    public static final int DB_VERSION = 1;

    // below variable is for our table name.
    public static final String TABLE_NAME = "plants";

    // below variable is for our id column.
    public static final String ID_COL = "id";

    // below variable is for our plant name column
    public static final String NAME_COL = "name";

    // below variable id for our plant price column.
    public static final String PRICE_COL = "price";

    // below variable for our plant description column.
    public static final String DESCRIPTION_COL = "description";

    // below variable is for our plant tracks column.
    public static final String IMG_COL = "imagelink";
    public static final String AMOUNT_COL = "amount";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PRICE_COL + " INTEGER,"
                + DESCRIPTION_COL + " TEXT,"
                + IMG_COL + " INTEGER,"
                + AMOUNT_COL + " INTEGER DEFAULT 0)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
        Log.e("onatejfgvkjsdbCre: ", "onatejfgvkjsdbCre: ");
    }

    // this method is use to add new plant to our sqlite database.
    public void addNewPlant(String plantName, int plantPrice, String plantDescription, String plantImg, int plantAmount) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, plantName);
        values.put(PRICE_COL, plantPrice);
        values.put(DESCRIPTION_COL, plantDescription);
        values.put(IMG_COL, plantImg);
        values.put(AMOUNT_COL, plantAmount);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void doesYaaTableExist() {
        //Checks if the table exists. If it doesn't then a new table is created. If the table does exist then it is queried
        //Retrieves the data from the table and updating the object
        ArrayList<ShopItemDetails> items = ShopItemDetails.items;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            //checking if the table already exists
            Cursor cursorr = db.rawQuery("SELECT name " +
                            "FROM sqlite_master " +
                            "WHERE type='table' " +
                            "AND name='" +
                            TABLE_NAME + "'",
                    null);
            //table not exist so need to create one
            if (cursorr.getCount() == 0 || true) {
                //create the table
                String query1 = "DROP TABLE IF EXISTS " + TABLE_NAME;
                db.execSQL(query1);
                String query2 = "CREATE TABLE " + TABLE_NAME + " ("
                        + ID_COL + " INTEGER, "
                        + NAME_COL + " TEXT,"
                        + PRICE_COL + " INTEGER,"
                        + DESCRIPTION_COL + " TEXT,"
                        + IMG_COL + " INTEGER,"
                        + AMOUNT_COL + " INTEGER DEFAULT 0)";
                db.execSQL(query2);
                //to store all the shop item details in it
                for (int i = 0; i < ShopItemDetails.items.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(ID_COL, ShopItemDetails.items.get(i).itemID);
                    values.put(NAME_COL, ShopItemDetails.items.get(i).itemName);
                    values.put(PRICE_COL, ShopItemDetails.items.get(i).itemPrice);
                    values.put(DESCRIPTION_COL, ShopItemDetails.items.get(i).itemDescription);
                    values.put(IMG_COL, ShopItemDetails.items.get(i).imageID);
                    values.put(AMOUNT_COL, ShopItemDetails.items.get(i).itemAmount);
                    db.insert(TABLE_NAME, null, values);
                }
            }
            else if (cursorr!= null) {
                //query the table
                //using a readable database cause thr table already exists so don't want to accidentally make any changes to it
                SQLiteDatabase datab = this.getReadableDatabase();
                String query = "SELECT * FROM " + TABLE_NAME;
                Cursor cursor = datab.rawQuery(query, null);
                //will loop through till there is no more where ot put the cursor so has looked at the whole table
                if (cursor.moveToFirst() == true) {
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        int price = cursor.getInt(2);
                        String description = cursor.getString(3);
                        int image = cursor.getInt(4);
                        Integer amount = cursor.getInt(5);
                        Log.d("the int thing", "doesYaaTableExist: " +id);

                        ShopItemDetails item = ShopItemDetails.findItemById(id);
                        item.itemName = name;
                        item.itemPrice = price;
                        item.itemDescription = description;
                        item.imageID = image;
                        item.itemAmount = amount;

                    }
                }
            }
            db.setTransactionSuccessful();
        }
        catch (SQLException e) {
            Log.e("the dbhandler", "smthing to do with doesyaatable exist" + e.getMessage());
        }
        finally {
            db.endTransaction();
            db.close();
        }
    }
}
