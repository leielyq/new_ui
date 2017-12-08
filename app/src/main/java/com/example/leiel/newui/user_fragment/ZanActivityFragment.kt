package com.example.leiel.newui.user_fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.leiel.newui.R
import kotlinx.android.synthetic.main.fragment_zan.*

/**
 * A placeholder fragment containing a simple view.
 */
class ZanActivityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_zan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var view = MyView(context)
        fragment_zan_root.addView(view)
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null)
    }

    class MyView(context: Context?) : View(context) {
        var mPaint = Paint()
        var startX = 100f
        var startY = 200f
        var str = "4399"
        var animator =ValueAnimator()

        init {
            mPaint.strokeWidth = 5f
            mPaint.style = Paint.Style.FILL
            mPaint.color = Color.RED
            mPaint.textSize = 120f

            animator = ValueAnimator.ofFloat(200f, 100f)
            animator.duration = 1000
//            animator.repeatMode = ValueAnimator.REVERSE
//            animator.repeatCount = ValueAnimator.INFINITE

            animator.addUpdateListener { valueAnimator ->
                startY = valueAnimator.animatedValue as Float
                invalidate()
            }
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {
            Log.d("ceshi", event?.action.toString())
            when (event?.getAction()) {
                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_DOWN -> {

                }
                MotionEvent.ACTION_UP -> {
                    if (animator.isRunning) {
                        animator.end()
                    }else
                        animator.start()
                    return true
                }
            }

            return true
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
//            canvas?.drawColor(Color.GRAY)
            startX = 100f

            var i = 0
            for (c in str) {
                i++
                startX += mPaint.measureText(c.toString())
                if (i == str.length) {
                    if (startY!=100f){
                        canvas?.drawText("8", startX, startY , mPaint)
                    }
                    canvas?.drawText(c.toString(), startX, startY + 100, mPaint)
                } else
                    canvas?.drawText(c.toString(), startX, 200f, mPaint)
            }

//            canvas?.drawText("3",startX,200f,mPaint)

        }
    }
}
