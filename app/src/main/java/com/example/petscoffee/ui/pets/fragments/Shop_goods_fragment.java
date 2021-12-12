package com.example.petscoffee.ui.pets.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.model.Bag;
import com.example.petscoffee.repository.local.Archive;
import com.example.petscoffee.model.equipments.Bell;
import com.example.petscoffee.model.equipments.Bowl;
import com.example.petscoffee.model.equipments.Equipment;
import com.example.petscoffee.model.equipments.Nest;
import com.example.petscoffee.model.goods.Foods;
import com.example.petscoffee.model.goods.Goods;

import java.util.ArrayList;
import java.util.List;

public class Shop_goods_fragment extends Fragment {
    private List<Goods> goods = new ArrayList<Goods>();//商品list,通过initGoods来设置内容
    private static Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_goods, container, false);
        activity = getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.shop_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        initGoods();
        ShopAdapter adapter = new ShopAdapter(goods);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public static class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
        private static List<Goods> goods;

        public ShopAdapter(List<Goods> goods) {
            this.goods = goods;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView shop_image;
            TextView shop_info;
            Button shop_buy;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                shop_image = itemView.findViewById(R.id.shop_item_image);
                shop_info = itemView.findViewById(R.id.shop_item_info);
                shop_buy = itemView.findViewById(R.id.shop_item_button);
                shop_buy.setOnClickListener(v -> {buyGoods(goods.get(getAdapterPosition()).getName());
                });
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Goods good = goods.get(position);
            holder.shop_image.setImageResource(good.getImageId());
            holder.shop_info.setText(good.getInfo() + "\n价格:" + good.getPrice());
        }

        @Override
        public int getItemCount() {
            return goods.size();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        goods.clear();
    }

    public void initGoods() {//初始化商店商品
        goods.add(new Foods());
        goods.add(new Bell());
        goods.add(new Bowl());
        goods.add(new Nest());
    }

    public static void buyGoods(String name) {//购买goods方法
        Archive.loadCoffee(activity, coffeeShop -> {
            View view = LayoutInflater.from(activity).inflate(R.layout.shop_fragment_number_input, null);
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            try {
                Goods good = (Goods) Class.forName(Goods.class.getName().replace("Goods", name)).getConstructor().newInstance();
                //通过反射获取包名
                float price = good.getPrice();//获取物品价格
                dialog.setTitle("购 买 " + name);
                dialog.setMessage("想要买多少个：");
                dialog.setCancelable(false);
                dialog.setView(view);
                dialog.setPositiveButton("确认", (dialog1, which) -> {
                    EditText editText = view.findViewById(R.id.inputValue);
                    int num = Integer.parseInt(editText.getText().toString());
                    if (coffeeShop.getMoney() >= num * price) {//如果钱够买这么多食物
                        try {//调用背包中的addGood方法
                            Bag.addGood(coffeeShop.getBag(), name, num);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        coffeeShop.setMoney(coffeeShop.getMoney()-num * price);//扣钱
                        Archive.saveCoffee(coffeeShop, activity);//保存购买后的结果
                    } else {
                        Toast.makeText(activity, "钱钱不够", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                try {//当购买的为装备时，因为装备类不在goods包下，无法通过上面的反射得到，所以通过如下操作购买
                    Goods good = (Goods) Class.forName(Equipment.class.getName().replace("Equipment", name)).getConstructor().newInstance();
                    //通过反射获取包名
                    float price = good.getPrice();//获取物品价格
                    dialog.setTitle("购 买 " + name);
                    dialog.setMessage("想要买多少个：");
                    dialog.setCancelable(false);
                    dialog.setView(view);
                    dialog.setPositiveButton("确认", (dialog1, which) -> {
                        EditText editText = view.findViewById(R.id.inputValue);
                        int num = Integer.parseInt(editText.getText().toString());
                        if (coffeeShop.getMoney() >= num * price) {//如果钱够买这么多食物
                            try {//调用背包中的addGood方法
                                Bag.addGood(coffeeShop.getBag(), name, num);
                            } catch (Exception w) {
                                e.printStackTrace();
                            }
                            coffeeShop.setMoney(coffeeShop.getMoney()-num * price);//扣钱
                            Archive.saveCoffee(coffeeShop, activity);//保存购买后的结果
                        } else {
                            Toast.makeText(activity, "钱钱不够", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception w) {
                    w.printStackTrace();
                }
                e.printStackTrace();
            }
            dialog.setNegativeButton("取消", (dialog12, which) -> {

            });
            activity.runOnUiThread(()->{
                dialog.show();
            });
        });
    }
}
