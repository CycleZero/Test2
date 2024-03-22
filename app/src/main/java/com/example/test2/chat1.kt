package com.example.test2

import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test2.databinding.FragmentChat1Binding
import androidx.navigation.fragment.findNavController



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

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_chat1_to_firstfragment)
        }


        animator = (binding.imageView2.drawable as AnimatedVectorDrawable)
        ca= Call(animator)
        binding.button3.setOnClickListener {

              animator.unregisterAnimationCallback(ca)//移除监听的方法

        }


        animator.registerAnimationCallback(ca)
        animator.start()
        if(!(binding.imageView2.drawable as AnimatedVectorDrawable).isRunning)
        {
            (binding.imageView2.drawable as AnimatedVectorDrawable).start()
        }


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