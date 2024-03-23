package com.example.test2

import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test2.databinding.FragmentChat1Binding


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


     //   animator = (binding.imageView2.drawable as AnimatedVectorDrawable)
     //   ca= Call(animator)
      //  ca2=Call2(binding)

        binding.button3.setOnClickListener {
/*
            isCycleChecked= !isCycleChecked
            val stateSet = intArrayOf(android.R.attr.state_checked * (if (isCycleChecked) 1 else -1))
            binding.imageView2.setImageState(stateSet, true)
*/
       //     binding.imageView2.setActivated(!binding.imageView2.isActivated());
            isCycleChecked = !isCycleChecked
           binding.imageView2.setImageState(intArrayOf(if (isCycleChecked) android.R.attr.state_checked else -android.R.attr.state_checked), true)
        //    (binding.imageView2.drawable as AnimatedVectorDrawable).start()
        }


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