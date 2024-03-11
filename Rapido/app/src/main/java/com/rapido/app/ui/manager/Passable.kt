package com.rapido.app.ui.manager

interface Passable<in T> {

    fun passData(t: T)

}
