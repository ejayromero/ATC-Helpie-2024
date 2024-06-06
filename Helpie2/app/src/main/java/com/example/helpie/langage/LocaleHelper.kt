package com.example.helpie.langage
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.Locale

object LocaleHelper {

    internal fun setLocale(context: Context, newLocale: String): Context {
        val locale = Locale(newLocale)
        return baseSetLocale(context, locale)
    }

    private fun baseSetLocale(context: Context, newLocale: Locale): Context {
        var tmpContext = context
        val res = tmpContext.resources
        val configuration = res.configuration
        configuration.setLocale(newLocale)
        val localeList = LocaleList(newLocale)
        LocaleList.setDefault(localeList)
        configuration.setLocales(localeList)
        tmpContext = tmpContext.createConfigurationContext(configuration)
        return tmpContext
    }
}
