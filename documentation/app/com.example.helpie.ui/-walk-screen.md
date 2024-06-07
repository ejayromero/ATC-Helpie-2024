//[app](../../index.md)/[com.example.helpie.ui](index.md)/[WalkScreen](-walk-screen.md)

# WalkScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [WalkScreen](-walk-screen.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, stepInfo: [walkInfo](../com.example.helpie/walk-info/index.md), lauchMaps: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})

A composable function representing the WalkScreen. This screen displays information and options for walking to the next step.

#### Parameters

androidJvm

| | |
|---|---|
| modifier | The modifier to be applied to the layout. |
| stepInfo | The information about the current walking step. |
| lauchMaps | The callback function to launch maps when the &quot;Navigate&quot; button is clicked. |
