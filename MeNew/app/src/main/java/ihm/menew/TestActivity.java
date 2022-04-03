package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TestActivity extends AppCompatActivity {

    private Intent activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        activity = getIntent();
        //int nbShift=activity.getIntExtra(getString(),0);
        //activityList=intentReceive.getCharSequenceArrayListExtra(getString(R.string.LIST));
        onClick();
    }

    public void onClick() {
        findViewById(R.id.PreparerPlat2).setOnClickListener( click -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }
}