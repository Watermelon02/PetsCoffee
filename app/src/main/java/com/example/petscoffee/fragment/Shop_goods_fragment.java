package com.example.petscoffee.fragment;

import android.app.Activity;
import android.content.Context;
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
import com.example.petscoffee.bag.Bag;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.equipment.Bell;
import com.example.petscoffee.equipment.Bowl;
import com.example.petscoffee.equipment.Equipment;
import com.example.petscoffee.equipment.Nest;
import com.example.petscoffee.database.Archive;
import com.example.petscoffee.goods.Foods;
import com.example.petscoffee.goods.Goods;

import java.util.ArrayList;
import java.util.List;

public class Shop_goods_fragment extends Fragment {
    private List<Goods> goods = new ArrayList<Goods>();//商品list,通过initGoods来设置内容
    private static CoffeeShop coffee;
    private static Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_goods, container, false);
        activity = getActivity();
        Archive.loadCoffee(activity, coffeeShop -> {
            coffee = coffeeShop;
            RecyclerView recyclerView = view.findViewById(R.id.shop_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            initGoods();
            ShopAdapter adapter = new ShopAdapter(goods);
            recyclerView.setAdapter(adapter);
        });

        return view;
    }

    public static class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
        private List<Goods> bag;//为购买物品到背包设置该属性
        private List<Goods> goods;
        private Context context;//购买商品时需加载到的ShopActivity的context,通过onCreateViewHolder中的parent来获取

        public ShopAdapter(List<Goods> goods) {
            this.bag = coffee.getBag();
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
            }
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Goods good = goods.get(position);
            holder.shop_image.setImageResource(good.getImageId());
            holder.shop_info.setText(good.getInfo() + "\n价格:" + good.getPrice());
            holder.shop_buy.setOnClickListener(v -> buyGoods(good.getName())
            );
        }

        @Override
        public int getItemCount() {
            return goods.size();
        }

        public void buyGoods(String name) {//购买goods方法
            View view = LayoutInflater.from(context).inflate(R.layout.shop_fragment_number_input, null);
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
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
                    if (coffee.getMoney() >= num * price) {//如果钱够买这么多食物
                        try {//调用背包中的addGood方法
                            Bag.addGood(bag,name, num);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        coffee.setMoney(-num * price);//扣钱
                        Archive.saveCoffee(coffee, activity);//保存购买后的结果
                    } else {
                        Toast.makeText(context, "钱钱不够", Toast.LENGTH_SHORT).show();
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
                        if (coffee.getMoney() >= num * price) {//如果钱够买这么多食物
                            try {//调用背包中的addGood方法
                                Bag.addGood(bag,name, num);
                            } catch (Exception w) {
                                e.printStackTrace();
                            }
                            coffee.setMoney(-num * price);//扣钱
                            Archive.saveCoffee(coffee, activity);//保存购买后的结果
                        } else {
                            Toast.makeText(context, "钱钱不够", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception w) {
                    w.printStackTrace();
                }
                e.printStackTrace();
            }
            dialog.setNegativeButton("取消", (dialog12, which) -> {

            });
            dialog.show();
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
}
