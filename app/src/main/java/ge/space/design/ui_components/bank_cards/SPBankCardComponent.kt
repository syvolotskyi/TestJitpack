package ge.space.design.ui_components.bank_cards

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.bank_cards.card.SPBankCardViewComponent
import ge.space.design.ui_components.bank_cards.chip.chip_icon.SPChipIconViewComponent
import ge.space.design.ui_components.bank_cards.chip.digital_chip.SPDigitalChipViewComponent
import ge.space.design.ui_components.bank_cards.chip.list_digital_chip.SPChipListViewComponent
import ge.space.design.ui_components.bank_cards.chip.new_credit_card.SPNewCreditCardComponent
import ge.space.design.ui_components.bank_cards.chip.primary_chip.SPPrimaryChipViewComponent
import ge.space.design.ui_components.bank_cards.chip.secondary_chip.SPSecondaryChipViewComponent
import ge.space.design.ui_components.bank_cards.chip.small_empty.SPEmptyChipViewComponent

class SPBankCardComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_bank_card

    override fun getDescriptionResId(): Int = R.string.component_bank_card_description

    override fun getSubComponents(): List<ShowCaseComponent> =
        listOf(
            SPBankCardViewComponent(),
            SPEmptyChipViewComponent(),
            SPChipIconViewComponent(),
            SPPrimaryChipViewComponent(),
            SPDigitalChipViewComponent(),
            SPSecondaryChipViewComponent(),
            SPChipListViewComponent(),
            SPNewCreditCardComponent()
        )
}