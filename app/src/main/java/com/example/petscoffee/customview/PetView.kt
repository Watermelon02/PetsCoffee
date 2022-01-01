package com.example.petscoffee.customview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.animation.BounceInterpolator

class PetView(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {
    private var mTop = 0

    init {
        jump()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    fun jump() {//一个宠物跳跃动画
        val valueAnimator = ValueAnimator.ofInt(0, -100, 0)
        valueAnimator.addUpdateListener {
            top = mTop + it.animatedValue as Int
        }
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.repeatCount = 0
        valueAnimator.duration = 2000
        valueAnimator.start()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mTop = top
    }
}