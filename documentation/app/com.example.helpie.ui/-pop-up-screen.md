//[app](../../index.md)/[com.example.helpie.ui](index.md)/[PopUpScreen](-pop-up-screen.md)

# PopUpScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [PopUpScreen](-pop-up-screen.md)(onDismiss: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onStop: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onRestart: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Composable function for displaying a popup screen. This popup screen asks the user whether they want to stop the current journey or find a new path.

#### Parameters

androidJvm

| | |
|---|---|
| onDismiss | Callback function to be called when the popup is dismissed. |
| onStop | Callback function to be called when the user chooses to stop the journey. |
| onRestart | Callback function to be called when the user chooses to find a new path. |
