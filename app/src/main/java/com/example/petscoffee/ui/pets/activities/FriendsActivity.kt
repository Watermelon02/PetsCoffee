package com.example.petscoffee.ui.pets.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.petscoffee.R
import com.example.petscoffee.adapters.FriendsAdapter
import com.example.petscoffee.databinding.ActivityFriendsBinding
import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.ui.pets.viewModel.FriendsViewModel

/**
 * description ： FriendsActivity,用于添加好友
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/27 14:16
 */

class FriendsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_friends
        ) as ActivityFriendsBinding
        val viewModel = ViewModelProvider(this)[FriendsViewModel::class.java]
        val friends = ArrayList<CoffeeShop>()
        binding.activityFriendsViewPager
        binding.activityFriendsViewPager.adapter = FriendsAdapter(friends,supportFragmentManager)
        binding.activityFriendsViewPager.layoutManager = LinearLayoutManager(this)
        binding.activityFriendsSearch.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.query(it.toString())
            } else {
                viewModel.friends()
            }
        }
        viewModel.friendsLiveData.observe(this) {
            friends.clear()
            friends.addAll(it)
            binding.activityFriendsViewPager.adapter?.notifyDataSetChanged()
        }
        viewModel.friends()//初始化最后时加载已经添加的好友
    }
}