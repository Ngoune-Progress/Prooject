package com.example.manlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class forgotPass extends AppCompatActivity {
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        email = (EditText) findViewById(R.id.editTextName);

    }

    public void reset(View view) {
        String txt_email = email.getText().toString();
        if(TextUtils.isEmpty(txt_email)){
            Toast.makeText(forgotPass.this, "Veuiller remplir le champs svp...", Toast.LENGTH_LONG).show();
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Voulez vraiment reinitialisé? ")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            sendEmail(txt_email);
                        }
                    })
                    .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
//            sendEmail(txt_email);
        }
    }


    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }
}