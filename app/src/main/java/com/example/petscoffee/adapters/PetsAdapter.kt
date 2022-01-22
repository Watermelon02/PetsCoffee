package com.example.petscoffee.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.petscoffee.databinding.ItemPetsBinding
import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.model.goods.Goods
import com.example.petscoffee.model.pets.Pets
import com.example.petscoffee.petsAction.Eater
import com.example.petscoffee.petsAction.Washer
import java.util.*

/**
 * description ： 宠物界面vp2的adapter
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/1/22 22:53
 */

class PetsAdapter(coffeeShop: CoffeeShop) : RecyclerView.Adapter<PetsAdapter.PetViewHolder>() {
    private val pets: List<Pets>
    private val bag: List<Goods>
    private lateinit var pet: Pets
    private lateinit var context
            : Context//父类context(应该传入 onCreateViewHolder中的parent的context)
    private lateinit var petsViewHolder
            : PetViewHolder//传递给Washer，Eater模块之后以改变子textView

    init {
        pets = coffeeShop.pets
        bag = coffeeShop.bag
    }


    class PetViewHolder(val binding: ItemPetsBinding) : RecyclerView.ViewHolder(binding.root) {
        var isClicked = false//宠物图片点击标志位,控制动画状态
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = ItemPetsBinding.inflate(LayoutInflater.from(parent.context))
        val viewHolder = PetViewHolder(view)
        context = parent.context //为传到washer的context和viewHolder
        petsViewHolder = viewHolder
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        pet = pets[position]
        holder.binding.apply {
            petsImage.setImageResource(pet.imageId)
            petsName.text = "name  :" + pet.name
            petsHp.setValue(pet.hp)
            petsHunger.text = "hunger:" + pet.hunger.toString()
            petsCleanliness.text = "clean :" + pet.cleanliness.toString()
            petsMood.text = "mood  :" + pet.mood.toString()
            petsLoveliness.text = "love  :" + pet.loveliness.toString()
            petsSp.text = "spec  :" + pet.sp.toString()
            petsImage.jump()
            petsImage.setOnClickListener {
                if (!holder.isClicked) {
                    holder.binding.petsImage.jump()
                    buttonShow(holder.binding.petsButtonWash, holder.binding.petsButtonEat)
                    holder.isClicked = true
                } else {
                    holder.binding.petsImage.jump()
                    buttonDisappear(holder.binding.petsButtonWash, holder.binding.petsButtonEat)
                    holder.isClicked = false
                }
            }
            petsButtonEat.setOnClickListener { Eater(context, petsViewHolder, bag, pet) }
            petsButtonWash.setOnClickListener {
                Timer().schedule(
                    Washer(context, pet, petsViewHolder),
                    1000,
                    1000
                )
            }
        }

    }

    override fun getItemCount(): Int {
        return pets.size
    }

    private fun buttonShow(wash: Button, eat: Button) {
        val set = AnimatorSet()
        set.playTogether(
            ObjectAnimator.ofFloat(wash, "translationX", 0f, 400f),
            ObjectAnimator.ofFloat(wash, "scaleX", 0f, 1f),
            ObjectAnimator.ofFloat(wash, "scaleY", 0f, 1f),
            ObjectAnimator.ofFloat(wash, "alpha", 0f, 1f),
            ObjectAnimator.ofFloat(eat, "translationX", 0f, -400f),
            ObjectAnimator.ofFloat(eat, "alpha", 0f, 1f),
            ObjectAnimator.ofFloat(eat, "scaleX", 0f, 1f),
            ObjectAnimator.ofFloat(eat, "scaleY", 0f, 1f)
        )
        set.duration = 1000
        set.start()
    }

    private fun buttonDisappear(wash: Button, eat: Button) {
        val set = AnimatorSet()
        set.playTogether(
            ObjectAnimator.ofFloat(wash, "translationX", 400f, 0f),
            ObjectAnimator.ofFloat(wash, "scaleX", 1f, 0f),
            ObjectAnimator.ofFloat(wash, "scaleY", 1f, 0f),
            ObjectAnimator.ofFloat(wash, "alpha", 1f, 0f),
            ObjectAnimator.ofFloat(eat, "translationX", -400f, 0f),
            ObjectAnimator.ofFloat(eat, "scaleX", 1f, 0f),
            ObjectAnimator.ofFloat(eat, "scaleY", 1f, 0f),
            ObjectAnimator.ofFloat(eat, "alpha", 1f, 0f),
        )
        set.duration = 1000
        set.start()
    }
}