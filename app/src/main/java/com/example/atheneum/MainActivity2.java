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

public class MainActivity2 extends AppCompatActivity {
    RecyclerView review;
    FirebaseFirestore db;
    useradapter adapter;
    Query query;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        review = findViewById(R.id.review);

        db = FirebaseFirestore.getInstance();
        query = db.collection("users");
        review.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<model2> options = new FirestoreRecyclerOptions.Builder<model2>()
                .setQuery(query, model2.class)
                .build();
        adapter = new useradapter(options);
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
                Intent intent2 = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.Total:
                Toast.makeText(this, "Total Due Selected", Toast.LENGTH_SHORT).show();
                Intent intent4 = new Intent(MainActivity2.this,MainActivity4.class);
                startActivity(intent4);
                return true;

            case R.id.Due:
                Toast.makeText(this, "Due Today Selected", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent3);

                return true;
            case R.id.Users:
                Toast.makeText(this, "Already in users", Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}