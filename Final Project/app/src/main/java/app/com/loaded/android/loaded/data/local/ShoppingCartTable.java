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
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_SHOPPING_CART
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
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

