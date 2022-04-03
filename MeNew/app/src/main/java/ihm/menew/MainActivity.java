package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import ihm.menew.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonClicked(findViewById(R.id.PreparerPlat));
    }

    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(),"Button clicked !");
        startActivity(new Intent(this, SecondActivity.class));
    }

    private void onClickButtonPlus1() {
        findViewById(R.id.buttonpllus1).setOnClickListener( click -> {
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            ((TextView)findViewById(R.id.jour)).setText("Mardi");
            startActivity(intent);
        });
    }

    private void onClickButtonPlus2() {
        findViewById(R.id.buttonpllus2).setOnClickListener( click -> {
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            ((TextView)findViewById(R.id.jour)).setText("Mardi");
            startActivity(intent);
        });
    }
}