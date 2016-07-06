package idmexico.com.mx.practica2_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import idmexico.com.mx.practica2_final.Adapters.AdapterItemList;
import idmexico.com.mx.practica2_final.Models.ModelApks;
import sql.DataSource;

/**
 * Created by Administrator on 29/06/2016.
 */
public class Detail_apps_activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    //private static final int REQUEST_CODE_ACTIVITY = 2;
    private DataSource dataSource;
    private ListView list;

    private ArrayList<ModelApks> arrayList;
    private TextView txtnohayapk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );
        setContentView(R.layout.detail_apps_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtnohayapk = (TextView) findViewById(R.id.txtNohayAPK);

        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_action_notification_adb);
        getSupportActionBar().setTitle(R.string.TitleBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        dataSource = new DataSource(getApplicationContext());
        list = (ListView) findViewById(R.id.listitem);

        arrayList = (ArrayList<ModelApks>) dataSource.getAllApks();

        if (arrayList.size()>0) {
            txtnohayapk.setText("Lista de aplicaciones");
            list.setAdapter(new AdapterItemList(getApplicationContext(), arrayList));
        }
        list.setOnItemClickListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_add:
                AddItem();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void AddItem() {
        startActivityForResult(new Intent(getApplicationContext(),AddItem_Activity.class),REQUEST_CODE_SECOND_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE_SECOND_ACTIVITY==requestCode && resultCode==RESULT_OK){
            txtnohayapk.setText("Lista de aplicaciones");
            arrayList = (ArrayList<ModelApks>) dataSource.getAllApks();
            list.setAdapter(new AdapterItemList(getApplicationContext(), arrayList));
        }
        else
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_apps_activity,menu);

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AdapterItemList adapterItemList = (AdapterItemList) parent.getAdapter();
        final ModelApks modelApks = adapterItemList.getItem(position);
        Intent intent = new Intent(getApplicationContext(),Detail_apk_simple.class);
        intent.putExtra("idapk",modelApks.getId());
        intent.putExtra("apkname",modelApks.getApkName());
        intent.putExtra("desarollador",modelApks.getDesarrollador());
        intent.putExtra("descripcion",modelApks.getDescripcion());
        intent.putExtra("imagen",modelApks.getResourceId());
        intent.putExtra("instalada",modelApks.getActualizado());
        //startActivity(intent);
        startActivityForResult(intent,REQUEST_CODE_SECOND_ACTIVITY);

    }

}
