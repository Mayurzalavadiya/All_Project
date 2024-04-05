package com.toolbar.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import com.example.sample26.ui.base.BaseFragment
import com.toolbar.R
import com.toolbar.databinding.FragmentFirstBinding
import com.toolbar.ui.activity.MainActivity

class FirstFragment : BaseFragment<FragmentFirstBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachedToParent: Boolean
    ): FragmentFirstBinding {
        return FragmentFirstBinding.inflate(inflater, container, attachedToParent)
    }

    override fun bindData() {
        setMenu()
        setClickListener()
    }

    fun setMenu() {
        if (activity != null) {
            val activity = activity as MainActivity
            activity.supportActionBar?.title = "FirstFragment"
        }
    }

    private fun setClickListener() {

        binding.buttonLogin.setOnClickListener {
            val secondFragment = SecondFragment()
            loadFragment(secondFragment)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuNotification -> showMessage("Notification")
            R.id.menuShare -> showMessage("Share")
            R.id.menuExit -> showMessage("Exit")
        }
        return super.onOptionsItemSelected(item)
    }
}