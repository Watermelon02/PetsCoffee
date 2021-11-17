package com.example.petscoffee.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petscoffee.R;
import com.example.petscoffee.activities.CoffeeShopActivity;
import com.example.petscoffee.coffeeShop.CoffeeShop;
import com.example.petscoffee.message.Message;
import com.example.petscoffee.message.MsgType;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    ArrayList<Message> msgArray;
    Context context;
    CoffeeShop coffee;
    public MessageAdapter(ArrayList<Message> msgArray){
        this.msgArray = msgArray;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout left_layout;
        LinearLayout right_layout;
        TextView left_text;
        TextView right_text;
        public ViewHolder(View itemView){
            super(itemView);
            left_layout = itemView.findViewById(R.id.message_layout_left);
            right_layout = itemView.findViewById(R.id.message_layout_right);
            left_text = itemView.findViewById(R.id.message_item_left);
            right_text = itemView.findViewById(R.id.message_item_right);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message msg = msgArray.get(position);
        if(msg.getMsgType() == MsgType.LEFT){//如果为左边的消息
            holder.right_layout.setVisibility(View.GONE);//右边布局不可见
            holder.left_layout.setVisibility(View.VISIBLE);
            holder.left_text.setText(msg.getContent());//设置TextView内容为msg中的内容
        }else {//为右边消息
            holder.left_layout.setVisibility(View.GONE);//左边布局不可见
            holder.right_layout.setVisibility(View.VISIBLE);
            holder.right_text.setText(msg.getContent());//设置TextView内容为msg中的内容
        }
    }

    @Override
    public int getItemCount() {
        return msgArray.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.right_text.getText()!=""){
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                    Intent intent = new Intent(context,CoffeeShopActivity.class);
                    intent.putExtra("coffee",coffee);
                    context.startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public void setCoffee(CoffeeShop coffee) {
        this.coffee = coffee;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
