package com.test.common.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

class IntentExt {
    companion object {
        fun Context?.openUrl(url: String) = runCatching {
            this?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        fun Fragment.openUrl(url: String) = runCatching {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
        
    }

}