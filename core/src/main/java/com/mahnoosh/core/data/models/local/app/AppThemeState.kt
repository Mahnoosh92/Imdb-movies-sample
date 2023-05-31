package com.mahnoosh.core.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppThemeState(
    var darkTheme: Boolean = false,
    var pallet: ColorPallet = ColorPallet.GREEN
): Parcelable
