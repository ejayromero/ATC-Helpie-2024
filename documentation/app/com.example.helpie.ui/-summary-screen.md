//[app](../../index.md)/[com.example.helpie.ui](index.md)/[SummaryScreen](-summary-screen.md)

# SummaryScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SummaryScreen](-summary-screen.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, summary: [TripSummary](../com.example.helpie/-trip-summary/index.md), steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[StepInfo](../com.example.helpie/-step-info/index.md)&gt;, onNext: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})

A composable function representing the SummaryScreen. This screen displays a summary of the trip with the details of each step.

#### Parameters

androidJvm

| | |
|---|---|
| modifier | The modifier for the SummaryScreen layout. |
| summary | The summary of the trip. |
| steps | The list of steps in the trip. |
| onNext | Callback function to navigate to the next screen. |
