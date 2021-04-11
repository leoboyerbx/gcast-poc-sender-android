package com.pnk.testcast

import android.content.Context
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.OptionsProvider
import com.google.android.gms.cast.framework.SessionProvider

class CastOptionsProvider: OptionsProvider {
    override fun getCastOptions(p0: Context?): CastOptions {
        return CastOptions.Builder()
            .setReceiverApplicationId("720A8D7E")
            .build()
    }

    override fun getAdditionalSessionProviders(p0: Context?): MutableList<SessionProvider> {
        return mutableListOf()
    }
}