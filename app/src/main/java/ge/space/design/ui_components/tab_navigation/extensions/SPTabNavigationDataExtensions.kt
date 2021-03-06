package ge.space.design.ui_components.tab_navigation.extensions

import android.content.Context
import com.example.spacedesignsystem.R
import ge.space.ui.components.tab_navigation.data.SPTabNavigationModel

/**
 * Sets two elements in Navigation View
 */
fun createNavigationWithTwoTabs(context: Context): MutableList<SPTabNavigationModel> {
    return mutableListOf<SPTabNavigationModel>().apply {
        add(
            SPTabNavigationModel(
                text = context.getString(R.string.component_tab_navigation_by_card),
                image = R.drawable.ic_bank_24_regular
            )
        )
        add(
            SPTabNavigationModel(
                text = context.getString(R.string.component_tab_navigation_by_number),
                image = R.drawable.ic_contacts_book_24_regular
            )
        )
    }
}

/**
 * Sets three elements in Navigation View
 */
fun createNavigationWithThreeTabs(context: Context): MutableList<SPTabNavigationModel> {
    return mutableListOf<SPTabNavigationModel>().apply {
        add(
            SPTabNavigationModel(
                text = context.getString(R.string.component_tab_navigation_by_card),
                image = R.drawable.ic_bank_24_regular
            )
        )
        add(
            SPTabNavigationModel(
                text = context.getString(R.string.component_tab_navigation_by_number),
                image = R.drawable.ic_contacts_book_24_regular
            )
        )
        add(
            SPTabNavigationModel(
                text = context.getString(R.string.component_tab_navigation_by_link),
                image = R.drawable.ic_share_android_24_regular
            )
        )
    }
}
