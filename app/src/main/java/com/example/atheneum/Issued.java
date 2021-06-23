package com.example.atheneum;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Issued extends AppCompatActivity {
    RecyclerView review;
    FirebaseFirestore db;
    issue_books adapter;
    Query query;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        review = findViewById(R.id.review);

        db = FirebaseFirestore.getInstance();
        Date date=new Date();
        int year=date.getYear();
        int month=date.getMonth()+1;
        int day=date.getDay();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String result=dateFormat.format(date);

        Log.d("msg", result);
        query = db.collection("requests");
        review.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<model3> options = new FirestoreRecyclerOptions.Builder<model3>()
                .setQuery(query, model3.class)
                .build();
        adapter = new issue_books(options);
        review.setAdapter(adapter);
        review.setHasFixedSize(true);
        add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), addnew.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Logout:
                Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Issued.this, MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.Total:
                Toast.makeText(this, "Total Due Selected", Toast.LENGTH_SHORT).show();

                Intent intent4 = new Intent(Issued.this,MainActivity4.class);
                startActivity(intent4);
                return true;

            case R.id.Due:
                Toast.makeText(this, "Due Today Selected", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(Issued.this,MainActivity3.class);
                startActivity(intent3);

                return true;
            case R.id.Users:
                Toast.makeText(this, "Users Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Issued.this,MainActivity2.class);
                startActivity(intent);
                return true;
            case R.id.Issue:
                Toast.makeText(this, "Already in issued today", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}