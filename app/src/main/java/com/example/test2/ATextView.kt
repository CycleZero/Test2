package com.example.test2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.TextView
import com.example.test2.R.*


class ATextView : ViewGroup {
    private var context: Context

    constructor(context: Context) : super(context) {
        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        this.context = context
    }


    @SuppressLint("ResourceAsColor")
    fun setText(text: String?, animation: Animation?, duration: Int) {
        var time:Long = 0
        if (text != null && !text.isEmpty()) {
            val characters = text.toCharArray()
            for (c in characters) {
                val t = TextView(context)

                //遍历传入的字符串的每个字符，生成一个TextView，并设置它的动画
                t.text = c.toString()
                t.textSize = 28f
                t.setTextColor(R.color.black)

                val h: Handler = Handler()

                //每隔duration时间，播放下一个TextView的动画
                h.postDelayed(Runnable {
                    addView(t)
                    t.animation = animation
                }, time)

                time += duration
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childUp = 0


        // 遍历所有子视图
        val childCount = childCount
        for (i in 0 until childCount) {
            val childView = getChildAt(i)


            // 获取在onMeasure中计算的视图尺寸
            val measureHeight = childView.measuredHeight
            val measuredWidth = childView.measuredWidth


            //将他们横向排列
            childView.layout(childLeft, childUp, childLeft + measuredWidth, childUp+measureHeight)

            if(childLeft+measuredWidth*2>= this.width)
            {
                childLeft=0
                childUp+=measureHeight
            }
            else
            {
                childLeft += measuredWidth

            }


        }
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureWidth: Int = measureWidth(widthMeasureSpec)
        val measureHeight: Int = measureHeight(heightMeasureSpec)

        // 计算自定义的ViewGroup中所有子控件的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        // 设置自定义的控件MyViewGroup的大小
        setMeasuredDimension(measureWidth, measureHeight)
    }
    private fun measureWidth(pWidthMeasureSpec: Int): Int {
        var result = 0
        val widthMode = MeasureSpec.getMode(pWidthMeasureSpec) // 得到模式
        val widthSize = MeasureSpec.getSize(pWidthMeasureSpec) // 得到尺寸

        when (widthMode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> result = widthSize
        }
        return result
    }

    private fun measureHeight(pHeightMeasureSpec: Int): Int {
        var result = 0

        val heightMode = MeasureSpec.getMode(pHeightMeasureSpec)
        val heightSize = MeasureSpec.getSize(pHeightMeasureSpec)

        when (heightMode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> result = heightSize
        }
        return result
    }
}