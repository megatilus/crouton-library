package com.megatilus.crouton.utils

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.megatilus.crouton.utils.CroutonType.Custom
import com.megatilus.crouton.utils.CroutonType.Error
import com.megatilus.crouton.utils.CroutonType.Info
import com.megatilus.crouton.utils.CroutonType.Success
import com.megatilus.crouton.utils.CroutonType.Warning

object CroutonResources {
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
            Error -> Color(0xFFFFF1F1)
            Info -> Color(0xFFF1FAFF)
            Success -> Color(0xFFF1FFF1)
            Warning -> Color(0xFFFFF8F1)
        }
    }

    @ColorInt
    fun getDarkColor(croutonType: CroutonType): Color {
        return when (croutonType) {
            is Custom -> croutonType.secondaryColor
            Error -> Color(0xFF221D1D)
            Info -> Color(0xFF1D2022)
            Success -> Color(0xFF1E221D)
            Warning -> Color(0xFF221F1D)
        }
    }
}