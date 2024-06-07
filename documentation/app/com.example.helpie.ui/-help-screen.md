//[app](../../index.md)/[com.example.helpie.ui](index.md)/[HelpScreen](-help-screen.md)

# HelpScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [HelpScreen](-help-screen.md)(usePhone: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), phoneNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), outlineNumber: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier)

Composable function for displaying help information.

#### Parameters

androidJvm

| | |
|---|---|
| usePhone | Flag indicating whether to use the phone number provided directly or the outline number. |
| phoneNumber | The phone number to be dialed. |
| outlineNumber | The outline number to be dialed if usePhone is false. |
| modifier | The modifier to be applied to the composable. |
