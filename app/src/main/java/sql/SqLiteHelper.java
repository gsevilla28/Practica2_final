package sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.provider.SyncStateContract;

/**
 * Created by Administrator on 28/06/2016.
 */
public class SqLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="AppsID";
    private static final int DATABASE_VERSION=1;

    public static final String TABLE_NAME="APPS_TABLE";
    public static final String COLUMN_ID= BaseColumns._ID;
    public static final String COLUMN_APP_NAME = "APP_NAME";
    public static final String COLUMN_ACTUALIZADO = "ACTUALIZADO";

    private static final String CREATE_TABLE_APPS = "create table " + TABLE_NAME +
            " ( " + COLUMN_ID + " integer PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_APP_NAME + " text not null, " +
            COLUMN_ACTUALIZADO + " integer)";

    public SqLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_APPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
