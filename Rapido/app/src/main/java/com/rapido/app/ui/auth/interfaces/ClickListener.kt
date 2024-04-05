package com.rapido.app.ui.auth.interfaces

import android.view.View
import com.rapido.app.data.pojo.response.AddressResponse

interface ClickListener {
    fun click(address:AddressResponse, v: View?)
}