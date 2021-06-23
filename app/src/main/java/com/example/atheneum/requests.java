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

public class requests extends AppCompatActivity {
    RecyclerView review;
    FirebaseFirestore db;
    requestadapter adapter;
    Query query;
    FloatingActionButton add;
    String newString=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        review = findViewById(R.id.review);

        db = FirebaseFirestore.getInstance();

        Bundle extras;


        if (savedInstanceState == null)

        {

            /*fetching extra data passed with intents in a Bundle type variable*/



            extras = getIntent().getExtras();
            /* fetching the string passed with intent using ‘extras’*/
            if(extras == null)

                newString = null;
            else

                newString = extras.getString("id");

        }




        Log.d("msg", newString);
        query = db.collection("requests").whereEqualTo("email",newString);
        review.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<model3> options = new FirestoreRecyclerOptions.Builder<model3>()
                .setQuery(query, model3.class)
                .build();
        adapter = new requestadapter(options);
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
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.id2:
                Toast.makeText(this, "Logout Selected", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(requests.this, MainActivity.class);
                startActivity(intent2);
                return true;
            case R.id.id3:
                Toast.makeText(this, "All books", Toast.LENGTH_SHORT).show();

                Intent intent4 = new Intent(requests.this,UserHome.class);
                intent4.putExtra("id",newString);
                startActivity(intent4);
                return true;
            case R.id.requests:
                Toast.makeText(this, "Already in my requests", Toast.LENGTH_SHORT).show();


                return true;





            case R.id.id1:
                Toast.makeText(this, newString, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(requests.this, list.class);
                String strName = null;
                Log.d("id",newString);
                intent.putExtra("id", newString);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}