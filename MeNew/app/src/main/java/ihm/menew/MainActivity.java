package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.Objects;

import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.fragments.MainFragment;
import ihm.menew.notifications.GenerationNotification;
import ihm.menew.notifications.NotificationEventReceiver;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    GenerationNotification generateur = new GenerationNotification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MeNewApplication)getApplication()).onViewCreated(findViewById(R.id.activity_main));
        //sendNotification();
        onClickButtonProfil();
        onClickButtonPlat();
        onClickButtonTwitter();
        onClickButtonAddCalendar();
        onClickButtonAddCalendar2();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //generateur.setAlarm(getApplicationContext());
        NotificationEventReceiver.setupAlarm(getApplicationContext());
        setUpNavigationBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(getClass().getSimpleName(), "On resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ici");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onButtonClicked(View view) {
        // Retrieve button tag
        String tag = (String) view.getTag();
        System.out.println(tag);
        if (tag == null){
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        if (tag != null) {
            Log.e(getClass().getSimpleName(), "Tag est non null");
        }
        System.out.println(tag);
        switch(Objects.requireNonNull(tag)){
            case "10":
                Log.e(getClass().getSimpleName(),"Button Home clicked !");
                onResume();
                return;
            case "15":
                Log.e(getClass().getSimpleName(),"Button Planning clicked !");
                startActivity(new Intent(this, CalendarActivity.class));
                return;
            case "20":
                Log.e(getClass().getSimpleName(),"Button Favori clicked !");
                startActivity(new Intent(this, FavorisActivity.class));
                return;
            case "30":
                Log.e(getClass().getSimpleName(),"Button Search clicked !");
                startActivity(new Intent(this, Research.class));
                return;
            case "40":
                Log.e(getClass().getSimpleName(),"Button Historique clicked !");
                startActivity(new Intent(this, HistoryActivity.class));
                return;
            default:break;
        }


    }

    private void onClickButtonPlus1() {
        findViewById(R.id.buttonpllus1).setOnClickListener( click -> {
            Log.e(getClass().getSimpleName(),"Button Plus clicked !");
        });
    }

    private void onClickButtonPlus2() {
        findViewById(R.id.buttonpllus2).setOnClickListener( click -> {
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            ((TextView)findViewById(R.id.jour)).setText("Mardi");
            startActivity(intent);
        });
    }

    private void onClickButtonProfil() {
        findViewById(R.id.profil).setOnClickListener( click -> {
            Intent intent = new Intent(getApplicationContext(), ActivityUtilisateur.class);
            startActivity(intent);
        });
    }

    private void onClickButtonPlat() {
        findViewById(R.id.PreparerPlat).setOnClickListener( click -> {
            Intent intent = new Intent(getApplicationContext(), WhoIsHungryActivity.class);
            startActivity(intent);
        });
    }

    private void onClickButtonTwitter(){
        findViewById(R.id.twitter).setOnClickListener( click -> {
            Intent intent = new Intent(getApplicationContext(), TwitterActivity.class);
            startActivity(intent);
        });
    }

    private void onClickButtonAddCalendar(){
        TextView platDuMidi = findViewById(R.id.platMidi);
        findViewById(R.id.addCalendar).setOnClickListener(click ->{
            if (!platDuMidi.getText().toString().isEmpty()){
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, "Repas");
                intent.putExtra(CalendarContract.Events.DESCRIPTION, platDuMidi.getText().toString());
                intent.putExtra(CalendarContract.Events.ALL_DAY, false);

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Pas d'application pour cette action",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Il n'y a pas de plat", Toast.LENGTH_SHORT).show();
            };
        });
    }

    private void onClickButtonAddCalendar2(){
        TextView platDuSoir = findViewById(R.id.platSoir);
        findViewById(R.id.addCalendar2).setOnClickListener(click ->{
            if (!platDuSoir.getText().toString().isEmpty()){
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, "Repas");
                intent.putExtra(CalendarContract.Events.DESCRIPTION, platDuSoir.getText().toString());
                intent.putExtra(CalendarContract.Events.ALL_DAY, false);

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Pas d'application pour cette action",Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(MainActivity.this, "Il n'y a pas de plat", Toast.LENGTH_SHORT).show();
            };
        });
    }

    private void sendNotification() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(getClass().getSimpleName(),"NOTI");
            generateur.sendNotification(getApplicationContext());
        }).start();
    }

    private void setUpNavigationBar(){
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(item  -> {
            switch (item.getItemId()) {
                case R.id.navigation_home: {
                    onResume();
                    return true;
                }
                case R.id.navigation_calendar: {
                    startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    return true;
                }
                case R.id.navigation_favoris: {
                    startActivity(new Intent(getApplicationContext(), FavorisActivity.class));
                    return true;
                }
                case R.id.navigation_research: {
                    startActivity(new Intent(getApplicationContext(), Research.class));
                    return true;
                }
                case R.id.navigation_history: {
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    return true;
                }
            }
            return true;
        });
    }
}