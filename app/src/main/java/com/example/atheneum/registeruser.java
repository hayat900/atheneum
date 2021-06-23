package com.example.atheneum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registeruser extends AppCompatActivity {
    EditText name,pwd,email;
    Button submit;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        name=findViewById(R.id.uname);
        pwd=findViewById(R.id.psd);
        email=findViewById(R.id.mail);

        submit=findViewById(R.id.reg);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().equals(""))
                    Toast.makeText(registeruser.this,"Username is required!!!",Toast.LENGTH_LONG).show();

                else if(pwd.getText().toString().equals(""))
                    Toast.makeText(registeruser.this,"Password is required!!!",Toast.LENGTH_LONG).show();

                else if(email.getText().toString().equals(""))
                    Toast.makeText(registeruser.this,"Email is required!!!",Toast.LENGTH_LONG).show();
                else
                    add();
            }
        });





    }

    private void add() {

        Map<String,Object> map=new HashMap<>();
        db=FirebaseFirestore .getInstance();

        map.put("email",email.getText().toString());
        map.put("password",pwd.getText().toString());


        //FirebaseFirestore db=FirebaseFirestore.getInstance();
        // ApiFuture<DocumentReference> addeddoc=db.collection("users").add(map);

//        FirebaseFirestore.getInstance().getReference().child("users").push()
//                .setValue(map)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        name.setText("");
//                        email.setText("");
//                        password.setText("");
//                        Toast.makeText(getApplicationContext(),"Inserted succsessfully",Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        name.setText("");
//                        email.setText("");
//                        password.setText("");
//                        Toast.makeText(getApplicationContext(),"Could not Insert",Toast.LENGTH_LONG).show();
//                    }
//                });

        db.collection("students").document(email.getText().toString()).set(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        email.setText("");
                        pwd.setText("");

                        Toast.makeText(getApplicationContext(),"Successfully Registered!!!!",Toast.LENGTH_LONG).show();
                        Intent in=new Intent(registeruser.this, user_login.class);
                        startActivity(in);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failed to register....",Toast.LENGTH_LONG).show();
                    }
                });



    }
}
