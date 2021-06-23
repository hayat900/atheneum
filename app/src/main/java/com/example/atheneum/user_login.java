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

import com.firebase.ui.auth.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class user_login extends AppCompatActivity {
    Button log,newu;
    EditText name,psd;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        name=findViewById(R.id.mail);
        psd=findViewById(R.id.psd);
        log=findViewById(R.id.login);
        newu=findViewById(R.id.newuser);
        db= FirebaseFirestore.getInstance();


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().equals(""))
                    Toast.makeText(user_login.this,"Username is required!!!",Toast.LENGTH_LONG).show();

                else if(psd.getText().toString().equals(""))
                    Toast.makeText(user_login.this,"Password is required!!!",Toast.LENGTH_LONG).show();

                else
                    login();
            }
        });

        newu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DateFormat df=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//                Date date=new Date();
//                String d=df.format(date);
//                Toast.makeText(User.this,"Time is :"+d,Toast.LENGTH_LONG).show();
                register();
            }
        });

    }

    private void login()
    {


        //DocumentReference doc;
        db.collection("students")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count=0;
                            for (QueryDocumentSnapshot document : task.getResult())
                            //String res = document.getId().toString()
                            {
                                if (document.getId().equals(name.getText().toString())) {
                                    //Log.d("val", document.getId() + "==>" + document.getString("Name"));
                                    if (document.getString("password").equals(psd.getText().toString())) {
                                        count = 1;
                                        Intent in = new Intent(getApplicationContext(), UserHome.class);
                                        in.putExtra("id",name.getText().toString());
                                        startActivity(in);



                                    }
                                }
                            }
                            if(count==0)
                                Toast.makeText(user_login.this,"Failed to Login...Wrong credentials",Toast.LENGTH_LONG).show();

                        }

                        else {
                            Log.d("Logger","get failed with",task.getException());
                            //Toast.makeText(User.this,"Failed to Login...Wrong credentials",Toast.LENGTH_LONG).show();

                        }

                    }
                });




    }

    private void register()
    {
        Intent in=new Intent(this, registeruser.class);
        startActivity(in);
    }
    }
