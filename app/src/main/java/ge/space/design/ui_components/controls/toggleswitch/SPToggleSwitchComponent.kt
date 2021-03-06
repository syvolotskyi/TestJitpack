package ge.space.design.ui_components.controls.toggleswitch

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutToggleSwitchShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPToggleSwitchComponent : ShowCaseComponent {

    override fun getNameResId() = R.string.toggle_switch

    override fun getDescriptionResId() = R.string.toggle_switch_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutToggleSwitchShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            with(layoutBinding) {
                cbDisable.setOnCheckedChangeListener { _, isChecked ->
                    toggleSwitch.isEnabled = !isChecked
                }
            }

            return layoutBinding.root
        }
    }
}