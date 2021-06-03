package com.example.atheneum;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter4 extends FirestoreRecyclerAdapter<model4, myadapter4.myviewholder> {
    public myadapter4(@NonNull FirestoreRecyclerOptions<model4> options) {
        super(options);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model4 model) {
        Date date_curr=new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String result=dateFormat.format(date_curr);

        String date=model.getDue();
        long fine = 0;

        try {
            Date date2 = dateFormat.parse(result);
            Date date1 = dateFormat.parse(date);
            long diff = date2.getTime() - date1.getTime();
            long days=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            fine=days*5;
            Log.d("days",String.valueOf(days));
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String[] arrSplit = result.split("-");
//        String[] arrSplit2 = date.split("-");
//        int curr_month=Integer.parseInt(arrSplit[1]);
//        int curr_date=Integer.parseInt(arrSplit[0]);
//        int curr_year=Integer.parseInt(arrSplit[2]);
//        int due_month=Integer.parseInt(arrSplit2[1]);
//        int due_date=Integer.parseInt(arrSplit2[0]);
//        int due_year=Integer.parseInt(arrSplit2[2]);

//        if(due_year==curr_year)
//        {
//            if(curr_month==due_month)
//            {
//                if(curr_date>due_date)
//                {
//                    fine=(curr_date-due_date)*5;
//                }
//            }
//            if(curr_month>due_month)
//            {
//                int diff_month=curr_month-due_month;
//                int total_days=30*diff_month;
//
//            }
//        }
        FirebaseFirestore dbroot;
        dbroot=FirebaseFirestore.getInstance();

        dbroot.collection("users").document(model.getEmail()).update("fine",String.valueOf(fine)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("msg","congrats");
                //dialogPlus.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("msg","sorry");
               // dialogPlus.dismiss();
            }
        });


            holder.name.setText("Name: " + model.getName());
            holder.sem.setText("Sem: " + model.getSem());
            holder.issue.setText("Usn: " + model.getUsn());
            holder.book.setText("Book: " + model.getBook());
            holder.due.setText("Author: " + model.getAuthor());
            holder.fine.setText("Fine: " + model.getFine());
            holder.usn.setText("Issue: " + model.getIssue());
            holder.author.setText("Due: " + model.getDue());
            holder.email.setText("Email: " + model.getEmail());


            Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("alert","alert msg");
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Do you wish to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseFirestore dbroot;
                        dbroot=FirebaseFirestore.getInstance();



                        dbroot.collection("users").document(model.getEmail()).delete();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent2)).setExpanded(true,1400
                        ).create();
                //dialogPlus.show();

                View myview=dialogPlus.getHolderView();
                EditText url=myview.findViewById(R.id.uimgurl);
                EditText name=myview.findViewById(R.id.uname);
                EditText author=myview.findViewById(R.id.uauthor);
                EditText fine=myview.findViewById(R.id.fine);
                EditText book=myview.findViewById(R.id.book);

                Button btn=myview.findViewById(R.id.usubmit);
                url.setText(model.getImage());
                name.setText(model.getName());
                author.setText(model.getAuthor());
                book.setText(model.getBook());
                fine.setText(model.getFine());
                dialogPlus.show();


                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> umap=new HashMap<>();
                        umap.put("url",url.getText().toString());
                        umap.put("name",name.getText().toString());
                        umap.put("author",author.getText().toString());
                        umap.put("fine",fine.getText().toString());
                        umap.put("book",book.getText().toString());
                        FirebaseFirestore dbroot;
                        dbroot=FirebaseFirestore.getInstance();



                        dbroot.collection("users").document(model.getEmail()).update(umap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("msg","congrats");
                                dialogPlus.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("msg","sorry");
                                dialogPlus.dismiss();
                            }
                        });


                    }
                });

            }
        });

        //
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow2,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name,usn,sem,book,author,fine,issue,due,email;
        ImageView edit,delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.name);
            author=(TextView)itemView.findViewById(R.id.author);
            fine=(TextView)itemView.findViewById(R.id.fine);
            book=(TextView)itemView.findViewById(R.id.book);
            usn=(TextView)itemView.findViewById(R.id.usn);
            sem=(TextView)itemView.findViewById(R.id.sem);
            email=(TextView)itemView.findViewById(R.id.email);
            issue=(TextView)itemView.findViewById(R.id.issue);
            due=(TextView)itemView.findViewById(R.id.due);
            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.delete);
        }
    }
}