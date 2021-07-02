package ge.space.design.ui_components.bank_cards.chip.chip_icon

import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPChipSize
import ge.space.ui.components.bank_cards.data.SPChipIconAppearance

data class SPChipIconSupport(
    val size: SPChipSize = SPChipSize.Big,
    val iconAppearance: SPChipIconAppearance = SPChipIconAppearance.Accent,
    val icon: Int = R.drawable.ic_bank_24_regular,
    val photoUrl: String? = null
)

object SPChipIconStyles {
    private const val PHOTO_URL =
        "https://images.ctfassets.net/hrltx12pl8hq/3hkYVkjAgg0vIPv52JXRxU/9418da2d548684a4eec491114762f3b6/Summer_jpg.jpg?fit=fill&w=480&h=270"

    val list = listOf(
        SPChipIconSupport(),
        SPChipIconSupport(
            size = SPChipSize.Small
        ),
        SPChipIconSupport(
            iconAppearance = SPChipIconAppearance.Dark
        ),
        SPChipIconSupport(
            size = SPChipSize.Small,
            iconAppearance = SPChipIconAppearance.Dark
        ),
        SPChipIconSupport(
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            size = SPChipSize.Small,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            iconAppearance = SPChipIconAppearance.Dark,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            size = SPChipSize.Small,
            iconAppearance = SPChipIconAppearance.Dark,
            icon = R.drawable.ic_info_24_regular
        ),
        SPChipIconSupport(
            photoUrl = PHOTO_URL
        ),
        SPChipIconSupport(
            size = SPChipSize.Small,
            photoUrl = PHOTO_URL
        ),
    )
}