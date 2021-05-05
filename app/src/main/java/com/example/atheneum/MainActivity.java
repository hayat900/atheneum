package com.example.atheneum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] Options = {"Admin", "User"};
                final AlertDialog.Builder window;
                window = new AlertDialog.Builder(MainActivity.this);
                window.setTitle("Make a Selection");
                window.setItems(Options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            Toast.makeText(MainActivity.this, "you selected admin", Toast.LENGTH_SHORT).show();
                            //first option clicked, do this...
                            Intent intent = new Intent(MainActivity.this,admin_login.class);
                            startActivity(intent);

                        } if(which == 1){
                            Toast.makeText(MainActivity.this, "you selected user", Toast.LENGTH_SHORT).show();
                            //second option clicked, do this...
                            Intent intent = new Intent(MainActivity.this,user_login.class);
                            startActivity(intent);

                        }
                    }
                });

                window.show();
            }
        });
    }
}