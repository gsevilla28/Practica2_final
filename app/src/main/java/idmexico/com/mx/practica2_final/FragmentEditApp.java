package idmexico.com.mx.practica2_final;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import idmexico.com.mx.practica2_final.Models.ModelApks;
import sql.DataSource;


/**
 * Created by Administrator on 05/07/2016.
 */
public class FragmentEditApp extends Fragment  {

    private EditText apkName,EditDesa,EditDescrip;
    DataSource dataSource;

    public static FragmentEditApp NuevaInstancia(int idapk, String apkName, String Desarrollador, String Descrip){
        FragmentEditApp f = new FragmentEditApp();
        Bundle b  = new Bundle();
        b.putInt("idapk",idapk);
        b.putString("apkName_F", apkName);
        b.putString("Desarrollador_D",Desarrollador);
        b.putString("Descrip_F",Descrip);
        f.setArguments(b);

        return f;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_edit_apk,container,false);
        final Bundle b = getArguments();

        apkName = (EditText) view.findViewById(R.id.apkName_edit);
        apkName.setText(b.getString("apkName_F"));

        EditDesa = (EditText) view.findViewById(R.id.desarrolla_Edit);
        EditDesa.setText(b.getString("Desarrollador_D"));

        EditDescrip = (EditText) view.findViewById(R.id.descrip_Edit);
        EditDescrip.setText(b.getString("Descrip_F"));

        dataSource = new DataSource(getActivity());

        view.findViewById(R.id.btnSave_Edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int apk_id= b.getInt("idapk");
                String nombre = apkName.getText().toString();
                String Desarrolla = EditDesa.getText().toString();
                String descrip = EditDescrip.getText().toString();

                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(Desarrolla) || TextUtils.isEmpty(descrip)) {

                    Toast.makeText(getActivity(), "ningun Campo puede ir vacio", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {

                        dataSource.UpdateApk(new ModelApks(apk_id, nombre, descrip, Desarrolla, 0, 1));
                        startActivity(new Intent(getActivity(), Detail_apps_activity.class));
                        finalize();

                    }
                    catch (Exception e) {

                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }

                }
            }
        });


        return view;
    }

}
