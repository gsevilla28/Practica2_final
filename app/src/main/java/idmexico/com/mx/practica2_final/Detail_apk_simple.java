package idmexico.com.mx.practica2_final;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import idmexico.com.mx.practica2_final.Services.ServiceNotifications;
import sql.DataSource;


public class Detail_apk_simple extends AppCompatActivity implements View.OnClickListener {


    private TextView txtName,txtDesa,txtDesc;
    private int idapk;

    DataSource dataSource;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_apk_simple);
        findViewById(R.id.btnAbrirWEB).setOnClickListener(this);
        findViewById(R.id.btnUninstall).setOnClickListener(this);


                /**obtener extras*/
        Intent intent = getIntent();
        idapk = intent.getExtras().getInt("idapk");
        String apkname= intent.getExtras().getString("apkname");
        String desa = intent.getExtras().getString("desarollador");
        String desc = intent.getExtras().getString("descripcion");
        int iResource = intent.getExtras().getInt("imagen");


        txtName = (TextView) findViewById(R.id.txtName_detail_simple);
        txtName.setText(apkname);

        txtDesa = (TextView) findViewById(R.id.txtDesarrollador_detail_simple);
        txtDesa.setText(desa);

        txtDesc = (TextView) findViewById(R.id.txtDescripcion_detail_simple);
        txtDesc.setText(desc);

        ImageView imageView = (ImageView) findViewById(R.id.img_detail_simple);
        imageView.setImageResource(iResource);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(apkname);
        getSupportActionBar().setLogo(iResource);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new DataSource(getApplicationContext());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAbrirWEB:
                String link = "http://www.idmexico.com.mx/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(intent);
                break;
            case R.id.btnUninstall:
                DesintallApp();
                break;
        }
    }

    private void DesintallApp() {
        String nombre = txtName.getText().toString();

        new AlertDialog.Builder(Detail_apk_simple.this)
                .setTitle("Uninstal app")
                .setMessage(String.format("¿Are you sure uninstall app:  %s?",nombre))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startService( new Intent(getApplicationContext(), ServiceNotifications.class));
                        int borrado = dataSource.DeleteApk((idapk));
                        Toast.makeText(getApplicationContext(), "Desinstalando aplicacion", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setCancelable(false).create().show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.Menu_Editar:
                EditarAppNewFragment();
                break;
        }

        return true;
    }

    private void EditarAppNewFragment() {
        String name=txtName.getText().toString();
        String Desarrolla = txtDesa.getText().toString();
        String Descri = txtDesc.getText().toString();

        FragmentEditApp f = FragmentEditApp.NuevaInstancia(idapk,name,Desarrolla,Descri);
        getFragmentManager().beginTransaction().replace(R.id.Fholder, f).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detail_apk_simple,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
