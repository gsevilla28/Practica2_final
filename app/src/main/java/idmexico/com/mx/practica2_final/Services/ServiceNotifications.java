package idmexico.com.mx.practica2_final.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import idmexico.com.mx.practica2_final.R;

/**
 * Created by Administrator on 06/07/2016.
 */
public class ServiceNotifications extends Service {
    private MyAsyncTask myAsyncTask;
    private int counter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getExtras() != null && intent.getExtras().containsKey("counter")){
            counter= intent.getExtras().getInt("counter");
        }
        if (myAsyncTask==null){
            myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute();
        }

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private class MyAsyncTask extends AsyncTask<Integer,Integer,Boolean>{

        private NotificationCompat.Builder mNotif;


        @Override
        protected void onPreExecute() {
            mNotif = new NotificationCompat
                    .Builder(getApplicationContext())
                    .setContentTitle("Uninstall App")
                    .setContentText("Uninstall")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_action_get_app))
                    .setSmallIcon(android.R.drawable.ic_delete);
        }

        protected Boolean doInBackground(Integer... params) {
            for (int i=0; i<=10;i++){
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return true;
        }

        @Override /*para ver en la interface*/
        protected void onProgressUpdate(Integer... values) {
            mNotif.setProgress(10,values[0],false);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,mNotif.build()); /*mostrar finalmente la notificacion*/

        }

        @Override
        protected void onPostExecute(Boolean result) {

            if (result){

                mNotif.setProgress(0,0,false);
                mNotif.setContentTitle("Uninstall Complete");
                mNotif.setContentInfo("uninstalled");

                mNotif.setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("the app selected to uninstal to way completed"));
                mNotif.setSmallIcon(android.R.drawable.ic_dialog_alert);
                mNotif.setAutoCancel(true);


                /*accion a realizar cuando se le de tab en la notificacion*/
                //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,
                //        new Intent(getApplicationContext(),Main2Activity.class),PendingIntent.FLAG_UPDATE_CURRENT);
                //mNotif.setContentIntent(pendingIntent);

                //mNotif.addAction(android.R.drawable.ic_input_add,"Descargar d nuevo",pintent); /*agregar una accion a la notificacion*/
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(counter,mNotif.build()); /*mostrar finalmente la notificacion*/


                stopSelf(); /*detener el proceso*/

            }
            myAsyncTask =null;

        }
    }


}
