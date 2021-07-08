package ge.space.ui.components.text_fields.input.dropdown

import android.content.Context
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.IdRes
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTextFieldDropdownBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.components.text_fields.input.utils.extension.SPDropdownItemModel
import ge.space.ui.components.text_fields.input.utils.extension.buildWithDropdownItemModel
import ge.space.ui.util.extension.loadImageUrl

class SPTextFieldDropdown<T> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPTextFieldBaseView<SpTextFieldDropdownBinding>(context, attrs, defStyleAttr) {

    /**
     * Binding a item view after selecting
     */
    var bindViewValue: (item: T) -> Unit = { _ -> }

    /**
     * Sets a image resource
     */
    @IdRes
    var src = 0
        set(value) {
            field = value

            inputTextBinding.ivLeftImage.setImageResource(src)
        }

    /**
     * Sets a image url
     */
    var imageUrl = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            context.loadImageUrl(
                value,
                inputTextBinding.ivLeftImage
            )
        }

    /**
     * Sets a image visible state
     */
    var isIconVisible = true
        set(value) {
            field = value

            inputTextBinding.ivLeftImage.isVisible = field
        }

    /**
     * Sets a default text
     */
    var defaultText = SPBaseView.EMPTY_TEXT
        set(value) {
            field = value

            inputTextBinding.etInputField.text = defaultText
        }


    /**
     * Sets a default image
     */
    @IdRes
    var defaultIcon = 0
        set(value) {
            field = value

            inputTextBinding.ivLeftImage.setImageResource(defaultIcon)
        }


    override var text: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.text.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.text = value
        }

    override var hint: String = SPBaseView.EMPTY_TEXT
        get() = inputTextBinding.etInputField.hint.toString()
        set(value) {
            field = value

            inputTextBinding.etInputField.hint = value
        }

    override fun getChildViewBinding(): SpTextFieldDropdownBinding {
        return SpTextFieldDropdownBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )
    }

    override fun setTextFieldStyle(defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.sp_text_field_dropdown)

        styleAttrs.run {
            isIconVisible = getBoolean(R.styleable.sp_text_field_dropdown_isIconVisible, false)
            defaultText =
                getString(R.styleable.sp_text_field_dropdown_defaultText).orEmpty()
            defaultIcon =
                getResourceId(R.styleable.sp_text_field_dropdown_defaultIcon, 0)

            recycle()
        }

        setOnClickListener { onDropDownClick() }
    }

    /**
     * While bottom sheets not implemented yet just show toast
     */
    private fun onDropDownClick() =
        Toast.makeText(inputTextBinding.root.context, "Dropdown click", Toast.LENGTH_SHORT).show()

    override fun handleImeOption() {
        inputTextBinding.etInputField.imeOptions = imeOption
    }

    override fun updateTextAppearance(textAppearance: Int) =
        TextViewCompat.setTextAppearance(inputTextBinding.etInputField, textAppearance)

    fun SPTextFieldDropdown<SPDropdownItemModel>.buildWithItemModel() =
        buildWithDropdownItemModel()

    override fun addTextChangedListener(watcher: TextWatcher) =
        inputTextBinding.etInputField.addTextChangedListener(watcher)

    override fun removeTextChangedListener(watcher: TextWatcher)  =
        inputTextBinding.etInputField.addTextChangedListener(watcher)

}
