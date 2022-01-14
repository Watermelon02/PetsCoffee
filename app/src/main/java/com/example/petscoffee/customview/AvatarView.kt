package com.example.petscoffee.customview

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.graphics.toRectF

class AvatarView(val mBitmap:Bitmap) : Drawable() {
    private var min = 0f
    private lateinit var mBounds:Rect
    private val mPaint = Paint()

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        mPaint.shader = BitmapShader(Bitmap.createScaledBitmap(mBitmap,right-left,bottom-top,true),Shader.TileMode.CLAMP,Shader.TileMode.CLAMP)
        mBounds = Rect(left, top, right, bottom)
    }

    override fun draw(canvas: Canvas) {
        min = minOf(mBounds.width().toFloat(),mBounds.height().toFloat())
        canvas.drawRoundRect(mBounds.toRectF(),min/2,min/2,mPaint)
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getIntrinsicHeight(): Int {
        return mBitmap.height
    }

    override fun getIntrinsicWidth(): Int {
        return mBitmap.width
    }
}