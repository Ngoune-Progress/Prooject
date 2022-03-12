package com.example.manlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    EditText email, mdp;
    Button login;
    private final OkHttpClient httpClient = new OkHttpClient();
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        //for changing status bar icon color
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }


        setContentView(R.layout.activity_main);

        email =  findViewById(R.id.editTextEmail);
        mdp = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.LoginButton);
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String txt_email = email.getText().toString().trim();
            String txt_mdp = mdp.getText().toString().trim();

            if(txt_email.isEmpty()|| txt_mdp.isEmpty()){
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Error")
                        .setMessage("Fill all the fields"+txt_email)

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
            } else {
                connexion(txt_email, txt_mdp);
                }


        }
    });

    }

    public void onLoginclick(View view) {
        Intent intent = new Intent(this, createlog.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(!drawerLayout.isDrawerVisible(GravityCompat.START)){
            finish();
        }
        finish();
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == event.KEYCODE_BACK){
//            finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
//
//    public void login(View view) {
//        String txt_email = email.getText().toString();
//        String txt_mdp = mdp.getText().toString();
//
//        if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_mdp)){
//            Toast.makeText(MainActivity.this, "Veuiller remplir tous les champs svp...", Toast.LENGTH_LONG).show();
//        } else {
//            connexion(txt_email, txt_mdp);
//        }
//    }

    private void connexion(String email, String mdp) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Connection...");
        pd.show();


        Request request = new Request.Builder()
                .url("http://172.20.10.3:8080/E-commerce/webresources/customercontroller/get/"+email)
//                .addHeader("custom-key", "mkyong")  // add request headers
//                .addHeader("User-Agent", "OkHttp Bot")
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Error Occured")
//                                .setMessage("Please ensure you have a good connection peey")
                                .setMessage(e.getMessage())

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
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
                        MainActivity.this.runOnUiThread(new Runnable() {
//                            String r = response.body().string();
//                            JSONObject json = new JSONObject(r);

                            @Override
                            public void run() {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Verify")
                                        .setMessage("email or password incorrect")

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
                    else {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            String r = response.body().string();
                            JSONObject json = new JSONObject(r);

                            @Override
                            public void run() {

                                try {
                                    if (email.equals(json.getString("login")) && mdp.equals(json.getString("password"))) {

                                        Intent intent = new Intent(MainActivity.this, startpage.class);
                                        startActivity(intent);
                                    } else {
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setTitle("Verify")
                                                .setMessage("email or password incorrect")

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


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    pd.dismiss();

                                }

//

                                // Get response body

                            }
                        });
                    }
                    // Get response headers


                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.dismiss();

                }
            }
        });

    }


    public void fogot(View view) {
        Intent intent = new Intent(this, forgotPass.class);
        startActivity(intent);
    }
}