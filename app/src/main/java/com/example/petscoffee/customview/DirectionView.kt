package com.example.petscoffee.customview

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/2/7 15:29
 */
class DirectionView2(context: Context?, attrs: AttributeSet?) :
    AppCompatImageView(context!!, attrs) {
    private var lastX = 0f
    private var lastY = 0f
    private var dx = 0
    private var dy = 0

    /* override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
         super.onMeasure(widthMeasureSpec, heightMeasureSpec)
         lastX = MeasureSpec.getSize(widthMeasureSpec) / 2f
         lastY = MeasureSpec.getSize(widthMeasureSpec) / 2f
     }*/

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            lastX = event.rawX
            lastY = event.rawY
            return true//当view在onTouchEvent中消费了action_down，之后的action_move和action_up才会传到该view
        } else if (event?.action == MotionEvent.ACTION_MOVE) {
            dx = (event.rawX - lastX).toInt()
            dy = (event.rawY - lastY).toInt()
            layout(
                left + dx,
                top + dy,
                right + dx,
                bottom + dy
            )
            lastX = event.rawX
            lastY = event.rawY
            return true
        } else {//ACTION_UP
            if (lastX < rootView.width / 2) {
                val animator = ValueAnimator.ofFloat(lastX - measuredWidth, 0f).setDuration(500)
                animator.addUpdateListener {
                    val value = it.animatedValue as Float
                    x = value
                }
                animator.start()
            } else {
                val animator =
                    ValueAnimator.ofFloat(lastX, rootView.width.toFloat() - measuredWidth)
                        .setDuration(500)
                animator.addUpdateListener {
                    val value = it.animatedValue as Float
                    x = value
                }
                animator.start()
            }
        }
        return super.onTouchEvent(event)
    }
}