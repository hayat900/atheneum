package com.example.atheneum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
    Button b1;
    EditText ed1,ed2;
    TextView textView5;
    TextView tx1;
    int counter = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        b1 = findViewById(R.id.button);
        ed1 = findViewById(R.id.email);
        ed2 = findViewById(R.id.password);
        tx1=findViewById(R.id.t1);

        textView5= findViewById(R.id.textView5);
        SpannableString content = new SpannableString( "Not logged in yet?Create a new account" ) ;
        content.setSpan( new UnderlineSpan() , 0 , content.length() , 0 ) ;
        textView5.setText(content) ;
        tx1.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Redirecting...",Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(admin_login.this,Retrive.class);


                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();

                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.RED);
                    counter--;
                    String cnt=Integer.toString(counter);
                    tx1.setText("You just have "+cnt+" chances left");

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });





        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(admin_login.this,adminregister.class);
                startActivity(intent);
            }
        });
    }
}