package com.example.petscoffee.customview.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.petscoffee.customview.viewGroup.MyRefreshLayout
import com.google.android.material.appbar.AppBarLayout

/**
 * description ： 在coordinatorLayout中让rv能随着appBarLayout的位置变化而改变自己的位置
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/2/11 16:17
 */
class RecyclerBehavior(context: Context, attrs: AttributeSet) :
    CoordinatorLayout.Behavior<MyRefreshLayout>(context, attrs) {
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: MyRefreshLayout,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: MyRefreshLayout,
        dependency: View
    ): Boolean {
        var dependencyY = dependency.height + dependency.translationY
        if (dependencyY < 0) {
            dependencyY = 0f
        }
        child.y = dependencyY
        (child.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin +=dependencyY.toInt()
        return true
    }
}