package idmexico.com.mx.practica2_final.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

import idmexico.com.mx.practica2_final.Models.ModelApks;
import idmexico.com.mx.practica2_final.R;

/**
 * Created by Administrator on 29/06/2016.
 */
public class AdapterItemList extends ArrayAdapter<ModelApks> {

    private final String url_1="http://www.panacom.com.tw/files/ProductData/PD_Pic1/PASE-LOGO-300x200.gif";
    private final String url_2="http://laeconomia.com.mx/wp-content/uploads/pase-urbano.png";

    public AdapterItemList(Context context,  List<ModelApks> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.rows_list,parent,false);

            TextView txtApkName = (TextView) convertView.findViewById(R.id.txtrow_apkName);
            TextView txtDesarrollador = (TextView) convertView.findViewById(R.id.txtrow_desarrollador);
            ImageView imgSource = (ImageView) convertView.findViewById(R.id.row_img);

            ModelApks modelApks = getItem(position);

            Picasso.with(getContext()).load(modelApks.getResourceId()==R.drawable.ic_action_action_supervisor_account? url_1 :url_2).into(imgSource);

            txtApkName.setText(modelApks.getApkName());
            txtDesarrollador.setText(modelApks.getDesarrollador());
            imgSource.setImageResource(modelApks.getResourceId());



        }

        return convertView;
    }
}
