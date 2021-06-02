package ge.space.design.ui_components.buttons.default_button

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemButtonsShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.buttons.horizontal_button.SPHorizontalButtonsComponentSP
import ge.space.design.ui_components.buttons.vertical_button.SPVerticalButtonsComponentSP
import ge.space.spaceui.databinding.SpButtonLayoutBinding
import ge.space.ui.base.SPBaseButton


class SPDefaultButtonsComponentSP : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.buttons

    override fun getDescriptionResId(): Int = R.string.button_description

    override fun getComponentClass(): Class<*>? = FactorySP::class.java

    override fun getIconResId(): Int {
        return R.drawable.ic_launcher_foreground
    }

    override fun getSubComponents(): List<SPShowCaseComponent> {
        return listOf(
            SPVerticalButtonsComponentSP(),
            SPHorizontalButtonsComponentSP()
        )
    }

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environmentSP.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPBaseButton<SpButtonLayoutBinding>>()
            SPButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId
                val supportsDisable = buttonSample.supportsDisabled


                val itemBinding = SpItemButtonsShowcaseBinding.inflate(
                    environmentSP.requireThemedLayoutInflater(resId),
                    layoutBinding.buttonsLayout,
                    true
                )

                if (!supportsDisable)
                    itemBinding.disableCheck.visibility = View.GONE

                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(resId)
                    text = resName.substringAfter(".", resName)
                }

                buttons.add(itemBinding.button)

                itemBinding.button.setOnClickListener {
                    Toast.makeText(environmentSP.context, "Clicked", Toast.LENGTH_SHORT).show()
                }

                itemBinding.disableCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isEnabled = !isChecked
                }

                itemBinding.wrapContentCheck.setOnCheckedChangeListener { _, isChecked ->
                    with(itemBinding.button) {
                        val newParams = layoutParams

                        newParams.width = if (isChecked) {
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        } else {
                            LinearLayout.LayoutParams.MATCH_PARENT
                        }

                        layoutParams = newParams
                    }
                }

                layoutBinding.textInput.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        buttons.onEach { it.text = s.toString() }
                    }
                })
                itemBinding.button.style(buttonSample.resId)
                itemBinding.button.text = "Button"
            }
            return layoutBinding.root

        }
    }

}