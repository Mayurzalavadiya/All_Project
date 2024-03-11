package com.example.listviewdemo.task5.fragment

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listviewdemo.R
import com.example.listviewdemo.databinding.FragmentItemsBinding
import com.example.listviewdemo.task5.adapter.MultiSelectAdapter
import com.example.listviewdemo.task5.listeners.ClickListener
import com.example.listviewdemo.task5.pojo.MultiSelectData

class ItemsFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentItemsBinding


    private val multiSelectAdapter by lazy { MultiSelectAdapter(this) }

    val items = mutableListOf<MultiSelectData>()
    private val selectedItems = arrayListOf<MultiSelectData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addItems()
        setItemAdapter()
        setClickListener()

    }

    private fun addItems() {
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/c/bcd8b1222581_H-8901030764820.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://www.beverlyhillsmagazine.com/wp-content/uploads/Skincare-for-Men-Anti-aging-Products-for-Men-Beauty-Magazine-Beauty-Supplies-Beauty-Product-For-Men-Beverly-Hills-Magazine-2-1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/c/bcd8b1222581_H-8901030764820.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://www.themancompany.com/cdn/shop/files/Perfect-Summer-Ready-Kit.jpg?v=1696938056",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-lSTfdrxt3UqN0sgf4pGCuW9tDlE54-AAbwPpI5UV6kY16yzZClZ5p73XhMlJhPiQhyI&usqp=CAU",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/c/bcd8b1222581_H-8901030764820.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://www.themancompany.com/cdn/shop/files/Perfect-Summer-Ready-Kit.jpg?v=1696938056",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-lSTfdrxt3UqN0sgf4pGCuW9tDlE54-AAbwPpI5UV6kY16yzZClZ5p73XhMlJhPiQhyI&usqp=CAU",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL._AC_UF1000,1000_QL80_.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/1/e/1e2961bNYLOPROF00001_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/7/b7ff307STBOT492_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/9/d/9d846d129671_H-8901030974182.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL._AC_UF1000,1000_QL80_.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/1/e/1e2961bNYLOPROF00001_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/7/b7ff307STBOT492_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/9/d/9d846d129671_H-8901030974182.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://m.media-amazon.com/images/I/61iasFL6iPL._AC_UF1000,1000_QL80_.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/1/e/1e2961bNYLOPROF00001_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/b/7/b7ff307STBOT492_1.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
        items.add(
            MultiSelectData(
                "https://images-static.nykaa.com/media/catalog/product/9/d/9d846d129671_H-8901030974182.jpg",
                "Home",
                "jhhdf hdshg hfghdsfgsdgfsd",
            )
        )
    }

    private fun setItemAdapter() = with(binding) {
        recyclerViewMultipleSelection.adapter = multiSelectAdapter
        recyclerViewMultipleSelection.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        recyclerViewMultipleSelection.addItemDecoration(ItemDecorate())
        multiSelectAdapter.addItem(items)
    }

    private fun setClickListener() = with(binding) {

        val selectItemsFragment = SelectItemsFragment()
        selectItemsFragment.arguments = Bundle().apply {
            putParcelableArrayList("SelectedData", selectedItems)
        }

        textViewCount.setOnClickListener {

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    selectItemsFragment
                )
                .addToBackStack(SelectItemsFragment::class.java.simpleName)
                .commit()
        }
    }

    inner class ItemDecorate : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = resources.getDimensionPixelSize(R.dimen._3dp)
            outRect.left = resources.getDimensionPixelSize(R.dimen._10dp)
            outRect.right = resources.getDimensionPixelSize(R.dimen._5dp)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(positions: ArrayList<Int>, v: View?) {
        selectedItems.clear()
        for (i in 0 until items.size) {
            if (items[i].isSelected) {
                selectedItems.add(items[i])
            }
        }
        Log.e("TAG", "onClick: $selectedItems", )
        binding.textViewCount.text =
            "${resources.getString(R.string.total_item_selected_count)} ${positions.size.toString()}"
    }

}
