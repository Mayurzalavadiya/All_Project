package com.example.fragmentdemo.task1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentdemo.R
import com.example.fragmentdemo.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    private var string: String? = null
    private var text: String? = null
    private var int: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            string = requireArguments().getString("String", "Data not Found")
            text = requireArguments().getString("Text", "not")
            int = requireArguments().getInt("Int")
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

        binding.textView.text = "$string $int " ?: "Hello, data not available"


        binding.button.text = text

        binding.button.setOnClickListener {

            val secondFragment = SecondFragment()
            secondFragment.arguments = Bundle().apply {
                putString("String", "This is second Fragment")
                putString("Text", "<< Pre")
                putInt("Int", 3)
            }

            childFragmentManager.beginTransaction().add(R.id.childFragment, secondFragment).commit()


//            (requireActivity() as FragmentDemoActivity).supportFragmentManager.beginTransaction()
//                .add(R.id.fragmentContainer, secondFragment)
//                .addToBackStack(FirstFragment::class.java.simpleName)
//                .commit()
        }
//        Handler(Looper.getMainLooper()).postDelayed({
//            Log.i("TAG", "onCreate: ${childFragmentManager.fragments[0].tag} ")
//
//        },5000)
    }
}