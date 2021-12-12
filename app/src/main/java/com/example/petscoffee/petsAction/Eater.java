package com.example.petscoffee.petsAction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.petscoffee.R;
import com.example.petscoffee.adapters.PetsAdapter;
import com.example.petscoffee.model.Bag;
import com.example.petscoffee.model.goods.Goods;
import com.example.petscoffee.model.pets.Pets;


import java.util.List;

public class Eater {// 实现多宠物同时开饭
    private Context context;
    private Pets pet;
    private PetsAdapter.ViewHolder viewHolder;
    private List<Goods> bag;

    public Eater(Context context, PetsAdapter.ViewHolder viewHolder,List<Goods> bag,Pets pet) {
        this.context = context;
        this.pet = pet;
        this.bag = bag;
        this.viewHolder = viewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_pets_eat,null);
        EditText editText = view.findViewById(R.id.eat_edit);
        AlertDialog.Builder alert =  new AlertDialog.Builder(context);
        if(Bag.search(bag,"Foods")!=-1){
            //如果背包里有食物
            alert.setTitle("投 喂").setMessage("剩余食物："    +bag.get(Bag.search(bag,"Foods")).getNumber())
                    .setCancelable(false)
                    .setView(view)
                    .setPositiveButton("确认", (dialog, which) -> {
                        TextView petHungerText = viewHolder.getPetsHunger();//
                        int eaten = Integer.valueOf(editText.getText().toString());//获取用户输入的投喂食物数
                        int total = bag.get(Bag.search(bag,"Foods")).getNumber();//获取背包中该物品剩余数量
                        if(eaten <= total){//如果输入的数量小于/等于剩余食物数量
                            if(eaten<=10-pet.getHunger()){//吃的食物数量小于/等于未满的饥饿值
                                pet.setHunger(eaten);
                                bag.get(Bag.search(bag,"Foods")).setNumber(-eaten);
                                petHungerText.setText("饱食度："+String.valueOf(pet.getHunger()));//修改宠物界面饱食度
                                Toast.makeText(context, "恢复了"+String.valueOf(eaten)+"点饱食度", Toast.LENGTH_SHORT).show();
                            }else {//吃的食物数量大于未满的饥饿值
                                bag.get(Bag.search(bag,"Foods")).setNumber(10-pet.getHunger());//减少不足饱食度数量的食物
                                pet.setHunger(10-pet.getHunger());//补满饱食度
                                petHungerText.setText("饱食度："+String.valueOf(pet.getHunger()));//修改宠物界面饱食度
                                Toast.makeText(context, pet.getName()+"已经吃撑了", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, "没有这么多的食物", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        }else {
            Toast.makeText(context, "包包里没有食物啦", Toast.LENGTH_SHORT).show();
        }
    }
}
