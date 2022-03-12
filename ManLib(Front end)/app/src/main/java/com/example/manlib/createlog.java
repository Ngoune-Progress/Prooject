package com.example.manlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class createlog extends AppCompatActivity {
    EditText nomLib, email, tel, mdp;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlog);


        nomLib = (EditText) findViewById(R.id.editTextName);
        email = (EditText) findViewById(R.id.editTextName2);
        tel = (EditText) findViewById(R.id.editTextName3);
        mdp = (EditText) findViewById(R.id.editTextPassword);
        signup = (Button) findViewById(R.id.LoginButton);
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.stay);
        finish();
    }

    public void signup(View view) {
        String txt_nomLib = nomLib.getText().toString().trim();
        String txt_email = email.getText().toString().trim();
        String txt_tel = tel.getText().toString().trim();
        String txt_mdp = mdp.getText().toString().trim();

        if(txt_nomLib.isEmpty() || txt_email.isEmpty() || txt_tel.isEmpty() || txt_mdp.isEmpty()){
            new AlertDialog.Builder(createlog.this)
                    .setTitle("Error Occured")
                    .setMessage("Please fill all the fields")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else if(txt_mdp.length() < 6){
            Toast.makeText(createlog.this, "password to short!", Toast.LENGTH_LONG).show();
        } else {
            sendRegister(txt_nomLib,txt_email, txt_mdp, txt_tel);
        }
    }


    public  void sendRegister(String name,String email, String pwd, String addresse) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Connection...");
        pd.show();
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        final String[] end1 = {""};
        try {
            jsonObject.put("password", pwd);
            jsonObject.put("login", email);
            jsonObject.put("addresse", addresse);
            jsonObject.put("creditcardinfo", "");
            jsonObject.put("status", "Active");

        } catch (JSONException e) {
            e.printStackTrace();

        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // put your json here
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url("http://172.20.10.3:8080/E-commerce/webresources/customercontroller/addcustomer")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                end1[0]= "no";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(createlog.this)
                                .setTitle("Error Occured")
                                .setMessage("Please ensure you have a good connection ")

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Continue with delete operation
                                    }
                                })

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        pd.dismiss();
                    }
                });

            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    end1[0] = "ok";

                    createlog.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(createlog.this,MainActivity.class);
                            startActivity(intent);

                            //   t.setText(pwd);
                            Log.d("response", myResponse);
                            pd.dismiss();
//                            progress.hide();
                        }
                    });
                }


            }
        });

    }


}