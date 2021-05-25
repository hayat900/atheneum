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

public class MainActivity4 extends AppCompatActivity {
    RecyclerView review;
    FirebaseFirestore db;
    myadapter4 adapter;
    Query query;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        review = findViewById(R.id.review);

        db = FirebaseFirestore.getInstance();
        Date date=new Date();
        int year=date.getYear();
        int month=date.getMonth()+1;
        int day=date.getDay();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String result=dateFormat.format(date);

        Log.d("msg", result);
        String[] arrSplit = result.split("-");

        query = db.collection("users").whereLessThanOrEqualTo("duedate",result);
        review.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<model4> options = new FirestoreRecyclerOptions.Builder<model4>()
                .setQuery(query, model4.class)
                .build();
        adapter = new myadapter4(options);
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
                Toast.makeText(this, "Logout SElected", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.Total:
                Toast.makeText(this, "Already in total dues page", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Due:
                Toast.makeText(this, "Due Today Selected", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(MainActivity4.this,MainActivity3.class);
                startActivity(intent3);
            case R.id.Users:
                Toast.makeText(this, "Users Selected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity4.this,MainActivity2.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}