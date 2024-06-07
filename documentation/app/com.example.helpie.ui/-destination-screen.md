//[app](../../index.md)/[com.example.helpie.ui](index.md)/[DestinationScreen](-destination-screen.md)

# DestinationScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [DestinationScreen](-destination-screen.md)(_uiState: [UiState](../com.example.helpie/-ui-state/index.md), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), registeredLocation: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../com.example.helpie/-localisation/index.md)&gt;, modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, showDialog: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), onRequest: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, setTarget: ([Localisation](../com.example.helpie/-localisation/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, setLocalisationAddress: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../com.example.helpie/-localisation/index.md)&gt;, [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { _, _, _,_ -&gt; }, switchDialog: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})

Composable function for displaying the destination screen.

#### Parameters

androidJvm

| | |
|---|---|
| _uiState | The current UI state. |
| context | The context. |
| registeredLocation | The list of registered locations. |
| modifier | The modifier to be applied to the composable. |
| showDialog | Flag indicating whether to show the dialog. |
| onRequest | The action to be performed when requesting. |
| setTarget | The action to be performed when setting the target. |
| setLocalisationAddress | The action to be performed when setting the localisation address. |
| switchDialog | The action to be performed when switching the dialog. |
