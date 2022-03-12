package com.example.manlib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ajoutlivre extends AppCompatActivity {

    ImageView img;
    EditText titre, auteur, description, prix_u, qte;
    RadioButton shirt, shoes, jewelry, sport, romance;
    Button ajouter, upload;

    StorageReference storageRef;
    private String image ="";
    public Uri imgurl;

//    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoutlivre);

        img = (ImageView) findViewById(R.id.img);

        titre = (EditText) findViewById(R.id.editTextTitre);
        auteur =  (EditText) findViewById(R.id.editTextAuteur);
        description =(EditText) findViewById(R.id.editTextDesc);
        prix_u =  (EditText) findViewById(R.id.editTextPrix);
        qte =   (EditText) findViewById(R.id.editTextQte);

        shoes= (RadioButton) findViewById(R.id.informatique);
        shirt = (RadioButton) findViewById(R.id.physics);
        jewelry= (RadioButton) findViewById(R.id.cuisine);
        sport = (RadioButton) findViewById(R.id.sports);
        romance = (RadioButton) findViewById(R.id.rommance);

        upload = (Button) findViewById(R.id.upload);
        ajouter = (Button) findViewById(R.id.add);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filechooser();
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ajouter(titre.getText().toString().trim(),prix_u.getText().toString());
            }
        });
    }

    public String getEntension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    private void Ajouter(String txt_titre, String txt_prix_u) {
//
//        String txt_titre = titre.getText().toString();
        String txt_auteur = auteur.getText().toString();
        String txt_description = description.getText().toString();
//        String txt_prix_u = prix_u.getText().toString();
        String txt_qte = qte.getText().toString();


        if(txt_titre.isEmpty() || txt_auteur.isEmpty() || txt_description.isEmpty() || txt_prix_u.isEmpty() || txt_qte.isEmpty()){
            Toast.makeText(ajoutlivre.this, "Veuiller remplir tous les champs svp...", Toast.LENGTH_LONG).show();
        } else {
            String s1 ="";
            if(shoes.equals(true)){
                s1 = "1";
            }
            if(shirt.equals(true)){
                s1 = "2";
            }
//            final ProgressDialog pd = new ProgressDialog(this);
//            pd.setMessage("Adding...");
//            pd.show();

            addProduct(txt_titre,txt_prix_u,s1);


        }

//        StorageReference ref = storageRef.child(System.currentTimeMillis()+"."+getEntension(imgurl));
//
//        UploadTask uploadTask = ref.putFile(imgurl);
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(ajoutlivre.this, "IMAGE UPLOADED...", Toast.LENGTH_LONG).show();
//            }
//        });
    }
    public  void addImage(int id,String image) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Connection...");
        pd.show();
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        String imagestring= "imagestring";
        String productid= "productid";


        String ob ="{"+
                '"'+imagestring+'"' +':'+'"'+image+'"'+','+

                '"'+productid+'"'+':'+ '{'+'"'+productid+'"'+':'+id+'}'+'}';



        final String[] end1 = {""};
        try {
            jsonObject.put("productid", id);
            jsonObject.put("imagestring", image);


        } catch (JSONException e) {
            e.printStackTrace();

        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // put your json here
        RequestBody body = RequestBody.create(JSON, ob);
        Request request = new Request.Builder()
                .url("http://172.20.10.3:8080/E-commerce/webresources/imagecontroller/addimage")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                end1[0]= "no";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(ajoutlivre.this)
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

                    ajoutlivre.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(ajoutlivre.this,startpage.class);
                            startActivity(intent);

                            //   t.setText(pwd);
                            Log.d("response", myResponse);
                            pd.dismiss();
//                            progress.hide();
                        }
                    });
                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(ajoutlivre.this)
                                    .setTitle("Error Occured")
                                    .setMessage(ob)

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


            }
        });

    }

    public  void addProduct(String name1,String price,String category) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Connection...");
        pd.show();
        OkHttpClient client = new OkHttpClient();
        JSONObject jsonObject = new JSONObject();
        String name = "name";
        String rate = "rate";
        String adminid = "adminid";
        String categoryid = "categoryid";


        String ob ="{"+
                '"'+name+'"' +':'+'"'+name1+'"'+','+
                '"'+rate+'"' +':'+'"'+price+'"'+','+
                '"'+adminid+'"'+':'+ '{'+'"'+adminid+'"'+':'+1+'}'+','+
                '"'+categoryid+'"'+':'+ '{'+'"'+categoryid+'"'+':'+1+'}'+'}';





//                "{'name':'sdf',"+
//                        "'rate':"+2+","
////                + "'rate':'" +price+ "',"
//
//                        + "'adminid':"
//                        + "[{'adminid':'" + 1 + "'}],"
//                        + "'categoryid':"
//                        + "[{'categoryid':'"+ 1 +"'}]}"
//                ;
        final String[] end1 = {""};
        try {
            jsonObject.put("name", name);
            jsonObject.put("rate", price);
            jsonObject.put("categoryid", jsonObject.put("categoryid",1));
            jsonObject.put("adminid", jsonObject.put("adminid",1));


        } catch (JSONException e) {
            e.printStackTrace();

        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        // put your json here
        RequestBody body = RequestBody.create(JSON, ob);
        Request request = new Request.Builder()
                .url("http://172.20.10.3:8080/E-commerce/webresources/productcontroller/addproduct")
                .post(body)
                .build();

        System.out.println(ob);

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                end1[0]= "no";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(ajoutlivre.this)
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

                    ajoutlivre.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            addImage(11,image);


                            //   t.setText(pwd);
                            Log.d("response", myResponse);
//                            pd.dismiss();
//                            progress.hide();
                        }
                    });
                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new AlertDialog.Builder(ajoutlivre.this)
                                    .setTitle("Error Occured")
                                    .setMessage(ob)

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


            }
        });

    }



    private void Filechooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent, 100);

//        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//        photoPickerIntent.setType("image/*");
//        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor =getContentResolver().query(uri, projection, null,null,null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == RESULT_OK){
////            imgurl = data.getData();
////            img.setImageURI(imgurl);


        try {
            final Uri imageUri = data.getData();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

            img.setImageBitmap(selectedImage);
           image= getPath(imageUri);





        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(ajoutlivre.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

//    }else {
//        Toast.makeText(ajoutlivre.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
//    }
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

}