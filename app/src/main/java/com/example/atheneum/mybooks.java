package com.example.atheneum;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


class mybooks extends FirestoreRecyclerAdapter<model5,mybooks.myviewholder> {
    public mybooks(@NonNull FirestoreRecyclerOptions<model5> options) {
        super(options);

    }


    @Override
    protected void onBindViewHolder(@NonNull mybooks.myviewholder holder, int position, @NonNull model5 model) {
        holder.name.setText(model.getName());
        holder.author.setText("Author : " + model.getAuthor());
        holder.due.setText("Due Date : " + model.getDuedate());

        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);


        holder.ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db;
                db = FirebaseFirestore.getInstance();

                //deleting the book

                db.collection("users").document(model.getName()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(v.getContext(), "Returned successfully!!!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(v.getContext(), "Failed to return the book!!!", Toast.LENGTH_LONG).show();

                            }
                        });

                //add the quantity to book list

//                int q = Integer.parseInt(document.getString("qty"));
//                q = q + 1;
//                //document.getString("qty")=String.valueOf(q);
//                Map<String, Object> map = new HashMap<>();
//                map.put("qty", String.valueOf(q));

                db.collection("books")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        if (document.getString("name").equals(model.getName())) {

                                            String res = document.getId().toString();
                                            int q = Integer.parseInt(document.getString("qty"));
                                            q = q + 1;
                                            Map<String, Object> map = new HashMap<>();
                                            map.put("qty", String.valueOf(q));
                                            db.collection("books").document(res).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("msg", "congrats");

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.d("msg", "sorry");

                                                }
                                            });

                                        }
                                    }
                                } else {
                                    Log.w("msg", "Error getting documents.", task.getException());
                                }
                            }
                        });
            }
        });
    }

    @NonNull
    @Override
    public mybooks.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_mybooks,parent,false);
        return new mybooks.myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name,author,due;
        Button ret;
        //ImageView edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.bname);
            due=(TextView)itemView.findViewById(R.id.due);
            author=(TextView)itemView.findViewById(R.id.auth);
            ret=(Button)itemView.findViewById(R.id.ret);
            //delete=(ImageView)itemView.findViewById(R.id.delete);
        }
    }
}
