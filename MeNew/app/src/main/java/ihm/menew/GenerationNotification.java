package ihm.menew;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GenerationNotification extends Application {

    private Bitmap myBitMap;
    private static int id = 0;
    GenerationNotification(){}

    public void sendNotification(Context applicationContext){
        myBitMap = BitmapFactory.decodeResource(applicationContext.getResources(), R.drawable.plat);
        //myBitMap = getBitmapFromURL("https://images.pexels.com/photos/11866150/pexels-photo-11866150.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
        Log.e(getClass().getSimpleName(),"LAA");
        NotificationCompat.Builder notification = new NotificationCompat.Builder(applicationContext, MeNewApplication.CHANNEL_HIGH)
                .setSmallIcon(R.drawable.ic_baseline_add_circle_24_v2)
                .setContentTitle("Pense à créer ton plat !")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(myBitMap))
                .setPriority(NotificationCompat.PRIORITY_HIGH);
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
}
