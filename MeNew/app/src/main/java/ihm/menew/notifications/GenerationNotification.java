package ihm.menew.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import ihm.menew.MeNewApplication;
import ihm.menew.R;

public class GenerationNotification extends WakefulBroadcastReceiver {

    private Bitmap myBitMap;
    private static final String ACTION_START_NOTIFICATION_SERVICE = "ACTION_START_NOTIFICATION_SERVICE";
    private static int id = 0;
    public GenerationNotification(){}

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent serviceIntent = null;
        if (ACTION_START_NOTIFICATION_SERVICE.equals(action)) {
            Log.i(getClass().getSimpleName(), "onReceive from alarm, starting notification service");
            serviceIntent = NotificationService.createIntentNotif(context);
        }
        if (serviceIntent != null) {
            startWakefulService(context, serviceIntent);
        }
    }

    public void sendNotification(Context applicationContext){
        //myBitMap = BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.plat);
        myBitMap = getBitmapFromURL("https://images.pexels.com/photos/11866150/pexels-photo-11866150.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        Log.e(getClass().getSimpleName(),"LAA");
        NotificationCompat.Builder notification = new NotificationCompat.Builder(applicationContext, MeNewApplication.CHANNEL_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24_v2)
                .setContentTitle("Pense à créer ton plat !")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(myBitMap))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        MeNewApplication.getNotificationManager().notify(id++, notification.build());

    }

    public void sendNotificationCourte(Context applicationContext){
        Log.e(getClass().getSimpleName(),"LAA");
        NotificationCompat.Builder notification = new NotificationCompat.Builder(applicationContext, MeNewApplication.CHANNEL_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24_v2)
                .setContentTitle("Pense à créer ton plat !")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setTimeoutAfter(5000);
        MeNewApplication.getNotificationManager().notify(id++, notification.build());

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = getStartPendingIntent(context);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                getDateForNotification(new Date()),
                60000,
                alarmIntent);
    }

    private long getDateForNotification(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //calendar.add(Calendar.HOUR, NOTIFICATIONS_INTERVAL_IN_HOURS);
        return calendar.getTimeInMillis();
    }

    private static PendingIntent getStartPendingIntent(Context context) {
        Intent intent = new Intent(context, GenerationNotification.class);
        intent.setAction(ACTION_START_NOTIFICATION_SERVICE);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE);
    }
}
