package com.example.petscoffee.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.petsAction.Eater;
import com.example.petscoffee.petsAction.Washer;
import com.example.petscoffee.model.CoffeeShop;
import com.example.petscoffee.model.goods.Goods;
import com.example.petscoffee.model.pets.Pets;


import java.util.List;
import java.util.Timer;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> implements View.OnClickListener{
    private List<Pets> pets;
    private List<Goods> bag;
    private Pets pet;//本item的宠物对象
    private Context context;//父类context(应该传入 onCreateViewHolder中的parent的context)
    private ViewHolder petsViewHolder;//为洗澡，吃饭之后能改变属性值的textView

    public PetsAdapter(CoffeeShop coffeeShop) {
        this.pets = coffeeShop.getPets();
        this.bag = coffeeShop.getBag();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView petsImage;
        TextView petsName;
        TextView petsHp;
        TextView petsHunger;
        TextView petsCleanliness;
        TextView petsMood;
        TextView petsLoveliness;
        TextView petsSp;
        Button wash;
        Button eat;

        public ViewHolder(View view) {
            super(view);
            petsImage = view.findViewById(R.id.pets_image);
            petsName = view.findViewById(R.id.pets_name);
            petsHp = view.findViewById(R.id.pets_hp);
            petsHunger = view.findViewById(R.id.pets_hunger);
            petsCleanliness = view.findViewById(R.id.pets_cleanliness);
            petsMood = view.findViewById(R.id.pets_mood);
            petsLoveliness = view.findViewById(R.id.pets_loveliness);
            petsSp = view.findViewById(R.id.pets_sp);
            wash = itemView.findViewById(R.id.pets_button_wash);
            eat = itemView.findViewById(R.id.pets_button_eat);
        }

        public TextView getPetsCleanliness() {//为洗澡之后能获得该textView，
            // 以改变宠物界面的清洁度数值
            return petsCleanliness;
        }

        public TextView getPetsHunger(){//为投喂之后能获得该textView，
            // 以改变宠物界面的饱食度数值
            return petsHunger;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pets, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        context = parent.getContext();//为传到washer的context和viewHolder
        this.petsViewHolder = viewHolder;
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pet = pets.get(position);
        holder.petsImage.setImageResource(pet.getImageId());
        holder.petsName.setText("名字 ：" + pet.getName());
        holder.petsHp.setText("生命值：" + String.valueOf(pet.getHp()));
        holder.petsHunger.setText("饥饿度：" + String.valueOf(pet.getHunger()));
        holder.petsCleanliness.setText("清洁度：" + String.valueOf(pet.getCleanliness()));
        holder.petsMood.setText("心情值：" + String.valueOf(pet.getMood()));
        holder.petsLoveliness.setText("可爱度：" + String.valueOf(pet.getLoveliness()));
        holder.petsSp.setText("特殊值：" + String.valueOf(pet.getSp()));
        holder.eat.setOnClickListener(this);
        holder.wash.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pets_button_wash:
                new Timer().schedule(new Washer(context,pet,petsViewHolder),1000,1000);
                break;
            case R.id.pets_button_eat:
                new Eater(context,petsViewHolder,bag,pet);
                break;
        }
    }
}
