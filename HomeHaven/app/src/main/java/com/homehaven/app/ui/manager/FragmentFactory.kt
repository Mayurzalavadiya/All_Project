package com.homehaven.app.ui.manager

import com.homehaven.app.ui.base.BaseFragment


object FragmentFactory {

    fun <T : BaseFragment<*>> getFragment(aClass: Class<T>): T? {

        try {
            return aClass.newInstance()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        return null
    }
}
