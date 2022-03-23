package com.space.formatter.format

import com.space.models.model.amount.SPCurrencyType

object SPDefaultFormatterFactory : SPFormatterFactory {

    override fun produceLabelAmountFormatter(grouping: Boolean): LongFormatter =
        SPLabelAmountFormatter(SPFractioningStrategies.TrimTrailingZeros)
            .apply {
            fractionPrecision = MAX_FRACTION_DIGITS
            separator = SEPARATOR
            group = grouping
        }

    override fun produceInputAmountFormatter(maxIntDigits: Int): StringFormatter =
        SPInputAmountFormatter().apply {
            maxFractionDigits = MAX_FRACTION_DIGITS
            maxIntegerDigits = maxIntDigits
            separator = SEPARATOR
        }

    override fun produceCardFormatter() = SPCardNumberFormatter()

    override fun getInputMaxIntDigits() = MAX_INTEGER_DIGITS

    override val defaultCurrency: SPCurrencyType
        get() = SPCurrencyType.UZS

    private const val MAX_FRACTION_DIGITS = 2
    private const val MAX_INTEGER_DIGITS = 10
    private const val SEPARATOR = ' '

}