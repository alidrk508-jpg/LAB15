package com.example.lab15;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab15.classes.Etudiant;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab15.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    private EditText etNom;
    private EditText etPrenom;
    private Button btnAjouter;

    private EditText etRechercheId;
    private Button btnRechercher;
    private Button btnSupprimer;
    private TextView tvResultat;
    
    private Button btnVoirListe;

    void viderChamps() {
        etNom.setText("");
        etPrenom.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EtudiantService etudiantService = new EtudiantService(this);

        etNom = findViewById(R.id.et_main_nom);
        etPrenom = findViewById(R.id.et_main_prenom);
        btnAjouter = findViewById(R.id.btn_main_ajouter);

        etRechercheId = findViewById(R.id.et_main_recherche_id);
        btnRechercher = findViewById(R.id.btn_main_rechercher);
        btnSupprimer = findViewById(R.id.btn_main_supprimer);
        tvResultat = findViewById(R.id.tv_main_resultat);
        
        btnVoirListe = findViewById(R.id.btn_main_voir_liste);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etudiantService.create(new Etudiant(etNom.getText().toString(), etPrenom.getText().toString()));
                viderChamps();

                for (Etudiant e : etudiantService.findAll()) {
                    Log.d("Etudiant", e.getId() + ": " + e.getNom() + " " + e.getPrenom());
                }

                Toast.makeText(MainActivity.this, "Étudiant ajouté", Toast.LENGTH_SHORT).show();
            }
        });

        btnRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = etRechercheId.getText().toString().trim();
                if (idText.isEmpty()) {
                    tvResultat.setText("");
                    Toast.makeText(MainActivity.this, "Saisir un id", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Etudiant e = etudiantService.findById(Integer.parseInt(idText));
                    if (e == null) {
                        tvResultat.setText("");
                        Toast.makeText(MainActivity.this, "Étudiant introuvable", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tvResultat.setText(e.getNom() + " " + e.getPrenom());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "ID invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idText = etRechercheId.getText().toString().trim();
                if (idText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Saisir un id", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Etudiant e = etudiantService.findById(Integer.parseInt(idText));
                    if (e == null) {
                        Toast.makeText(MainActivity.this, "Aucun étudiant à supprimer", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    etudiantService.delete(e);
                    tvResultat.setText("");
                    Toast.makeText(MainActivity.this, "Étudiant supprimé", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "ID invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        btnVoirListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListEtudiantActivity.class);
                startActivity(intent);
            }
        });
    }
}