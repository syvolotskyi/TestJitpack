package ge.space.ui.components.text_fields.input.phone_input

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.widget.TextViewCompat
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldPhoneLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView

/**
 * Field view extended from [SPTextFieldBaseView] that allows
 * to change EditorAction and sets the mask.
 *
 */
class SPTextFieldPhone @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPEditText_Masked
) : SPTextFieldBaseView<SpTextFieldPhoneLayoutBinding>(context, attrs, defStyleAttr) {

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextFieldBaseView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPTextFieldBaseView_style,
                    defStyleRes
                )
            )
        }

        inputTextBinding.etInputField.mask = resources.getString(R.string.phone_mask)

    }

    override var text: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.getRawText()
        set(value) {
            field = value

            inputTextBinding.etInputField.setText(value)
        }

    override var hint: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.hint.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.hint = value
        }

    fun setOnEditorActionListener(listener: TextView.OnEditorActionListener) {
        with(inputTextBinding.etInputField) {
            setImeActionEnabled(true)
            onActionListener = listener
        }
    }

    override fun addTextChangedListener(watcher: TextWatcher) {
        inputTextBinding.etInputField.addTextChangedListener(watcher)
    }

    override fun removeTextChangedListener(watcher: TextWatcher) {
        inputTextBinding.etInputField.addTextChangedListener(watcher)
    }

    override fun setOnFocusChangeListener(listener: OnFocusChangeListener) {
        inputTextBinding.etInputField.onFocusChangeListener = listener
    }

    override fun getChildViewBinding(): SpTextFieldPhoneLayoutBinding {
        return SpTextFieldPhoneLayoutBinding.inflate(LayoutInflater.from(context), this, false)
    }

    override fun handleImeOption() {
        inputTextBinding.etInputField.imeOptions = imeOption
    }

    /**
     * Sets a style for the SPButton view.
     *
     * <p>
     * Default style theme is SPBaseView.SPBaseButton style.
     * <p>
     *
     * @param defStyleRes [Int] style resource id
     */
    override fun setTextFieldStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPTextFieldInput)

        styleAttrs.run {

            val textAppearance = getResourceId(
                R.styleable.SPTextFieldBaseView_android_textAppearance,
                R.style.h600_bold_text_field_phone
            )
            updateTextAppearance(textAppearance)
            recycle()
        }

    }

    override fun updateTextAppearance(textAppearance: Int) =
        inputTextBinding.etInputField.setTextStyle(textAppearance)

}