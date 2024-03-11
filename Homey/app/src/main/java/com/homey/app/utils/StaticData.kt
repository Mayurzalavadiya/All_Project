package com.homey.app.utils

import com.homey.app.R
import com.homey.app.ui.home.favorite.model.FavoriteData

class StaticData {

    fun getFavoriteList(): MutableList<FavoriteData> {
        return mutableListOf<FavoriteData>().apply {
            add(FavoriteData(R.drawable.favorite_1, "Sunrise Smart Hotel", "250"))
            add(FavoriteData(R.drawable.favorite_2, "Paris Sand Hotel", "200"))
            add(FavoriteData(R.drawable.favorite_3, "Hotel Light Sky", "230"))
            add(FavoriteData(R.drawable.favorite_1, "Hotel Light Sky", "230"))
            add(FavoriteData(R.drawable.favorite_2, "Hotel Light Sky", "230"))
            add(FavoriteData(R.drawable.favorite_3, "Hotel Light Sky", "230"))
            add(FavoriteData(R.drawable.favorite_1, "Hotel Light Sky", "230"))
        }
    }
}