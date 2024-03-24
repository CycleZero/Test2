package com.example.test2

import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.test2.databinding.FragmentChat1Binding
import androidx.appcompat.app.AppCompatActivity


/**
 * A simple [Fragment] subclass.
 * Use the [chat1.newInstance] factory method to
 * create an instance of this fragment.
 */
class chat1 : Fragment() {

    private var _binding: FragmentChat1Binding? = null
    private val binding get() = _binding!!
    public lateinit var t:T
    public lateinit var animator :AnimatedVectorDrawable
    private lateinit var ca:Call
    private lateinit var ca2:Call2
    private var isCycleChecked = false
    private  var isEss=true
    private lateinit var alphatext: AlphaAnimation

    private var txtCount=0
    private   var handler: Handler= Handler()

    private var txt:Array<String> = arrayOf(
        "你好",
        "你好，我是Another Me，一个由先进人工智能大模型驱动的应用程序，协助决策，智能推送，喜好分析。我被作为该高校的AI项目推出，期待与你共度的故事",
        "这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二这是测试字符串二"
    )












    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentChat1Binding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


     //   animator = (binding.imageView2.drawable as AnimatedVectorDrawable)
     //   ca= Call(animator)
      //  ca2=Call2(binding)
      //  binding.imageView2.setImageState(intArrayOf(android.R.attr.state_checked), true)
        binding.button3.setOnClickListener {
/*
            isCycleChecked= !isCycleChecked
            val stateSet = intArrayOf(android.R.attr.state_checked * (if (isCycleChecked) 1 else -1))
            binding.imageView2.setImageState(stateSet, true)
*/
       //     binding.imageView2.setActivated(!binding.imageView2.isActivated());
                if(isEss)
                {
                    isEss=false
                    binding.textView.clearAnimation()
                    binding.textView.setVisibility(View.INVISIBLE)



                }

                isCycleChecked = !isCycleChecked
                binding.imageView2.setImageState(
                    intArrayOf(if (isCycleChecked) android.R.attr.state_checked else -android.R.attr.state_checked),
                    true
                )
            if(isCycleChecked)
            {
                handler.postDelayed(object :Runnable{
                    override fun run() {
                        binding.textViewMain.setText(txt[txtCount],AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_text),50)
                        if(txtCount<txt.size-1)
                        {
                            txtCount++
                        }
                    }
                },500)
                //binding.textViewMain.setText(txt[txtCount],AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_text),100)



            }
            else
            {
                //binding.textViewMain.setText("汉语",AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_text),1)
                binding.textViewMain.removeAllViews()
            }


        //    (binding.imageView2.drawable as AnimatedVectorDrawable).start()
        }
        alphatext=AlphaAnimation(0.0f,1.0f)
        alphatext.setDuration(2000)
        alphatext.setRepeatMode(Animation.REVERSE)
        alphatext.setRepeatCount(Animation.INFINITE)
        alphatext.setInterpolator(object : AccelerateDecelerateInterpolator(){})

        binding.textView.startAnimation(alphatext)
    //    binding.textView.clearAnimation()




      //  animator.registerAnimationCallback(ca)
       // animator.start()
        /*
        if(!(binding.imageView2.drawable as AnimatedVectorDrawable).isRunning)
        {
            (binding.imageView2.drawable as AnimatedVectorDrawable).start()
        }
*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class T(var a: AnimatedVectorDrawable) :Thread()
{
    var play=false
    override fun run() {
        while(play)
        {
            if(!a.isRunning)
            {
                a.start()
            }
        }
    }
}
class Call(var a: AnimatedVectorDrawable) :Animatable2.AnimationCallback(){
    override fun onAnimationEnd(drawable: Drawable?) {
        super.onAnimationEnd(drawable)
        a.start()
    }
}
class Call2(var a: FragmentChat1Binding) :Animatable2.AnimationCallback(){
    override fun onAnimationEnd(drawable: Drawable?) {
        super.onAnimationEnd(drawable)
        a.imageView2.setImageResource(R.drawable.cycleanimbind2)
    }
}