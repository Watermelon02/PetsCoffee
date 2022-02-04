package com.example.petscoffee.ui.pets.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.petscoffee.R
import com.example.petscoffee.databinding.DialogFriendBinding
import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.model.friends.Friend
import com.example.petscoffee.repository.ArchiveRepository

/**
 * description ： FriendActivity界面点击用户item后弹出的dialogFragment,用于查看用户的详细信息或是参观对方的咖啡馆
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/2/3 21:44
 */

class FriendDialogFragment : DialogFragment() {
    private lateinit var coffeeShop: CoffeeShop

    @SuppressLint("SetTextI18n")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogFriendBinding.inflate(layoutInflater)
        binding.dialogFriendName.text = "${coffeeShop.name}(${coffeeShop.account})"
        binding.dialogFriendDay.text = "${coffeeShop.day}天"
        binding.dialogFriendMoney.text = "$:${coffeeShop.money}"
        binding.dialogFriendFriendsNumber.text = "有${coffeeShop.friends.size}位好友"
        //创建对应个数的imageView来展示用户拥有的宠物
        for (pet in coffeeShop.pets) {
            val petView = ImageView(requireContext())
            petView.setImageResource(if (pet.species == 1) R.drawable.cat else R.drawable.dog)
            petView.layoutParams = ViewGroup.LayoutParams(40, 40)
            binding.dialogFriendPets.addView(petView)
        }
        //添加好友
        binding.dialogFriendAdd.setOnClickListener {
            ArchiveRepository.loadCoffee {
                it.friends.add(Friend(coffeeShop.account, coffeeShop.name))
                ArchiveRepository.saveCoffee(it)
            }
        }
        binding.dialogFriendVisit.setOnClickListener {

        }
        return AlertDialog.Builder(requireContext()).setView(binding.root).create()
    }

    /**该方法用于传入dialogFragment要显示的数据，应在show()之前调用*/
    fun setCoffeeShop(coffeeShop: CoffeeShop): FriendDialogFragment =
        apply { this@FriendDialogFragment.coffeeShop = coffeeShop }
}