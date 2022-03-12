package com.example.manlib;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class vente extends AppCompatActivity {
    EditText NomClient, TitreL, Prix, Qte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vente);

        NomClient = (EditText) findViewById(R.id.editTextNC);
        TitreL = (EditText) findViewById(R.id.editTextTL);
        Prix = (EditText) findViewById(R.id.editTextPrix);
        Qte = (EditText) findViewById(R.id.editTextQte);

    }

    public void back(View view) {
        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_in, R.anim.stay);
        finish();
    }

    public void vendre(View view) {
        String txt_NomClient = NomClient.getText().toString();
        String txt_TitreL = TitreL.getText().toString();
        String txt_Prix = Prix.getText().toString();
        String txt_Qte = Qte.getText().toString();

        if(txt_NomClient.isEmpty() || txt_Prix.isEmpty() || txt_Qte.isEmpty() || txt_TitreL.isEmpty()){
            Toast.makeText(vente.this, "Remplire tout les champs SVP...",Toast.LENGTH_LONG).show();
        }
        else {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Enregistrement de la vente...");
            pd.show();
        }
    }
}