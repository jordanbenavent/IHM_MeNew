package ihm.menew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityUtilisateur extends AppCompatActivity {

    Button buttonEditUtilisateur1;
    Button buttonAddUtilisateur;


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

        buttonAddUtilisateur = findViewById(R.id.ButtonAddUtilisateur);
        buttonAddUtilisateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUtilisateur();
            }
        });
    }

    private void editUtilisateur1() {
        Intent EditUtilisateur1Intent = new Intent(this, ActivityModificationUtilisateur.class);
        startActivity(EditUtilisateur1Intent);
    }

    private void addUtilisateur() {
        Intent AddUtilisateurIntent = new Intent(this, ActivityAddUtilisateur.class);
        startActivity(AddUtilisateurIntent);
    }
}