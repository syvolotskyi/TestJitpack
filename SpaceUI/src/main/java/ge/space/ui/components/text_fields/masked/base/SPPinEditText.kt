package ge.space.ui.components.text_fields.masked.base

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.viewbinding.ViewBinding
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPSetViewStyleInterface
import ge.space.ui.util.extension.handleAttributeAction


/**
 * Field view extended from [SPBaseView] that allows to change its configuration.
 *
 * @property text [String] value which sets a text.
 * @property labelText [String] value which sets a label text.
 * @property descriptionText [String] value which sets a description text.
 * @property maxLength [Int] value which sets a count of symbols.
 */
abstract class SPPinEditText<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr), SPSetViewStyleInterface {

    /**
     * Reference to [VB] instance which is related to ViewBinding
     */
    protected val binding: VB

    /**
     * Lazy property for initialize ViewBinding in constructor
     */
    private val _binding by lazy {
        getViewBinding()
    }

    /**
     * Sets a text
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            updateText(value)
        }

    /**
     * Sets a labelText
     */
    var labelText: String = EMPTY_TEXT
        set(value) {
            field = value

            updateLabel(value)
        }

    /**
     * Sets a labelText
     */
    var descriptionText: String = EMPTY_TEXT
        set(value) {
            field = value

            updateDescription(value)
        }

    /**
     * Sets a error
     */
    var state: SPPinState = SPPinState.UNKNOWN
        set(state) {
            field = state
            handleState()
        }

    /**
     * Sets a maxLength
     */
    var maxLength: Int = DEFAULT_LENGTH
        set(value) {
            field = value

            setMaxLength()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    protected var textAppearance: Int = R.style.h700_medium_caps_label_secondary

    /**
     * Sets a description text appearance
     */
    @StyleRes
    protected var descriptionTextAppearance: Int = R.style.h700_bold_caps_brand_primary

    protected val errorAnimation: Animation by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.sp_shake_anim
        ).apply {
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    resetPin()
                }
            })
        }
    }

    init {

        binding = _binding

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPinEditText,
            defStyleAttr
        ) {
            text = getString(R.styleable.SPPinEditText_android_text).orEmpty()
            labelText = getString(R.styleable.SPPinEditText_pinLabelText).orEmpty()
            descriptionText = getString(R.styleable.SPPinEditText_pinDescriptionText).orEmpty()
            isEnabled = getBoolean(R.styleable.SPPinEditText_android_enabled, true)
            maxLength = getInt(
                R.styleable.SPPinEditText_android_maxLength,
                DEFAULT_LENGTH
            )
            getResourceId(
                R.styleable.SPPinEditText_android_textAppearance,
                DEFAULT_OBTAIN_VAL
            ).handleAttributeAction(
                DEFAULT_OBTAIN_VAL
            ) {
                if (it != DEFAULT_OBTAIN_VAL) textAppearance = it
            }
            getResourceId(
                R.styleable.SPPinEditText_descriptionTextAppearance,
                DEFAULT_OBTAIN_VAL
            ).handleAttributeAction(
                DEFAULT_OBTAIN_VAL
            ) {
                if (it != DEFAULT_OBTAIN_VAL) descriptionTextAppearance = it
            }
            updateTextAppearance()
        }
    }

    override fun setViewStyle(newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setOTPStyle(this)
        }
    }

    abstract fun setOnDescriptionClickListener(listener: () -> Unit)

    private fun setOTPStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPPinEditText)

        styleAttrs.run {
            text = getString(R.styleable.SPPinEditText_android_text).orEmpty()
            getString(R.styleable.SPPinEditText_pinLabelText).handleAttributeAction(
                EMPTY_TEXT
            ) {
                it?.let { labelText = it }
            }
            getString(R.styleable.SPPinEditText_pinDescriptionText).handleAttributeAction(
                EMPTY_TEXT
            ) {
                it?.let { descriptionText = it }
            }

            getResourceId(
                R.styleable.SPPinEditText_android_textAppearance,
                DEFAULT_OBTAIN_VAL
            ).handleAttributeAction(
                DEFAULT_OBTAIN_VAL
            ) {
                if (it != DEFAULT_OBTAIN_VAL) textAppearance = it
            }
            getResourceId(
                R.styleable.SPPinEditText_descriptionTextAppearance,
                DEFAULT_OBTAIN_VAL
            ).handleAttributeAction(
                DEFAULT_OBTAIN_VAL
            ) {
                if (it != DEFAULT_OBTAIN_VAL) descriptionTextAppearance = it
            }
            updateTextAppearance()
        }

    }

    /**
     * Allows to init ViewBinding
     */
    protected abstract fun getViewBinding(): VB
    protected abstract fun updateTextAppearance()
    protected abstract fun updateText(text: String)
    protected abstract fun updateLabel(text: String)
    protected abstract fun updateDescription(text: String)
    protected abstract fun handleState()
    protected abstract fun setMaxLength()
    protected abstract fun focus()
    protected abstract fun resetPin()

    companion object {
        const val DEFAULT_LENGTH = 4
    }
}