package com.example.atheneum;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Date;

public class MainActivity3 extends AppCompatActivity {
    RecyclerView review;
    FirebaseFirestore db;
    myadapter3 adapter;
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
        int month=date.getMonth();
        int day=date.getDay();
        String result=day+"-"+month+"-"+year;
        query = db.collection("users").whereEqualTo("due",result);
        review.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<model3> options = new FirestoreRecyclerOptions.Builder<model3>()
                .setQuery(query, model3.class)
                .build();
        adapter = new myadapter3(options);
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
                Toast.makeText(this, "LOgout SElected", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.Total:
                Toast.makeText(this, "Total Due SElected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Due:
                Toast.makeText(this, "Due Today SElected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Users:
                Toast.makeText(this, "Users SElected", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}