package idmexico.com.mx.practica2_final;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Detail_apk_simple extends AppCompatActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_apk_simple);
        findViewById(R.id.btnAbrirWEB).setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        //getSupportActionBar().setLogo("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**obtener extras*/
        Intent intent = getIntent();
        String apkname= intent.getExtras().getString("apkname");
        String desa = intent.getExtras().getString("desarollador");
        String desc = intent.getExtras().getString("descripcion");
        int iResource = intent.getExtras().getInt("imagen");


        TextView txtName = (TextView) findViewById(R.id.txtName_detail_simple);
        txtName.setText(apkname);

        TextView txtDesa = (TextView) findViewById(R.id.txtDesarrollador_detail_simple);
        txtDesa.setText(desa);

        TextView txtDesc = (TextView) findViewById(R.id.txtDescripcion_detail_simple);
        txtDesc.setText(desc);

        ImageView imageView = (ImageView) findViewById(R.id.img_detail_simple);
        imageView.setImageResource(iResource);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAbrirWEB:
                String link = "http://www.idmexico.com.mx/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }

        return true;
    }


}
