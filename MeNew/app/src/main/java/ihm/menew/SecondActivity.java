package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Objects;

import ihm.menew.fragments.MainFragment;

public class SecondActivity extends AppCompatActivity implements MainFragment.OnButtonClickedListener {

    // Contient actuellement le fragment photo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(),"Button clicked !");
        String tag = (String) view.getTag();
        if (tag == null){
            Log.e(getClass().getSimpleName(), "Tag est null");
            return;
        }
        switch(Objects.requireNonNull(tag)) {
            case "10":
                Log.e(getClass().getSimpleName(), "Button Home clicked !");
                SecondActivity.this.finish();
                //startActivity(new Intent(this, SecondActivity.class));
                break;
            default:
                break;
        }
    }
}