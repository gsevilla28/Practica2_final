package sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public void UpdateApk(ModelApks modelApks){
        contentValues.put(SqLiteHelper.COLUMN_ACTUALIZADO,modelApks.getActualizado()); /*en el content se colocan los valores que se van a actualizar*/

        sql.update(SqLiteHelper.TABLE_NAME,contentValues,SqLiteHelper.COLUMN_ID + "=?",new String[]{String.valueOf(modelApks.getId())});
        contentValues.clear();
    }

    /*eliminar apks*/
    public int DeleteApk(int id){

        return sql.delete(SqLiteHelper.TABLE_NAME,SqLiteHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    /*guardar apk una vez que se instala*/
    public void SaveApk(ModelApks modelApks){
        //contentValues.put(SqLiteHelper.COLUMN_ID, modelApks.getId());
        contentValues.put(SqLiteHelper.COLUMN_APP_NAME,modelApks.getApkName());
        contentValues.put(SqLiteHelper.COLUMN_APP_DESCRIPCION,modelApks.getDescripcion());
        contentValues.put(SqLiteHelper.COLUMN_APP_DESARROLLADOR,modelApks.getDesarrollador());
        contentValues.put(SqLiteHelper.COLUMN_APP_RESOURCE,modelApks.getResourceId());
        contentValues.put(SqLiteHelper.COLUMN_ACTUALIZADO,modelApks.getActualizado());

        sql.insert(SqLiteHelper.TABLE_NAME,null,contentValues);
        contentValues.clear();
    }

    /*obtener todos las apks instaladas*/
    public List<ModelApks> getAllApks(){
        List<ModelApks> modelListApp = new ArrayList<>();
        Cursor c = sql.query(SqLiteHelper.TABLE_NAME,null,null,null,null,null,null,null);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_ID));
            String ApkName = c.getString(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_APP_NAME));
            String descrip = c.getString(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_APP_DESCRIPCION));
            String Desarrollador= c.getString(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_APP_DESARROLLADOR));
            int resourceId= c.getInt(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_APP_RESOURCE));
            int actualizado = c.getInt(c.getColumnIndexOrThrow(SqLiteHelper.COLUMN_ACTUALIZADO));

            modelListApp.add(new ModelApks(id, ApkName, descrip,Desarrollador ,resourceId, actualizado));
        }
        return modelListApp;
    }








}

