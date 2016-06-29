package idmexico.com.mx.practica2_final.Models;

/**
 * Created by Administrator on 28/06/2016.
 */
public class ModelApks {


    public ModelApks(int id, String apkName, String descripcion,String Desarrollador ,int resourceId, int actualizado ) {
        Actualizado = actualizado;
        ApkName = apkName;
        this.descripcion = descripcion;
        this.id = id;
        this.resourceId = resourceId;
        this.Desarrollador=Desarrollador;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    private int resourceId;
    private int Actualizado;

    public String getDesarrollador() {
        return Desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        Desarrollador = desarrollador;
    }

    private String Desarrollador;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String descripcion;

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
