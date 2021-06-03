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
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class UserHome extends AppCompatActivity {
    RecyclerView review;
    FirebaseFirestore db ;
    myadapter5 adapter;
    Query query;
    String logid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        review = findViewById(R.id.review);
        logid=getIntent().getStringExtra("Login_id");


        // add=findViewById(R.id.add);

        db = FirebaseFirestore.getInstance();
        query = db.collection("books");
        review.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<model5> options = new FirestoreRecyclerOptions.Builder<model5>()
                .setQuery(query, model5.class)
                .build();

        adapter = new myadapter5(options);
        review.setAdapter(adapter);
        review.setHasFixedSize(true);

    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu2,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.id1:
            {
                Toast.makeText(this, logid, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(UserHome.this, list.class);
                String strName = null;
                Log.d("id",logid);
                intent2.putExtra("id", logid);
                startActivity(intent2);
                return true;
            }
            case R.id.id2:
            {


                startActivity(new Intent(this,MainActivity.class));
                Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
                return true;

            }
            case R.id.id3:
                Toast.makeText(this, "Already in all books", Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
}

