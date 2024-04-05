package com.example.fragmentdemo.task4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdemo.Const
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentFirst2Binding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirst2Binding

    companion object {
        fun fragmentBundle(name: String, image: Int): Fragment {
            val fragment = FirstFragment()
            fragment.arguments = Bundle().apply {
                putString(Const.Title, name)
                putInt(Const.Image, image)
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirst2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        super.onViewCreated(view, savedInstanceState)

        setData()


    }

    private fun setData() = with(binding){
        arguments?.let { 
            textView.text = requireArguments().getString(Const.Title)
            imageView.setImageResource(requireArguments().getInt(Const.Image))
        }
    }

}