package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_utilisateur extends AppCompatActivity {

    Button buttonEditUtilisateur1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur);
        buttonEditUtilisateur1 = findViewById(R.id.buttonEditUtilisateur1);

        buttonEditUtilisateur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUtilisateur1();
            }
        });
    }

    private void editUtilisateur1() {
        Intent EditUtilisateur1Intent = new Intent(this, activity_modification_utilisateur.class);
        startActivity(EditUtilisateur1Intent);
    }


}