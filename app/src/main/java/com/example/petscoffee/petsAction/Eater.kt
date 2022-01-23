package com.example.petscoffee.petsAction

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.petscoffee.R
import com.example.petscoffee.adapters.PetsAdapter.PetViewHolder
import com.example.petscoffee.model.Bag
import com.example.petscoffee.model.goods.Goods
import com.example.petscoffee.model.pets.Pets

class Eater @SuppressLint("SetTextI18n") constructor(// 实现多宠物同时开饭
    private var context: Context, viewHolder: PetViewHolder, bag: List<Goods>, pet: Pets
) {
    private val pet: Pets
    private val viewHolder: PetViewHolder
    private val bag: List<Goods>

    init {
        context = context
        this.pet = pet
        this.bag = bag
        this.viewHolder = viewHolder
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_pets_eat, null)
        val editText = view.findViewById<EditText>(R.id.eat_edit)
        val alert = AlertDialog.Builder(
            context
        )
        if (Bag.search(bag, "Foods") != -1) {
            //如果背包里有食物
            alert.setTitle("投 喂").setMessage("剩余：" + bag[Bag.search(bag, "Foods")].getNumber())
                .setCancelable(false)
                .setView(view)
                .setPositiveButton("确认") { dialog: DialogInterface?, which: Int ->
                    val petHungerText: TextView = viewHolder.binding.petsHunger //
                    val eaten = editText.text.toString().toInt() //获取用户输入的投喂食物数
                    val total = bag[Bag.search(bag, "Foods")].getNumber() //获取背包中该物品剩余数量
                    if (eaten <= total) { //如果输入的数量小于/等于剩余食物数量
                        if (eaten <= 10 - pet.hunger) { //吃的食物数量小于/等于未满的饥饿值
                            pet.hunger = pet.hunger + eaten
                            bag[Bag.search(bag, "Foods")].setNumber(-eaten)
                            petHungerText.text = "hunger:：" + pet.hunger //修改宠物界面饱食度
                            Toast.makeText(context, "恢复了" + eaten + "点饱食度", Toast.LENGTH_SHORT)
                                .show()
                        } else { //吃的食物数量大于未满的饥饿值
                            bag[Bag.search(bag, "Foods")].setNumber(10 - pet.hunger) //减少不足饱食度数量的食物
                            pet.hunger = 10 - pet.hunger //补满饱食度
                            petHungerText.text = "hunger:" + pet.hunger //修改宠物界面饱食度
                            Toast.makeText(context, pet.name + "已经吃撑了", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "没有这么多的食物", Toast.LENGTH_SHORT).show()
                    }
                }.show()
        } else {
            Toast.makeText(context, "包包里没有食物啦", Toast.LENGTH_SHORT).show()
        }
    }
}