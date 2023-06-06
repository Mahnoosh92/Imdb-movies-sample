package com.mahnoosh.core.utils.string

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultContentRetrieval @Inject constructor(@ApplicationContext private val context: Context) :
    ContentRetrieval {
    override fun getString(id: Int) = context.getString(id)
}