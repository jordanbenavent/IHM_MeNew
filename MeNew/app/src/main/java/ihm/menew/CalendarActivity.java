package ihm.menew;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

import ihm.menew.demonotifications.NotificationsActivity;
import ihm.menew.favoris.mvc.FavorisActivity;
import ihm.menew.fragments.MainFragment;

public class CalendarActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    private CalendarActivity calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.calendar = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        onclickMidi();
        onClickSoir();
    }

    void onClickSoir(){
        findViewById(R.id.soirLundi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(1).setSoir(MeNewApplication.platsCHoisis);
            this.popUp();
        });
        findViewById(R.id.soirMardi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(2).setSoir(MeNewApplication.platsCHoisis);
            this.popUp();
        });
        findViewById(R.id.soirMercredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(3).setSoir(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.soirJeudi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(4).setSoir(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.soirVendredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(5).setSoir(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.soirSamedi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(6).setSoir(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.soirDimanche).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(0).setSoir(MeNewApplication.platsCHoisis);
            this.popUp();

        });
    }

    void onclickMidi(){
        findViewById(R.id.midiLundi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(1).setMidi(MeNewApplication.platsCHoisis);
            this.popUp();
        });
        findViewById(R.id.midiMardi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(2).setMidi(MeNewApplication.platsCHoisis);
            this.popUp();
        });
        findViewById(R.id.midiMercredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(3).setMidi(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.midiJeudi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(4).setMidi(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.midiVendredi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(5).setMidi(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.midiSamedi).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(6).setMidi(MeNewApplication.platsCHoisis);
            this.popUp();

        });
        findViewById(R.id.midiDimanche).setOnClickListener(click -> {
            MeNewApplication.mesPlat.getEmploieDutemps().get(0).getSemaine().get(0).setMidi(MeNewApplication.platsCHoisis);
            this.popUp();

        });
    }

    void popUp(){
        AlertDialog.Builder addCalendar = new AlertDialog.Builder(calendar);
        addCalendar.setMessage("Voulez-vous aussi ajouter ce repas sur le calendrier de votre téléphone?");
        addCalendar.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE, "Repas");
                intent.putExtra(CalendarContract.Events.DESCRIPTION, MeNewApplication.platsCHoisis.toString());
                intent.putExtra(CalendarContract.Events.ALL_DAY, false);

                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CalendarActivity.this, "Pas d'application pour cette action",Toast.LENGTH_SHORT).show();
                }
            }
        });
        addCalendar.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity( new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        addCalendar.show();
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
                startActivity(new Intent(this, NotificationsActivity.class));
                return;
            default:break;
        }
    }
}
