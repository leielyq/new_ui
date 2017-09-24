package com.example.leiel.newui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.Scene
import android.support.transition.TransitionManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_alt.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val con1 = ConstraintSet()
        con1.clone(activity_main_cl)
        val con2 = ConstraintSet()
        con2.clone(this, R.layout.activity_main_alt)
        val con02 = Scene.getSceneForLayout(activity_main_cl, R.layout.activity_main_alt, this);

        var change = false
        findViewById<View>(R.id.activity_main_btn).setOnClickListener {
//            TransitionManager.beginDelayedTransition(activity_main_cl)
            TransitionManager.beginDelayedTransition(con02, ChangeBounds())
            val constraint = if (change) con1 else con2
//            constraint.applyTo(activity_main_cl)
            change = !change

        }


    }
}
