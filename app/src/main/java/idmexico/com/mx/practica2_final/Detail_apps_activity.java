package idmexico.com.mx.practica2_final;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import idmexico.com.mx.practica2_final.R;

/**
 * Created by Administrator on 29/06/2016.
 */
public class Detail_apps_activity extends AppCompatActivity {

    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState );
        setContentView(R.layout.detail_apps_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_action_notification_adb);
        getSupportActionBar().setTitle(R.string.TitleBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

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


        }
        else
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail_apps_activity,menu);

        return true;
    }
}
