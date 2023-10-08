package com.test.testfordrumncode.nav

import androidx.navigation.NavController
import com.test.testfordrumncode.ui.feed.FeedFragmentDirections

class NavExt {

    companion object {

        fun NavController.navigateInfo(id: String) = runCatching {
            val action = FeedFragmentDirections.actionFeedFragmentToInfoFragment(id)
            navigate(action)
        }


    }

}