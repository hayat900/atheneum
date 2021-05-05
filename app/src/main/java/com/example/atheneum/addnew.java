package com.example.atheneum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class addnew extends AppCompatActivity {
    EditText name,author,image,qty;
    Button btn,back;
    FirebaseFirestore dbroot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew);
        name=findViewById(R.id.add_name);
        author=findViewById(R.id.add_author);
        qty=findViewById(R.id.add_qty);
        image=findViewById(R.id.add_purl);
        btn=findViewById(R.id.add_submit);
        back=findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Retrive.class));
                finish();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert();
            }
        });
    }
    private void processinsert() {
        dbroot= FirebaseFirestore.getInstance();
        Map<String,String> items=new HashMap<>();
        items.put("name",name.getText().toString().trim());
        items.put("author",author.getText().toString().trim());
        items.put("qty",qty.getText().toString().trim());
        items.put("image",image.getText().toString().trim());
        Date date=new Date();
        items.put("timestamp", String.valueOf(date.getTime()));
        Log.d("msg", String.valueOf(FieldValue.serverTimestamp()));

        dbroot.collection("books").document(String.valueOf(date.getTime())).set(items)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        author.setText("");
                        qty.setText("");
                        image.setText("");

                        Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
                        //Toast.makeText(this,"Succcessful",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"not",Toast.LENGTH_LONG).show();
            }
        });



    }
}