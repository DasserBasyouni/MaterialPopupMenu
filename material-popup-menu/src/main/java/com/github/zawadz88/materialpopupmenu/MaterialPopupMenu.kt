package com.github.zawadz88.materialpopupmenu

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StyleRes
import android.support.annotation.UiThread
import android.support.v7.widget.MaterialRecyclerViewPopupWindow
import android.view.View
import com.github.zawadz88.materialpopupmenu.internal.PopupMenuAdapter


/**
 * Holds all the required information for showing a popup menu.
 *
 * @param style Style of the popup menu. See [MaterialPopupMenuBuilder.style]
 * @param dropdownGravity Gravity of the dropdown list. See [MaterialPopupMenuBuilder.dropdownGravity]
 * @param sections a list of sections
 *
 * @author Piotr Zawadzki
 */
class MaterialPopupMenu internal constructor(
        internal @StyleRes val style: Int,
        internal val dropdownGravity: Int,
        internal val sections: List<PopupMenuSection>) {

    /**
     * Shows a popup menu in the UI.
     *
     * This must be called on the UI thread.
     * @param context Context
     * @param anchor view used to anchor the popup
     */
    @UiThread
    fun show(context: Context, anchor: View) {
        val popupWindow = MaterialRecyclerViewPopupWindow(context, dropdownGravity, style)
        val adapter = PopupMenuAdapter(context, style, sections, { popupWindow.dismiss() })

        popupWindow.adapter = adapter
        popupWindow.anchorView = anchor

        popupWindow.show()
    }

    internal data class PopupMenuSection(
            val title: String?,
            val items: List<PopupMenuItem>
    )

    internal data class PopupMenuItem(
            val label: String,
            @DrawableRes val icon: Int,
            val callback: () -> Unit
    )

}