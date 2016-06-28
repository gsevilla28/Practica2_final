package idmexico.com.mx.practica2_final.Models;

/**
 * Created by Administrator on 28/06/2016.
 */
public class ModelApks {

    public ModelApks(int actualizado, String apkName, int id) {
        Actualizado = actualizado;
        ApkName = apkName;
        this.id = id;
    }

    private int Actualizado;

    public int getActualizado() {
        return Actualizado;
    }
    public void setActualizado(int actualizado) {
        Actualizado = actualizado;
    }
    private String ApkName;
    public String getApkName() {
        return ApkName;
    }
    public void setApkName(String apkName) {
        ApkName = apkName;
    }
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



}
