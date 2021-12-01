package com.example.petscoffee.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.petscoffee.R;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.database.Archive;
import com.example.petscoffee.pets.Create;

public class Shop_pets_fragment extends Fragment implements View.OnClickListener {
    private static CoffeeShop coffee;
    private static Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_pets, container, false);
        activity = getActivity();
        Archive.loadCoffee(activity, coffeeShop -> {
            coffee = coffeeShop;
            Button buyCat = view.findViewById(R.id.shop_cat_buy);
            Button buyDog = view.findViewById(R.id.shop_dog_buy);
            buyCat.setOnClickListener(this);
            buyDog.setOnClickListener(this);
        });
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_cat_buy:
                    buyPet(coffee, 1);
                break;
            case R.id.shop_dog_buy:
                    buyPet(coffee, 2);
        }
    }

    public void buyPet(CoffeeShop coffee, int species) {//购买宠物方法
        if (coffee.getMoney() >= 2000f) {
            View view = LayoutInflater.from(activity).inflate(R.layout.shop_fragment_name_input, null);
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            dialog.setTitle("购 买 " + (species == 1 ? "猫 猫" : "狗 狗"));
            dialog.setMessage("给新" + (species == 1 ? "猫猫" : "狗狗") + "取一个名字：");
            dialog.setCancelable(false);
            dialog.setView(view);
            dialog.setPositiveButton("确认", (dialog1, which) -> {
                EditText editText = view.findViewById(R.id.inputName);
                try {
                    String name = editText.getText().toString();
                    new Create(coffee, name, species);//调用pets.Create方法创建新宠物
                }catch (Exception e){
                    e.printStackTrace();
                }
                coffee.setMoney(-2000f);//扣钱
                Archive.saveCoffee(coffee, getActivity());//保存
            });
            dialog.setNegativeButton("取消", (dialog12, which) -> {

            });
            dialog.show();
        } else {
            Toast.makeText(activity, "钱钱不够TvT", Toast.LENGTH_SHORT).show();
        }
    }

}
