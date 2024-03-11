package com.homey.app.ui.home.settings.fragment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.homey.app.R
import com.homey.app.databinding.FragmentFaqBinding
import com.homey.app.ui.base.BaseFragment
import com.homey.app.ui.home.settings.adapter.AddMoneyAdapter
import com.homey.app.ui.home.settings.adapter.FAQAdapter
import com.homey.app.ui.home.settings.model.FAQData
import java.util.ArrayList
import java.util.Locale

class FaqFragment : BaseFragment<FragmentFaqBinding>() {

    lateinit var faqAdapter: FAQAdapter
    private var itemList = mutableListOf<FAQData>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentFaqBinding {
        return FragmentFaqBinding.inflate(inflater, container, attachToRoot)
    }

    override fun bindData() {
        setSearch()
        setUpAdapter()
    }

    private fun setUpAdapter() = with(binding) {
        itemList = getFAQData()
        faqAdapter = FAQAdapter(itemList)
        recyclerview.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerview.adapter = faqAdapter
    }

    private fun setSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                filterList(newText)
                return true
            }

        })
    }

/*
    fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<FAQData>()
            for (i in itemList) {
                if (i.question.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                showMessage("No Data found")
            } else {
                faqAdapter.setFilteredList(filteredList)
            }
        }
    }*/

    fun getFAQData(): MutableList<FAQData> = mutableListOf<FAQData>().apply {
        add(FAQData(getString(R.string.textview_what_does_lorem_ipsum_mean),getString(R.string.textview_answer)))
        add(FAQData(getString(R.string.textview_what_does_lorem_ipsum_mean),getString(R.string.textview_answer)))
        add(FAQData(getString(R.string.textview_what_does_lorem_ipsum_mean),getString(R.string.textview_answer)))
    }

}