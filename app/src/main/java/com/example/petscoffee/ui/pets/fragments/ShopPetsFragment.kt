package com.example.petscoffee.ui.pets.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.petscoffee.R
import com.example.petscoffee.customview.PetView
import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.model.pets.createPet
import com.example.petscoffee.repository.local.Archive
import kotlinx.android.synthetic.main.fragment_shop_pets.view.*

class ShopPetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop_pets, container, false)

        Archive.loadCoffee(activity) { coffeeShop: CoffeeShop? ->
            val buyCat: CardView = view.shop_cat_buy
            val buyDog: CardView = view.shop_dog_buy
            val cat: PetView = view.shop_cat_image
            val dog: PetView = view.shop_dog_image
            buyCat.setOnClickListener {
                cat.jump()
                buyPet(coffeeShop, 1)
            }
            buyDog.setOnClickListener {
                dog.jump()
                buyPet(coffeeShop, 2)
            }
        }
        return view
    }

    private fun buyPet(coffee: CoffeeShop?, species: Int) { //购买宠物方法
        if (coffee!!.money >= 2000f) {
            val view = LayoutInflater.from(activity)
                .inflate(R.layout.shop_fragment_name_input, null)
            val dialog = AlertDialog.Builder(this.requireContext())
            dialog.setTitle("购 买 " + if (species == 1) "猫 猫" else "狗 狗")
            dialog.setMessage("给新" + (if (species == 1) "猫猫" else "狗狗") + "取一个名字：")
            dialog.setCancelable(false)
            dialog.setView(view)
            dialog.setPositiveButton("确认") { dialog1: DialogInterface?, which: Int ->
                val editText = view.findViewById<EditText>(R.id.inputName)
                val name = editText.text.toString()
                createPet(coffee, name, species) //调用pets.Create方法创建新宠物
                coffee.money = coffee.money - 2000f //扣钱
                Archive.saveCoffee(coffee, activity) //保存
            }
            dialog.setNegativeButton("取消") { dialog12: DialogInterface?, which: Int -> }
            dialog.show()
        } else {
            Toast.makeText(activity, "钱钱不够TvT", Toast.LENGTH_SHORT).show()
        }
    }

}