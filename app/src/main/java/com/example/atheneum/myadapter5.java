package com.example.atheneum;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;



public class myadapter5 extends FirestoreRecyclerAdapter<model5,myadapter5.myviewholder> {
    public myadapter5(@NonNull FirestoreRecyclerOptions<model5> options) {
        super(options);

    }





    @Override
    protected void onBindViewHolder(@NonNull myadapter5.myviewholder holder, int position, @NonNull model5 model) {
        holder.name.setText("Book:"+model.getName());
        holder.author.setText("Author : "+model.getAuthor());
        holder.qty.setText("Qty : "+model.getQty());

        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);




        holder.issue.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override


            public void onClick(View v) {

                if (model.getQty().equals("0"))
                    Toast.makeText(v.getContext(), "Sorry!!! No stock available", Toast.LENGTH_LONG).show();

                else {

                    final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                            .setContentHolder(new ViewHolder(R.layout.activity_issuebook)).setGravity(Gravity.CENTER)
                            .create();
                    //dialogPlus.show();
                    View myview = dialogPlus.getHolderView();
                    CircleImageView image = myview.findViewById(R.id.img1);
                    TextView bname = myview.findViewById(R.id.bkname);
                    TextView author = myview.findViewById(R.id.author);
                    TextView qty = myview.findViewById(R.id.qty);

                    EditText uname = myview.findViewById(R.id.uname);
                    EditText mail = myview.findViewById(R.id.mail);
                    EditText usn = myview.findViewById(R.id.usn);
                    EditText sem = myview.findViewById(R.id.sem);

                    Button btn = myview.findViewById(R.id.usubmit);
                    Glide.with(image.getContext()).load(model.getImage()).into(image);
                    bname.setText(model.getName());
                    author.setText(model.getAuthor());
                    qty.setText(model.getQty());
                    String url;
                    String fine = "0";
                    url = model.getImage();

                    dialogPlus.show();


                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            if (uname.getText().toString().equals(""))
                                Toast.makeText(v.getContext(), "Username is required!!!", Toast.LENGTH_LONG).show();

                            else if (usn.getText().toString().equals(""))
                                Toast.makeText(v.getContext(), "USN is required!!!", Toast.LENGTH_LONG).show();

                            else if (sem.getText().toString().equals(""))
                                Toast.makeText(v.getContext(), "Semester is required!!!", Toast.LENGTH_LONG).show();

                            else if (mail.getText().toString().equals(""))
                                Toast.makeText(v.getContext(), "Email is required!!!", Toast.LENGTH_LONG).show();


                            else {

                                FirebaseFirestore db;
                                db = FirebaseFirestore.getInstance();

                                db.collection("books")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    int count = 0;
                                                    for (QueryDocumentSnapshot document : task.getResult())
                                                    //String res = document.getId().toString()
                                                    {
                                                        if (document.getString("name").equals(bname.getText().toString())) {
//                                                            if (document.getString("qty").equals("0")) {
//                                                                Toast.makeText(v.getContext(), "Sorry!!! No stock availiable", Toast.LENGTH_LONG).show();
//                                                                break;

                                                            //}
                                                            //else {
                                                            count = 1;
                                                            //addbook();
                                                            String res = document.getId().toString();
                                                            int q = Integer.parseInt(document.getString("qty"));
                                                            q = q - 1;
                                                            //document.getString("qty")=String.valueOf(q);
                                                            Map<String, Object> map = new HashMap<>();
                                                            map.put("qty", String.valueOf(q));
                                                            //FirebaseFirestore db1;
                                                            //db = FirebaseFirestore.getInstance();

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


                                                            // }
                                                        }

                                                    }

                                                    if (count == 1) {


                                                        Calendar cldr = Calendar.getInstance();
                                                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                                        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
                                                        String d1 = df.format(cldr.getTime());

                                                        //Toast.makeText(Issuebook.this,"Time is :"+d1,Toast.LENGTH_SHORT).show();
                                                        String d2 = "";
                                                        String d3 = "";
                                                        cldr.add(Calendar.DAY_OF_YEAR, 15);
                                                        try {
                                                            d2 = df.format(new Date(df.parse(df.format(cldr.getTime())).getTime()));
                                                            d3 = df1.format(new Date(df1.parse(df1.format(cldr.getTime())).getTime()));
                                                            //Toast.makeText(Issuebook.this,"Time is :"+d2,Toast.LENGTH_LONG).show();
                                                        } catch (ParseException e) {
                                                            e.printStackTrace();
                                                        }

                                                        FirebaseFirestore db2;
                                                        db2 = FirebaseFirestore.getInstance();


                                                        Map<String, Object> umap = new HashMap<>();
                                                        umap.put("name", uname.getText().toString());
                                                        umap.put("usn", usn.getText().toString());
                                                        umap.put("sem", sem.getText().toString());
                                                        umap.put("email", mail.getText().toString());

                                                        umap.put("book", bname.getText().toString());
                                                        umap.put("author", author.getText().toString());
                                                        //umap.put("qty", qty.getText().toString());
                                                        umap.put("image", url);
                                                        umap.put("issue", d1);




                                                        db2.collection("requests").document(mail.getText().toString()).set(umap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Log.d("msg", "congrats");
                                                                Toast.makeText(v.getContext(), "Successfully issued", Toast.LENGTH_LONG).show();
                                                                dialogPlus.dismiss();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Log.d("msg", "sorry");
                                                                dialogPlus.dismiss();
                                                            }
                                                        });

                                                    }


                                                } else {
                                                    //Log.d("Logger","Failed to issue the book!!!",task.getException());
                                                    Toast.makeText(v.getContext(), "Failed to Issue the book", Toast.LENGTH_LONG).show();

                                                }

                                            }
                                        });
                            }

                        }

                    });

                }
            }
        });



    }







    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow3,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name,author,qty;
        Button issue;
        //ImageView edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.bname);
            qty=(TextView)itemView.findViewById(R.id.qun);
            author=(TextView)itemView.findViewById(R.id.auth);
            issue=(Button)itemView.findViewById(R.id.issue);
            //delete=(ImageView)itemView.findViewById(R.id.delete);
        }
    }
}



























