package app.com.loaded.android.loaded.data.local;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by ShowMe on 12/13/16.
 */

public class ShoppingCartContentProvider extends ContentProvider {

    // TODO: 12/19/16 update

    // database
    private ShoppingCartHelper database;

    // used for the UriMacher
    private static final int SHOPPING_CARTS = 10;
    private static final int SHOPPING_CART_ID = 20;

    private static final String AUTHORITY = "app.com.loaded.android.loaded.data.local";

    private static final String BASE_PATH = "shoppingcarts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/shoppingcarts";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/shoppingcart";

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, SHOPPING_CARTS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", SHOPPING_CART_ID);
    }

    @Override
    public boolean onCreate() {
        database = new ShoppingCartHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(ShoppingCartTable.TABLE_SHOPPING_CART);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case SHOPPING_CARTS:
                break;
            case SHOPPING_CART_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(ShoppingCartTable.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        if (database == null) {
            Log.d("test", "it's null");
            return null;
        } else {
            Log.d("test", "hmmm");
            SQLiteDatabase db = database.getWritableDatabase();
            Cursor cursor = queryBuilder.query(db, projection, selection,
                    selectionArgs, null, null, sortOrder);
            // make sure that potential listeners are getting notified
            cursor.setNotificationUri(getContext().getContentResolver(), uri);

            return cursor;
        }
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case SHOPPING_CARTS:
                id = sqlDB.insert(ShoppingCartTable.TABLE_SHOPPING_CART, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case SHOPPING_CARTS:
                rowsDeleted = sqlDB.delete(ShoppingCartTable.TABLE_SHOPPING_CART, selection,
                        selectionArgs);
                break;
            case SHOPPING_CART_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(
                            ShoppingCartTable.TABLE_SHOPPING_CART,
                            ShoppingCartTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(
                            ShoppingCartTable.TABLE_SHOPPING_CART,
                            ShoppingCartTable.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case SHOPPING_CARTS:
                rowsUpdated = sqlDB.update(ShoppingCartTable.TABLE_SHOPPING_CART,
                        values,
                        selection,
                        selectionArgs);
                break;
            case SHOPPING_CART_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(ShoppingCartTable.TABLE_SHOPPING_CART,
                            values,
                            ShoppingCartTable.COLUMN_ID + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(ShoppingCartTable.TABLE_SHOPPING_CART,
                            values,
                            ShoppingCartTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = {ShoppingCartTable.COLUMN_NAME,
                ShoppingCartTable.COLUMN_PRICE,
                ShoppingCartTable.COLUMN_ID};
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }




}
