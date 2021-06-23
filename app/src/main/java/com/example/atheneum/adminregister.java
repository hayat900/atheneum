package com.example.atheneum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class adminregister extends AppCompatActivity {
    TextView textView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminregister);
        textView5=findViewById(R.id.textView5);
        SpannableString content = new SpannableString( "Back to login" ) ;
        content.setSpan( new UnderlineSpan() , 0 , content.length() , 0 ) ;
        textView5.setText(content) ;
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(adminregister.this,admin_login.class);
                startActivity(intent);
            }
        });
    }
}