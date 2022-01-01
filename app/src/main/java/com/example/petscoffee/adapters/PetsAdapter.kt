package com.example.petscoffee.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petscoffee.R
import com.example.petscoffee.customview.BloodBar
import com.example.petscoffee.customview.PetView
import com.example.petscoffee.model.CoffeeShop
import com.example.petscoffee.model.goods.Goods
import com.example.petscoffee.model.pets.Pets
import com.example.petscoffee.petsAction.Eater
import com.example.petscoffee.petsAction.Washer
import com.google.android.material.button.MaterialButton

import kotlinx.android.synthetic.main.item_pets.view.*
import java.util.*

class PetsAdapter(coffeeShop: CoffeeShop) : RecyclerView.Adapter<PetsAdapter.ViewHolder>() {
    private val pets: List<Pets>
    private val bag: List<Goods>
    private lateinit var pet
            : Pets//本item的宠物对象
    private lateinit var context
            : Context//父类context(应该传入 onCreateViewHolder中的parent的context)
    private lateinit var petsViewHolder
            : ViewHolder//为洗澡，吃饭之后能改变属性值的textView

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petsImage: PetView = view.pets_image
        val petsName: TextView = view.pets_name
        val petsHp: BloodBar = view.pets_hp

        //为投喂之后能获得该textView，
        // 以改变宠物界面的饱食度数值
        val petsHunger: TextView = view.pets_hunger

        //为洗澡之后能获得该textView，
        // 以改变宠物界面的清洁度数值
        val petsCleanliness: TextView = view.pets_cleanliness
        val petsMood: TextView = view.pets_mood
        val petsLoveliness: TextView = view.pets_loveliness
        val petsSp: TextView = view.pets_sp
        val wash: MaterialButton = view.pets_button_wash
        val eat: MaterialButton = view.pets_button_eat
        var isClicked = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pets, parent, false)
        val viewHolder = ViewHolder(view)
        context = parent.context //为传到washer的context和viewHolder
        petsViewHolder = viewHolder
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        pet = pets[position]
        holder.petsImage.setImageResource(pet.imageId)
        holder.petsName.text = "name  :" + pet.name
        holder.petsHp.setValue(pet.hp)
        holder.petsHunger.text = "hunger:" + pet.hunger.toString()
        holder.petsCleanliness.text = "clean :" + pet.cleanliness.toString()
        holder.petsMood.text = "mood  :" + pet.mood.toString()
        holder.petsLoveliness.text = "love  :" + pet.loveliness.toString()
        holder.petsSp.text = "spec  :" + pet.sp.toString()
        holder.petsImage.setOnClickListener {
            if (!holder.isClicked) {
                holder.petsImage.jump()
                buttonShow(holder.wash, holder.eat)
                holder.isClicked = true
            } else {
                holder.petsImage.jump()
                buttonDisappear(holder.wash, holder.eat)
                holder.isClicked = false
            }
        }
        holder.eat.setOnClickListener { Eater(context, petsViewHolder, bag, pet) }
        holder.wash.setOnClickListener {
            Timer().schedule(
                Washer(context, pet, petsViewHolder),
                1000,
                1000
            )
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

    init {
        pets = coffeeShop.pets
        bag = coffeeShop.bag
    }
}