package ihm.menew.demonotifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import ihm.menew.GenerationNotification;
import ihm.menew.R;
import ihm.menew.Research;
import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.fragments.MainFragment;

public class NotificationsActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    GenerationNotification generateur = new GenerationNotification();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        clickOnBouttonNotifLongue();
        clickOnBouttonNotifCourte();
    }

    private void clickOnBouttonNotifCourte() {
        findViewById(R.id.boutonNotifCourte).setOnClickListener( click -> {
            Log.e(getClass().getSimpleName(),"Button Plus clicked !");
            generateur.sendNotificationCourte(getApplicationContext());
        });
    }

    private void clickOnBouttonNotifLongue() {
        findViewById(R.id.boutonNotifLongue).setOnClickListener( click -> {
            Log.e(getClass().getSimpleName(),"Button Plus clicked !");
            generateur.sendNotification(getApplicationContext());
        });
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
                NotificationsActivity.this.finish();
                return;
            case "15":
                Log.e(getClass().getSimpleName(),"Button Planning clicked !");
                //startActivity(new Intent(this, SecondActivity.class));
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
                onResume();
                return;
            default:break;
        }

    }
}