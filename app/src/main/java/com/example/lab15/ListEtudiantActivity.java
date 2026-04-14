package com.example.lab15;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab15.classes.Etudiant;
import com.example.lab15.service.EtudiantService;

import java.util.List;

public class ListEtudiantActivity extends AppCompatActivity {

    private TableLayout tlEtudiants;
    private EtudiantService etudiantService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_etudiant);

        etudiantService = new EtudiantService(this);
        tlEtudiants = findViewById(R.id.tl_list_etudiants);

        refreshTable();
    }

    private void refreshTable() {
        int childCount = tlEtudiants.getChildCount();
        if (childCount > 1) {
            tlEtudiants.removeViews(1, childCount - 1);
        }

        List<Etudiant> etudiants = etudiantService.findAll();
        for (Etudiant e : etudiants) {
            TableRow row = new TableRow(this);
            row.setPadding(0, 8, 0, 8);

            TextView tvId = new TextView(this);
            tvId.setText(String.valueOf(e.getId()));
            tvId.setPadding(8, 8, 8, 8);

            TextView tvNom = new TextView(this);
            tvNom.setText(e.getNom());
            tvNom.setPadding(8, 8, 8, 8);

            TextView tvPrenom = new TextView(this);
            tvPrenom.setText(e.getPrenom());
            tvPrenom.setPadding(8, 8, 8, 8);

            Button btnModifier = new Button(this);
            btnModifier.setText("Edit");
            btnModifier.setTextSize(10);
            btnModifier.setOnClickListener(v -> showUpdateDialog(e));

            Button btnSupprimer = new Button(this);
            btnSupprimer.setText("Del");
            btnSupprimer.setTextSize(10);
            btnSupprimer.setOnClickListener(v -> showDeleteConfirmation(e));

            row.addView(tvId);
            row.addView(tvNom);
            row.addView(tvPrenom);
            
            TableLayout actionLayout = new TableLayout(this);
            TableRow actionRow = new TableRow(this);
            actionRow.addView(btnModifier);
            actionRow.addView(btnSupprimer);
            actionLayout.addView(actionRow);
            
            row.addView(actionLayout);

            tlEtudiants.addView(row);
        }
    }

    private void showDeleteConfirmation(Etudiant e) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Voulez-vous vraiment supprimer l'étudiant " + e.getNom() + " " + e.getPrenom() + " ?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    etudiantService.delete(e);
                    Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show();
                    refreshTable();
                })
                .setNegativeButton("Non", null)
                .show();
    }

    private void showUpdateDialog(Etudiant e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Modifier Étudiant");

        View viewInflated = getLayoutInflater().inflate(R.layout.dialog_update_etudiant, null);
        final EditText inputNom = viewInflated.findViewById(R.id.et_dialog_nom);
        final EditText inputPrenom = viewInflated.findViewById(R.id.et_dialog_prenom);

        inputNom.setText(e.getNom());
        inputPrenom.setText(e.getPrenom());

        builder.setView(viewInflated);

        builder.setPositiveButton("Modifier", (dialog, which) -> {
            e.setNom(inputNom.getText().toString());
            e.setPrenom(inputPrenom.getText().toString());
            etudiantService.update(e);
            refreshTable();
            Toast.makeText(ListEtudiantActivity.this, "Mis à jour", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Annuler", (dialog, which) -> dialog.cancel());

        builder.show();
    }
}