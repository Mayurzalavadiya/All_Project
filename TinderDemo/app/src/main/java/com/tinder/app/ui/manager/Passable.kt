package com.tinder.app.ui.manager

interface Passable<in T> {

    fun passData(t: T)

}
