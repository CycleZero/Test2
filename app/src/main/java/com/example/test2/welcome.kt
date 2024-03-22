package com.example.test2

import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.example.test2.databinding.ActivityWelcomeBinding
import kotlinx.coroutines.delay

class welcome : AppCompatActivity() {
    private lateinit var binding:ActivityWelcomeBinding
    private lateinit var t:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.animate(binding.imageView).apply {
            //缩放，变成1.0倍
            scaleX(1.0f)
            scaleY(1.0f)
            //动画时常1秒
            duration = 1500
            //动画监听
            setListener(object : ViewPropertyAnimatorListener {
                override fun onAnimationStart(view: View) {
                    t="1"
                }

                override fun onAnimationEnd(view: View) {
                    startActivity(Intent(this@welcome,MainActivity::class.java))
                    finish()

                }

                override fun onAnimationCancel(view: View) {
                    t="1"
                }
            })
        }

    }
}