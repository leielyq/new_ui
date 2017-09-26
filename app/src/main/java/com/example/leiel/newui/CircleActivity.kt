package com.example.leiel.newui

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_circle.*
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint


class CircleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle)
        activity_circle_root.addView(MyView(this))
    }

    class MyView(context: Context?) : View(context) {

        var mPaint = Paint()
        var mR = 200
        var mW = 0
        var mH = 0
        var mC = 0f

        init {
            mPaint.strokeWidth = 5f
            mPaint.style = Paint.Style.FILL
            mPaint.color = Color.GRAY

        }


        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            mW = MeasureSpec.getSize(widthMeasureSpec)
            mH = MeasureSpec.getSize(heightMeasureSpec)


            val mA = ValueAnimator.ofFloat(0f,360f)
            mA.duration=1000
            mA.repeatMode=ValueAnimator.REVERSE
            mA.repeatCount=ValueAnimator.INFINITE
            mA.addUpdateListener { valueAnimator ->
                mC= valueAnimator.animatedValue as Float
                invalidate()
            }

            mA.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    mPaint.color = Color.RED
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    super.onAnimationRepeat(animation)
                    mPaint.color = Color.RED
                }
            })


            mA.start()
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            val rect = RectF((mW/2-mR/2).toFloat(), (mH/2-mR/2).toFloat(), (mW/2+mR/2).toFloat(), (mH/2+mR/2).toFloat())

            canvas?.drawArc(rect, 0f, mC,true,mPaint)

//            canvas?.drawCircle((mW / 2).toFloat(), (mH / 2).toFloat(), mR.toFloat(), mPaint)
        }
    }
}
