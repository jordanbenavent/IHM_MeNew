package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import ihm.menew.fragments.MainFragment;
import ihm.menew.webservice.Point;
import ihm.menew.webservice.WebService;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    GenerationNotification generateur = new GenerationNotification();
    private WebService webService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MeNewApplication)getApplication()).onViewCreated(findViewById(R.id.activity_main));
        onButtonClicked(findViewById(R.id.PreparerPlat));
        onClickButtonPlus1();
        sendNotification();
        onClickButtonProfil();
        webService = new WebService();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(getClass().getSimpleName(), "On resume");
    }

    @Override
    public void onButtonClicked(View view) {
        // Retrieve button tag
        String tag = (String) view.getTag();
        if (tag == null){
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        if (tag != null) {
            Log.e(getClass().getSimpleName(), "Tag est non null");
        }
        switch(Objects.requireNonNull(tag)){
            case "10":
                Log.e(getClass().getSimpleName(),"Button Home clicked !");
                onResume();
                //startActivity(new Intent(this, SecondActivity.class));
                return;
            case "20":
                Log.e(getClass().getSimpleName(),"Button Favori clicked !");
                Point point;
                try {
                    point = webService.makeRequest("pasta");
                    Log.e("Reponse : ", point.getResults().get(0).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(this, SecondActivity.class));
                return;
            case "30":
                Log.e(getClass().getSimpleName(),"Button Search clicked !");
                startActivity(new Intent(this, SecondActivity.class));
                return;
            default:break;
        }


    }

    private void onClickButtonPlus1() {
        findViewById(R.id.buttonpllus1).setOnClickListener( click -> {
            Log.e(getClass().getSimpleName(),"Button Plus clicked !");
            generateur.sendNotification(getApplicationContext());
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

}