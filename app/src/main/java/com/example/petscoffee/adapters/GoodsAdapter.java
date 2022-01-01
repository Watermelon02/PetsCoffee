package com.example.petscoffee.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.model.goods.Goods;

import java.util.List;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private List<Goods> bag;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImage;
        TextView goodsName;
        TextView goodsNumber;

        public ViewHolder(@NonNull View itemView) {//itemView为传入的goods_item布局
            super(itemView);
            goodsImage = itemView.findViewById(R.id.goods_image);//获取goods_item中的子view实例
            goodsName = itemView.findViewById(R.id.goods_name);
            goodsNumber = itemView.findViewById(R.id.goods_number);
        }
    }

    public GoodsAdapter(List<Goods> bag) {//将要展示的 数据 通过构造方法传入
        this.bag = bag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//实例化viewHolder
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods, parent
                , false);//通过layout.goods_item布局实例化itemView对象
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {//该方法对Recycler子项的数据进行赋值，
        // 会在每个子项滚动到屏幕中时执行
        Goods good = bag.get(position);//根据position，获取bag中的goods实例
        holder.goodsImage.setImageResource(good.getImageId());
        holder.goodsName.setText(good.getName());
        holder.goodsNumber.setText(String.valueOf(good.getNumber()));
    }

    @Override
    public int getItemCount() {
        return bag.size();
    }


}
