package sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

import idmexico.com.mx.practica2_final.Models.ModelApks;

/**
 * Created by Administrator on 28/06/2016.
 */
public class DataSource {

    private final SQLiteDatabase sql;
    private final ContentValues contentValues = new ContentValues();

    public DataSource(Context context) {
        SqLiteHelper helper = new SqLiteHelper(context);
        sql = helper.getWritableDatabase();
    }



    /*obtener todos las apks instaladas*/
    public List<ModelApks> getAllApks(){
        List<ModelApks> modelListApp = new ArrayList<>();
        Cursor c = sql.query(SqLiteHelper.TABLE_NAME,null,null,null,null,null,null,null);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_ID));
            String ApkName = c.getString(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_APP_NAME));
            int actualizado = c.getInt(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_ACTUALIZADO));

            modelListApp.add(new ModelApks(actualizado, ApkName, id));
        }
        return modelListApp;
    }








}

