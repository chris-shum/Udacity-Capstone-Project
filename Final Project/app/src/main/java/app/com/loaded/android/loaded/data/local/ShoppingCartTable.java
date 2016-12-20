package app.com.loaded.android.loaded.data.local;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by ShowMe on 12/13/16.
 */

public class ShoppingCartTable {

    // TODO: 12/19/16 update

    // Database table
    public static final String TABLE_SHOPPING_CART = "shoppingcart";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DESCRIPTION = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String[] RESTAURANT_COLUMNS = {COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_PRICE};

    // Database creation SQL statement
    public static final String DATABASE_CREATE = "create table "
            + TABLE_SHOPPING_CART
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DESCRIPTION + " text not null, "
            + COLUMN_PRICE + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(ShoppingCartTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING_CART);
        onCreate(database);
    }
}

