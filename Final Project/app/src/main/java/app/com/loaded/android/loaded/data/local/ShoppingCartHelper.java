package app.com.loaded.android.loaded.data.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ShowMe on 12/13/16.
 */

public class ShoppingCartHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shoppingcarttable.db";
    private static final int DATABASE_VERSION = 1;

    public ShoppingCartHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        ShoppingCartTable.onCreate(sqLiteDatabase);
//        sqLiteDatabase.execSQL(ShoppingCartTable.DATABASE_CREATE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ShoppingCartTable.TABLE_SHOPPING_CART);
//        this.onCreate(sqLiteDatabase);
        ShoppingCartTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);

    }


    public Cursor getRestaurantList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ShoppingCartTable.TABLE_SHOPPING_CART, // a. table
                ShoppingCartTable.RESTAURANT_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }
}
