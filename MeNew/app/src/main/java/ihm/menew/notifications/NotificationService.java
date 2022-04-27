package ihm.menew.notifications;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import ihm.menew.MeNewApplication;
import ihm.menew.R;
import ihm.menew.demonotifications.NotificationsActivity;

public class NotificationService extends IntentService {

    public static final String START = "START";
    private static int id = 19 ;

    /**
     * @param name
     * @deprecated
     */
    public NotificationService(String name) {
        super(name);
    }


    public static Intent createIntentNotif(Context context){
        Intent intent = new Intent(context, GenerationNotification.class);
        intent.setAction(START);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("pppp");
        try {
            String action = intent.getAction();
            if (START.equals(action)) {
                processStartNotification();
            }

        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void processStartNotification() {
        System.out.println("ici");
        Bitmap myBitMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.plat);
        //myBitMap = getBitmapFromURL("https://images.pexels.com/photos/11866150/pexels-photo-11866150.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        Log.e(getClass().getSimpleName(),"LAA");
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, MeNewApplication.CHANNEL_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24_v2)
                .setContentTitle("Pense à créer ton plat !")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(myBitMap))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                id,
                new Intent(this, NotificationsActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        MeNewApplication.getNotificationManager().notify(id++, notification.build());
    }

}
