package ge.space.design.ui_components.text_fields

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.text_fields.dropdown.SPDropdownComponent
import ge.space.design.ui_components.text_fields.input.SPInputComponent
import ge.space.design.ui_components.text_fields.number.SPNumberComponent
import ge.space.design.ui_components.text_fields.otp.SPOtpComponent
import ge.space.design.ui_components.text_fields.password.SPPasswordComponent
import ge.space.design.ui_components.text_fields.phone.SPPhoneComponent

open class SPTextFieldsComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.text_fields

    override fun getDescriptionResId(): Int = R.string.text_fields_desc

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            SPPasswordComponent(),
            SPOtpComponent(),
            SPPhoneComponent(),
            SPInputComponent(),
            SPDropdownComponent(),
            SPNumberComponent()
        )
    }
}