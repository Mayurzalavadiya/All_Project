package com.example.fragmentdemo.task2.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.fragmentdemo.databinding.FragmentTermsAndConditionsBinding


class TermsAndConditionsFragment : Fragment() {

    private lateinit var binding: FragmentTermsAndConditionsBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTermsAndConditionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("TAG", "Fragment back pressed invoked")
                    val fm = requireActivity().supportFragmentManager
                    for (i in 0 until fm.backStackEntryCount) {

                        Log.i("TAG", "onViewCreated: ${fm.getBackStackEntryAt(i).name}")

                    }
                }
            }
        )
//        requireActivity().onBackPressedDispatcher.addCallback {
//            if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
//
//                val fm = requireActivity().supportFragmentManager
//
//                for (i in 0 until fm.backStackEntryCount) {
//
//                    fm.popBackStack(LoginFragment::class.java.simpleName, 0)
//                        Log.i("TAG", "onViewCreated: ${fm.getBackStackEntryAt(i).name}")
////                        fm.popBackStack()
//
//                }
//            }
//        }
    }
}