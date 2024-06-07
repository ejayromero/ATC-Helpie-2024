//[app](../../index.md)/[com.example.helpie.ui](index.md)/[SettingsScreen](-settings-screen.md)

# SettingsScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SettingsScreen](-settings-screen.md)(registeredLocation: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../com.example.helpie/-localisation/index.md)&gt;, setLocalisationName: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../com.example.helpie/-localisation/index.md)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { _, _, _ -&gt; }, setLocalisationAddress: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { _, _ -&gt; }, usePhone: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), outlineNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), phone: ([Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, setPhone: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, debugging: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), switchDebug: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, easyRide: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), switchTicket: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, setLangage: ([String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, currentLangage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

A composable function representing the SettingsScreen. This screen displays various settings options such as language selection, ticket settings, contact options, and registered destinations.

#### Parameters

androidJvm

| | |
|---|---|
| registeredLocation | List of registered locations. |
| setLocalisationName | Callback function to set the name of a registered location. |
| setLocalisationAddress | Callback function to set the address of a registered location. |
| usePhone | Flag indicating whether to use phone contact option. |
| phoneNumber | The phone number for contact. |
| outlineNumber | The outline number for contact. |
| phone | Callback function to toggle phone contact option. |
| setPhone | Callback function to set the phone number. |
| debugging | Flag indicating whether debugging is enabled. |
| switchDebug | Callback function to toggle debugging mode. |
| easyRide | Flag indicating whether to enable easy ride mode. |
| switchTicket | Callback function to toggle ticket settings. |
| setLangage | Callback function to set the language. |
| currentLangage | The currently selected language. |
