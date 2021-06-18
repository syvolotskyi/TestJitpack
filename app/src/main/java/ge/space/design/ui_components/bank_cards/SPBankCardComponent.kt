package ge.space.design.ui_components.bank_cards

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemBankCardShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutBankCardShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.bank_cards.SPBankCardView

class SPBankCardComponent : SPShowCaseComponent {
    override fun getNameResId(): Int =
        R.string.component_bank_card

    override fun getDescriptionResId(): Int =
        R.string.component_bank_card_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpLayoutBankCardShowCaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val cards = mutableListOf<SPBankCardView>()

            SPButtonStyles.list.forEach { bankCardSample ->
                val itemBinding = SpItemBankCardShowcaseBinding.inflate(
                    environment.requireThemedLayoutInflater(R.style.SPBankCardView),
                    binding.bankCardsLayout,
                    true
                )

                with(itemBinding.bankCard) {
                    accountNumber = bankCardSample.accountNumber
                    bankLogo = bankCardSample.bankLogo
                    amount = bankCardSample.amount
                    bankName = bankCardSample.bankName
                    paySystemUrl = bankCardSample.paySystemUrl
                    cardBackground = bankCardSample.cardBackground
                    payWaveType = bankCardSample.payWaveType
                    status = bankCardSample.bankCardStatus
                    accountNumberStyle = bankCardSample.accountNumberStyle
                    balanceVisible = bankCardSample.balanceVisible
                    cardType = bankCardSample.cardType
                    isCredit = bankCardSample.isCredit
                    hasChip = bankCardSample.hasChip
                    hasPayWave = bankCardSample.hasPayWave
                    isFavorite = bankCardSample.isFavorite
                    accountVisible = bankCardSample.accountVisible
                }

                cards.add(itemBinding.bankCard)
            }

            return binding.root
        }
    }
}