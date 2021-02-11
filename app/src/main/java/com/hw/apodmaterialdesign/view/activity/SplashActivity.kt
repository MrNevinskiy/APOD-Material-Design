package com.hw.apodmaterialdesign.view.activity

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.hw.apodmaterialdesign.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        image_bg_system.animate().rotationBy(550f)
            .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(2750)
            .setListener(object : Animator.AnimatorListener {

                override fun onAnimationEnd(animation: Animator?) {}
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })

        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}