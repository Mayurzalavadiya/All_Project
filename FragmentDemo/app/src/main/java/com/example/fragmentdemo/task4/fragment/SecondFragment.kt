package com.example.fragmentdemo.task4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentdemo.Const
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    companion object {
        fun fragmentBundle(name: String): Fragment {
            val fragment = SecondFragment()
            fragment.arguments = Bundle().apply {
                putString(Const.Title, name)
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            textView.text = requireArguments().getString(com.example.fragmentdemo.Const.Title)
            imageView.setImageResource(setImage())
        }
    }


    private fun setImage():Int{
        return arrayOf(R.drawable.image5, R.drawable.image4, R.drawable.image7, R.drawable.image6).random()
    }
}