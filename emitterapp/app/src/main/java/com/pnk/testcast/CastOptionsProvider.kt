package com.pnk.testcast

import android.content.Context
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.OptionsProvider
import com.google.android.gms.cast.framework.SessionProvider

class CastOptionsProvider : OptionsProvider {
    val CUSTOM_NAMESPACE = "urn:x-cast:custom_namespace"

    override fun getCastOptions(p0: Context?): CastOptions {
        val supportedNamespaces: MutableList<String> = ArrayList()
        supportedNamespaces.add(CUSTOM_NAMESPACE)
        return CastOptions.Builder()
            .setReceiverApplicationId("720A8D7E")
            .setSupportedNamespaces(supportedNamespaces)
            .build()
    }

    override fun getAdditionalSessionProviders(p0: Context?): MutableList<SessionProvider> {
        return mutableListOf()
    }
}
