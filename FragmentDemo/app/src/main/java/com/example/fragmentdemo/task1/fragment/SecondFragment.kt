package com.example.fragmentdemo.task1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentFirstBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    private var text: String? = null
    private var string: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            string = requireArguments().getString("String", "Data not Found")
            text = requireArguments().getString("Text", "not")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = string  ?: "Hello, data not available"

        binding.button.text = text
        binding.button.setOnClickListener {

            val firstFragment1 = FirstFragment()
            firstFragment1.arguments = Bundle().apply {
                putString("String", "return first Fragment")
                putInt("Int", 2)
                putString("Text", "Next >")
            }

            childFragmentManager.beginTransaction().add(R.id.childFragment, firstFragment1).commit()
//            (requireActivity() as FragmentDemoActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainer, firstFragment1)
//                .addToBackStack("first")
//                .commit()
        }
    }
}