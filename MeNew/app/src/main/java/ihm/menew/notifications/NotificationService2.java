package ihm.menew.notifications ;
import android.app.Service ;
import android.content.Intent ;
import android.os.Handler ;
import android.os.IBinder ;
import android.util.Log ;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer ;
import java.util.TimerTask ;

import ihm.menew.notifications.GenerationNotification;

public class NotificationService2 extends Service {
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    Timer timer ;
    TimerTask timerTask ;
    String TAG = "Timers" ;
    int Your_X_SECS = 5 ;
    @Override
    public IBinder onBind (Intent arg0) {
        return null;
    }
    @Override
    public int onStartCommand (Intent intent , int flags , int startId) {
        Log. e ( TAG , "onStartCommand" ) ;
        super .onStartCommand(intent , flags , startId) ;
        startTimer() ;
        return START_STICKY ;
    }
    @Override
    public void onCreate () {
        Log. e ( TAG , "onCreate" ) ;
    }
    @Override
    public void onDestroy () {
        Log. e ( TAG , "onDestroy" ) ;
        stopTimerTask() ;
        super .onDestroy() ;
    }
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler() ;
    public void startTimer () {
        timer = new Timer() ;
        initializeTimerTask() ;
        timer.schedule(timerTask, getGoodDate());
        //timer.schedule( timerTask , 5000 , 1000 ) ; //
    }

    public Date getGoodDate(){
        Date date = new Date();
        System.out.println(date);
        if( !(date.getHours() > 22) && date.getHours() < 13){
            date.setHours(11);
            date.setMinutes(30);
        } else {
            System.out.println("maintenant");
            date.setHours(22);
            date.setMinutes(date.getMinutes());
            date.setSeconds(date.getSeconds()+10);
        }
        return date;
    }
    public void stopTimerTask () {
        if ( timer != null ) {
            timer .cancel() ;
            timer = null;
        }
    }
    public void initializeTimerTask () {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        GenerationNotification generateur = new GenerationNotification();
                        generateur.sendNotification(getApplicationContext());
                    }
                });
            }
        };
    }
}