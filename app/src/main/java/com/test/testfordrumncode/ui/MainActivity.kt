package com.test.testfordrumncode.ui

import com.test.common.base.BaseActivity
import com.test.testfordrumncode.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun initView() {
        applyStatusBarInsets(binding.appBarLayout, binding.navHostFragment)
    }

}