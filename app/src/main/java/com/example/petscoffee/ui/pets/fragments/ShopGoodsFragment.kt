package com.example.petscoffee.ui.pets.fragments

import com.example.petscoffee.model.goods.Goods
import java.util.ArrayList
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.petscoffee.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.petscoffee.model.goods.Foods
import com.example.petscoffee.model.equipments.Bell
import com.example.petscoffee.model.equipments.Bowl
import com.example.petscoffee.model.equipments.Nest
import com.example.petscoffee.repository.local.Archive
import com.example.petscoffee.model.CoffeeShop
import android.content.DialogInterface
import android.widget.EditText
import com.example.petscoffee.model.Bag
import java.lang.Exception
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.petscoffee.model.equipments.Equipment

class ShopGoodsFragment : Fragment() {
    private val goods: MutableList<Goods> = ArrayList() //商品list,通过initGoods来设置内容
    private var activity: Activity? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shop_goods, container, false)
        activity = getActivity()
        val recyclerView: RecyclerView = view.findViewById(R.id.shop_recycler)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        initGoods()
        val adapter = ShopAdapter(goods)
        recyclerView.adapter = adapter
        return view
    }

    inner class ShopAdapter(private val goods: List<Goods>) :
        RecyclerView.Adapter<ShopAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var shopImage: ImageView = itemView.findViewById(R.id.shop_item_image)
            var shopInfo: TextView = itemView.findViewById(R.id.shop_item_info)
            private var shopItemLayout: CardView = itemView.findViewById(R.id.shop_item_layout)

            init {
                shopItemLayout.setOnClickListener {
                    buyGoods(
                        goods[adapterPosition].name
                    )
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_shop, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val good = goods[position]
            holder.shopImage.setImageResource(good.imageId)
            holder.shopInfo.text = """
                ${good.info}
                价格:${good.price}
                """.trimIndent()
        }

        override fun getItemCount(): Int {
            return goods.size
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        goods.clear()
    }

    private fun initGoods() { //初始化商店商品
        goods.add(Foods())
        goods.add(Bell())
        goods.add(Bowl())
        goods.add(Nest())
        goods.add(Bowl())
        goods.add(Foods())
        goods.add(Bell())
        goods.add(Nest())
        goods.add(Bowl())
        goods.add(Nest())
        goods.add(Bowl())
        goods.add(Foods())
    }

    fun buyGoods(name: String) { //购买goods方法
        Archive.loadCoffee(activity) { coffeeShop: CoffeeShop ->
            val view =
                LayoutInflater.from(activity).inflate(R.layout.shop_fragment_number_input, null)
            val builder = AlertDialog.Builder(
                requireActivity()
            )
            try {
                val good =
                    Class.forName(Goods::class.java.name.replace("Goods", name)).getConstructor()
                        .newInstance() as Goods
                //通过反射获取包名
                val price = good.price //获取物品价格
                builder.setTitle("购 买 $name")
                builder.setMessage("想要买多少个：")
                builder.setCancelable(false)
                builder.setView(view)
                builder.setPositiveButton("确认") { dialog: DialogInterface?, which: Int ->
                    val editText = view?.findViewById<EditText>(R.id.inputValue)
                    val num = editText?.text.toString().toInt()
                    if (coffeeShop.money >= num * price) { //如果钱够买这么多食物
                        try { //调用背包中的addGood方法
                            Bag.addGood(coffeeShop.bag, name, num)
                            playSuccess()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        coffeeShop.money = coffeeShop.money - num * price //扣钱
                        Archive.saveCoffee(coffeeShop, activity) //保存购买后的结果
                    } else {
                        Toast.makeText(activity, "钱钱不够", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                try { //当购买的为装备时，因为装备类不在goods包下，无法通过上面的反射得到，所以通过如下操作购买
                    val good = Class.forName(Equipment::class.java.name.replace("Equipment", name))
                        .getConstructor().newInstance() as Goods
                    //通过反射获取包名
                    val price = good.price //获取物品价格
                    builder.setTitle("购 买 $name")
                    builder.setMessage("想要买多少个：")
                    builder.setCancelable(false)
                    builder.setView(view)
                    builder.setPositiveButton("确认") { dialog1: DialogInterface?, which: Int ->
                        val editText = view?.findViewById<EditText>(R.id.inputValue)
                        val num = editText?.text.toString().toInt()
                        if (coffeeShop.money >= num * price) { //如果钱够买这么多食物
                            try { //调用背包中的addGood方法
                                Bag.addGood(coffeeShop.bag, name, num)
                            } catch (w: Exception) {
                                e.printStackTrace()
                            }
                            coffeeShop.money = coffeeShop.money - num * price //扣钱
                            Archive.saveCoffee(coffeeShop, activity) //保存购买后的结果
                        } else {
                            Toast.makeText(activity, "钱钱不够", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (w: Exception) {
                    w.printStackTrace()
                }
                e.printStackTrace()
            }
            builder.setNegativeButton("取消") { dialog12: DialogInterface?, which: Int -> }
            requireActivity().runOnUiThread { builder.show() }
        }
    }

    private fun playSuccess(){
        AlertDialog.Builder(requireActivity()).apply {
            setView(LayoutInflater.from(activity).inflate(R.layout.shopping_success,null))
            show()
        }
    }
}