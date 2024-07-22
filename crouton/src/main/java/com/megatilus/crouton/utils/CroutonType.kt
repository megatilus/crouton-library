package com.megatilus.crouton.utils

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.megatilus.crouton.R
import com.megatilus.crouton.utils.CroutonType.Custom
import com.megatilus.crouton.utils.CroutonType.Error
import com.megatilus.crouton.utils.CroutonType.Info
import com.megatilus.crouton.utils.CroutonType.Success
import com.megatilus.crouton.utils.CroutonType.Warning

/**
 * Represents the different visual styles available for a Crouton.
 *
 * Each style is defined by a primary color, a secondary color, and an icon.
 * You can use one of the predefined styles or create your own custom style.
 *
 * **Important:** Do not attempt to directly instantiate the [CroutonType] class using its constructor.
 * Always use one of the predefined subclasses or the [Custom] subclass to create a new style.
 *
 * **Predefined Styles:**
 *
 * * **[Success]** - Indicates a successful operation. Uses green as the primary color and success icon.
 * * **[Error]** - Indicates an error. Uses red as the primary color and error icon.
 * * **[Warning]** - Indicates a warning. Uses orange as the primary color and warning icon.
 * * **[Info]** - Represents informational messages. Uses blue as the primary color and info icon.
 *
 * **Creating a Custom Style:**
 *
 * To create a custom style, use the [CroutonType.Custom] data class.
 */
sealed class CroutonType private constructor(
    @ColorInt  val primaryColor: Color,
    @ColorInt val secondaryColor: Color,
    @DrawableRes val icon: Int
) {
    data object Success: CroutonType(Color(0xFFA1FF97), Color(0xFF365330), R.drawable.ic_success)
    data object Error: CroutonType(Color(0xFFFF9797), Color(0xFF533030), R.drawable.ic_error)
    data object Warning: CroutonType(Color(0xFFFFD997), Color(0xFF534530), R.drawable.ic_warning)
    data object Info: CroutonType(Color(0xFF97C6FF), Color(0xFF304153), R.drawable.ic_info)
    data class Custom(
        @ColorInt private val customPrimaryColor: Color,
        @ColorInt private val customSecondaryColor: Color,
        @DrawableRes private val customIcon: Int
    ): CroutonType(customPrimaryColor, customSecondaryColor, customIcon)
}