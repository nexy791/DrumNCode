package com.test.common.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.test.common.alias.InflateActivity
import dev.chrisbanes.insetter.applyInsetter

abstract class BaseActivity<VB : ViewBinding>(
    private val inflate: InflateActivity<VB>,
) : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate.invoke(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    abstract fun initView()

    protected fun applyStatusBarInsets(vararg views: View) =
        views.forEach { it.applyInsetter { type(statusBars = true) { margin() } } }

    protected fun applyNavigationBarInsets(vararg views: View) =
        views.forEach { it.applyInsetter { type(navigationBars = true) { margin() } } }

}