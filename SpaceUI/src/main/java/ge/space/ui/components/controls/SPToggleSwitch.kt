package ge.space.ui.components.controls

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import com.google.android.material.switchmaterial.SwitchMaterial
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.handleAttributeAction

class SPToggleSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SwitchMaterial(context, attrs, defStyleAttr), SPViewStyling {

    init {
        getContext().withStyledAttributes(attrs, R.styleable.SPToggleSwitch, defStyleAttr) {
            setViewStyle(getResourceId(R.styleable.SPToggleSwitch_style, DEFAULT_OBTAIN_VAL))
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.theme.obtainStyledAttributes(newStyle, R.styleable.SPToggleSwitch).run {
            withStyledAttributes()
            recycle()
        }
    }

    private fun TypedArray.withStyledAttributes() {
        getResourceId(R.styleable.SPToggleSwitch_showText, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                showText = getBoolean(it, false)
            }

        getResourceId(R.styleable.SPToggleSwitch_android_state_enabled, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                isEnabled = getBoolean(it, true)
            }

        getResourceId(R.styleable.SPToggleSwitch_android_state_selected, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                isSelected = getBoolean(it, false)
            }

        getResourceId(R.styleable.SPToggleSwitch_android_thumb, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                setThumbResource(it)
            }

        getResourceId(R.styleable.SPToggleSwitch_track, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                setTrackResource(it)
            }

        getResourceId(R.styleable.SPToggleSwitch_thumbTint, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                // think about using ColorStateList constructor
                thumbTintList = ColorStateList.valueOf(getColor(it, DEFAULT_OBTAIN_VAL))
            }

        getResourceId(R.styleable.SPToggleSwitch_trackTint, DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(DEFAULT_OBTAIN_VAL) {
                trackTintList = ColorStateList.valueOf(getColor(it, DEFAULT_OBTAIN_VAL))
            }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
    }
}