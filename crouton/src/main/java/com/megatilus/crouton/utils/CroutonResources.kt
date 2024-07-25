package com.megatilus.crouton.utils

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.megatilus.crouton.utils.CroutonType.Custom
import com.megatilus.crouton.utils.CroutonType.Error
import com.megatilus.crouton.utils.CroutonType.Info
import com.megatilus.crouton.utils.CroutonType.Success
import com.megatilus.crouton.utils.CroutonType.Warning

internal object CroutonResources {
    @ColorInt
    fun getPrimaryColor(croutonType: CroutonType): Color = croutonType.primaryColor

    @ColorInt
    fun getSecondaryColor(croutonType: CroutonType): Color = croutonType.secondaryColor

    @DrawableRes
    fun getIcon(croutonType: CroutonType): Int = croutonType.icon

    @ColorInt
    fun getLightColor(croutonType: CroutonType): Color {
        return when (croutonType) {
            is Custom -> croutonType.secondaryColor
            Error -> Color(0xFFFFF3F3)
            Info -> Color(0xFFF3F9FF)
            Success -> Color(0xFFF5FFF3)
            Warning -> Color(0xFFFFF9F3)
        }
    }

    @ColorInt
    fun getDarkColor(croutonType: CroutonType): Color {
        return when (croutonType) {
            is Custom -> croutonType.secondaryColor
            Error -> Color(0xFF1B1818)
            Info -> Color(0xFF181A1B)
            Success -> Color(0xFF191B18)
            Warning -> Color(0xFF1B1A18)
        }
    }
}