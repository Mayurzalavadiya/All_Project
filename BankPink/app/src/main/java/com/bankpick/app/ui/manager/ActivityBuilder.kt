package com.bankpick.app.ui.manager

import android.content.Intent
import android.os.Bundle
import androidx.annotation.UiThread
import androidx.core.util.Pair
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import com.bankpick.app.ui.base.BaseFragment


@UiThread
interface ActivityBuilder {

    fun start()

    fun addBundle(bundle: Bundle): ActivityBuilder

    fun addSharedElements(pairs: List<Pair<View, String>>): ActivityBuilder

    fun byFinishingCurrent(): ActivityBuilder

    fun byFinishingAll(): ActivityBuilder

    fun <T : BaseFragment<*>> setPage(page: Class<T>): ActivityBuilder

    /**
     * This method has been deprecated.
     * Use forResult(startForResult: ActivityResultLauncher<Intent>)
     */
    @Deprecated("This method has been deprecated")
    fun forResult(requestCode: Int): ActivityBuilder

    fun forResult(startForResult: ActivityResultLauncher<Intent>): ActivityBuilder

    fun shouldAnimate(isAnimate: Boolean): ActivityBuilder

}