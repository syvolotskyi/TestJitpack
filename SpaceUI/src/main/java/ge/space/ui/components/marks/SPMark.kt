package ge.space.ui.components.marks

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.EMPTY_TEXT
import ge.space.extensions.setSize
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpMarkLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPSetViewStyleInterface
import ge.space.ui.components.buttons.SPButton
import ge.space.ui.components.text_fields.input.base.SPTextFieldBaseView
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

/**
 * Mark view extended from [SPBaseView].
 *
 * @property hasBorder [Boolean] value which applies a border.
 * @property textAppearance [Int] value sets text Appearance for initials marks
 */
class SPMark @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPMark_Size40
) : SPBaseView(context, attrs, defStyleAttr), SPSetViewStyleInterface {


    /**
     * Allows to hide or show a border for the view
     */
    var hasBorder: Boolean = false
        set(value) {
            field = value

            handleBorder()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = SPTextFieldBaseView.DEFAULT_INT

    /**
     * Changes the sizes of the add image view
     */
    var imageSize: Int = 0

    /**
     * Changes the paddings of the add image view
     */
    var paddings: Int = 0

    /**
     * Inflates and returns [SpMarkLayoutBinding] value
     */
    val binding =
        SpMarkLayoutBinding.inflate(LayoutInflater.from(context), this)


    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPBaseView,
            defStyleAttr
        ) {
            setViewStyle(
                getResourceId(
                    R.styleable.SPBaseView_style,
                    defStyleRes
                )
            )
        }

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPMark,
            defStyleAttr
        ) {
            getResourceId(
                R.styleable.SPMark_android_src, DEFAULT_OBTAIN_VAL
            ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                if (it != DEFAULT_OBTAIN_VAL)
                    setViewData(SPViewData.SPImageResourcesData(it))
            }
            getString(
                R.styleable.SPMark_android_text
            ).handleAttributeAction(EMPTY_TEXT) {
                if (!it.isNullOrEmpty())
                    setViewData(SPViewData.SPTextData(it.take(MAX_LENGTH)))
            }
        }
    }


    override fun setViewStyle(newStyle: Int) {
        with(newStyle) {
            setStyle(this)
            setMarkStyle(this)
        }
    }

    fun setViewData(viewData: SPViewData) {
        val view = createView(viewData)
        binding.markContentWrapper.removeAllViews()
        binding.markContentWrapper.addView(view)
        binding.markContentWrapper.invalidate()
    }

    private fun createView(viewData: SPViewData) = when (viewData) {
        is SPViewData.SPImageUrlData -> viewData.createView(context)
        is SPViewData.SPTextData -> {
            viewData.apply {
                textStyle = textAppearance
                backgroundColor = context.getColorFromAttribute(R.attr.background_tertiary)
            }
            viewData.createView(context)
        }
        is SPViewData.SPImageResourcesData -> {
            viewData.apply {
                tintColor = context.getColorFromAttribute(R.attr.brand_primary)
                height = imageSize
                width = imageSize
                padding = paddings
                backgroundColor = context.getColorFromAttribute(R.attr.background_tertiary)
            }
            viewData.createView(context)
        }
        else -> View(context)
    }

    private fun setMarkStyle(@StyleRes defStyleRes: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(defStyleRes, R.styleable.SPMark)

        styleAttrs.run {
            val chipSize = getDimensionPixelSize(
                R.styleable.SPMark_markHeight, DEFAULT_OBTAIN_VAL
            )
            textAppearance = getResourceId(
                R.styleable.SPMark_android_textAppearance,
                DEFAULT_OBTAIN_VAL
            )
            hasBorder = getBoolean(R.styleable.SPMark_hasBorder, false)
            imageSize = getDimensionPixelSize(
                R.styleable.SPMark_imageSize, DEFAULT_OBTAIN_VAL
            )

            paddings = getDimensionPixelSize(
                R.styleable.SPMark_imagePadding, DEFAULT_OBTAIN_VAL
            )

            binding.markContentWrapper.setSize(chipSize, chipSize)
        }
    }

    private fun handleBorder() {
        if (hasBorder) {
            val borderSize = resources.getDimensionPixelSize(R.dimen.dimen_p_1)
            val padding = resources.getDimensionPixelSize(R.dimen.dimen_p_0_5)

            setPadding(padding, padding, padding, padding)
            changeBorder(
                context.getColorFromAttribute(R.attr.separator_non_opaque),
                borderSize.toFloat()
            )
        } else {
            shadowRadius = 0f
            changeBorder(DEFAULT_OBTAIN_VAL, DEFAULT_OBTAIN_VAL.toFloat())
        }
    }

    companion object {
        private val MAX_LENGTH = 2
    }
}