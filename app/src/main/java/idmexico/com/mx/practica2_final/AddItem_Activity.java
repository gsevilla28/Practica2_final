package idmexico.com.mx.practica2_final;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import idmexico.com.mx.practica2_final.Models.ModelApks;
import sql.DataSource;


/**
 * Created by Administrator on 29/06/2016.
 */
public class AddItem_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtapkname,txtdescription, txtDesarrollador;
    private CheckBox checkInstalada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.det);
        getSupportActionBar().setLogo(R.drawable.ic_action_notification_adb);

        txtapkname = (EditText) findViewById(R.id.txtapkname);
        txtdescription = (EditText) findViewById(R.id.txtDescripcion);
        txtDesarrollador= (EditText) findViewById(R.id.txtDesarrollador);
        checkInstalada = (CheckBox) findViewById(R.id.checkInstalada);

        findViewById(R.id.btnSave).setOnClickListener(this);


    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                SaveApk();
                break;

        }

    }

    private void SaveApk() {
        String apkname = txtapkname.getText().toString();
        String descrip = txtdescription.getText().toString();
        String desarollador = txtDesarrollador.getText().toString();

        if (!TextUtils.isEmpty(apkname) && !TextUtils.isEmpty(descrip) && !TextUtils.isEmpty(desarollador)) {

            Boolean isVal = false;
            DataSource dataSource = new DataSource(getApplicationContext());
            dataSource.SaveApk(new ModelApks(0, apkname, descrip, desarollador, isVal ? R.drawable.ic_action_action_3d_rotation : R.drawable.ic_action_action_supervisor_account, 0));
            isVal = !isVal;
            setResult(RESULT_OK);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Ningun Campo debe estar vacio", Toast.LENGTH_SHORT).show();
        }
            
    }
}
