package com.example.petscoffee.fragment;

import android.content.Context;
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
import com.example.petscoffee.file.Archive;
import com.example.petscoffee.pets.Create;

public class Shop_pets_fragment extends Fragment implements View.OnClickListener {
    private static CoffeeShop coffee;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_pets_fragment, container, false);
        context = getActivity();
        Button buyCat = view.findViewById(R.id.shop_cat_buy);
        Button buyDog = view.findViewById(R.id.shop_dog_buy);
        buyCat.setOnClickListener(this);
        buyDog.setOnClickListener(this);
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
            View view = LayoutInflater.from(context).inflate(R.layout.input, null);
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("购 买 " + (species == 1 ? "猫 猫" : "狗 狗"));
            dialog.setMessage("给新" + (species == 1 ? "猫猫" : "狗狗") + "取一个名字：");
            dialog.setCancelable(false);
            dialog.setView(view);
            dialog.setPositiveButton("确认", (dialog1, which) -> {
                EditText editText = view.findViewById(R.id.inputValue);
                String name = editText.getText().toString();
                new Create(coffee, name, species);//调用pets.Create方法创建新宠物
                coffee.setMoney(-2000f);//扣钱
                Archive.save(coffee, getActivity());//保存
            });
            dialog.setNegativeButton("取消", (dialog12, which) -> {

            });
            dialog.show();
        } else {
            Toast.makeText(context, "钱钱不够TvT", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Archive.save(coffee,getActivity());//保存购买后的结果
    }

    public void setCoffee(CoffeeShop coffee){
        this.coffee = coffee;
    }
}
