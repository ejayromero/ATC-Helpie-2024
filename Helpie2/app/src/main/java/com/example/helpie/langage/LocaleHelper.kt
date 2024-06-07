package com.example.helpie.langage
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import java.util.Locale

/**
 * Helper object for managing the locale settings of the application.
 */
object LocaleHelper {

    /**
     * Sets the locale of the application.
     *
     * @param context The context of the application.
     * @param newLocale The new locale to be set.
     * @return The context with the updated locale.
     */
    internal fun setLocale(context: Context, newLocale: String): Context {
        val locale = Locale(newLocale)
        return baseSetLocale(context, locale)
    }

    /**
     * Sets the base locale configuration of the application.
     *
     * @param context The context of the application.
     * @param newLocale The new locale to be set.
     * @return The context with the updated locale configuration.
     */
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
