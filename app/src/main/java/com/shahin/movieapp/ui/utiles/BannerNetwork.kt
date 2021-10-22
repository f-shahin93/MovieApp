package com.shahin.movieapp.ui.utiles

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.sergivonavi.materialbanner.Banner
import com.shahin.movieapp.R

fun Context.bannerNetwork(parentViewGroup: ViewGroup, isOnline : (context:Context) -> Boolean) {
    var banner : Banner? = null
    banner = Banner.Builder(this)
        .setParent(parentViewGroup)
        .setIcon(R.drawable.ic_alart)
        .setMessage("You have lost connection to the Internet. This app is offline.")
        .setLeftButton("Dismiss") { banner ->
            banner.dismiss()
        }
        .setRightButton("Turn on wifi") { _ ->
            if (isOnline.invoke(this)) {
                banner?.visibility = View.GONE
                banner?.dismiss()
            } else {
                banner?.show()
                banner?.visibility = View.VISIBLE
            }
        }
        .create()

    banner.show()
    banner.visibility = View.VISIBLE
}