package com.example.atheneum;




import androidx.annotation.NonNull;
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
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class issued extends AppCompatActivity {
    RecyclerView review;
    FirebaseFirestore db ;
    myadapter3 adapter;
    Query query;
    String userid;

    //Button b;
    //FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        review = findViewById(R.id.review);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String j="";
        if(b!=null)
        {
            j =(String) b.get("id");
            Log.d("id2",j);

        }
        //userid=getIntent().getStringExtra("Log_id");

        // add=findViewById(R.id.add);

        db = FirebaseFirestore.getInstance();
        //query = db.collection("bookissue");


        query=db.collection("users").whereEqualTo("email",j);
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                if(document.getId().equals(userid)) {
//                                    query.get().document;
//                                }
//                                //Log.d("msg", document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d("msg", "Error getting documents.", task.getException());
//                        }
//                    }
//                });

        review.setLayoutManager(new LinearLayoutManager(this));
        FirestoreRecyclerOptions<model3> options = new FirestoreRecyclerOptions.Builder<model3>()
                .setQuery(query, model3.class)
                .build();

        adapter = new myadapter3(options);
        review.setAdapter(adapter);
        review.setHasFixedSize(true);


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