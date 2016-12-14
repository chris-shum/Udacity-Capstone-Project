package app.com.loaded.android.loaded.data.local;

import android.content.Context;
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


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        ShoppingCartTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);

    }
}
