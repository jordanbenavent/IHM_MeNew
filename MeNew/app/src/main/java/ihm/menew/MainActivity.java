package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

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
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case "20":
                Log.e(getClass().getSimpleName(),"Button Plan clicked !");
                startActivity(new Intent(this, SecondActivity.class));
                break;
            case "30":
                Log.e(getClass().getSimpleName(),"Button Favori clicked !");
                startActivity(new Intent(this, SecondActivity.class));
                break;
            default:break;
        }


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