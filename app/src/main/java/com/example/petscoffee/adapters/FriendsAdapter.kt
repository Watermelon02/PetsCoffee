package com.example.petscoffee.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petscoffee.databinding.ItemFriendBinding
import com.example.petscoffee.model.CoffeeShop

/**
 * description ： FriendsActivity中的vp2对应的adapter
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/29 19:49
 */

class FriendsAdapter(private var friends:List<CoffeeShop>) : RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {
    class ViewHolder(val binding:ItemFriendBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val binding = ItemFriendBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.root.setOnClickListener {

        }
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.itemFriendName.text = "${friends[position].name}(${friends[position].account})"
        holder.binding.itemFriendTime.text = "${friends[position].day}天"
        holder.binding.itemFriendMoney.text = "$:${friends[position].money}"
        holder.binding.itemFriendPets.text = "${friends[position].pets.size}只宠物"
    }

    override fun getItemCount(): Int {
        Log.d("testTag", "friends:${friends.size}")
        return friends.size
    }
}