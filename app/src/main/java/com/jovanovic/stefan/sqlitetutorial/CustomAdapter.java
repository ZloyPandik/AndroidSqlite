package com.jovanovic.stefan.sqlitetutorial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, name, gender, lang, ide;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList name, ArrayList gender, ArrayList lang,
                  ArrayList ide){
        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.lang = lang;
        this.ide = ide;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.gender_txt.setText(String.valueOf(gender.get(position)));
        holder.lang_txt.setText(String.valueOf(lang.get(position)));
        holder.ide_txt.setText(String.valueOf(ide.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("gender", String.valueOf(gender.get(position)));
                intent.putExtra("lang", String.valueOf(lang.get(position)));
                intent.putExtra("ide", String.valueOf(ide.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, name_txt, gender_txt, lang_txt, ide_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            name_txt = itemView.findViewById(R.id.name_txt);
            gender_txt = itemView.findViewById(R.id.gender_txt);
            lang_txt = itemView.findViewById(R.id.lang_txt);
            ide_txt = itemView.findViewById(R.id.ide_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
