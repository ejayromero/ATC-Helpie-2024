//[app](../../../index.md)/[com.example.helpie](../index.md)/[StepInfo](index.md)

# StepInfo

open class [StepInfo](index.md)(val mode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null)

Open class representing a step in a trip.

#### Inheritors

| |
|---|
| [transportInfo](../transport-info/index.md) |
| [walkInfo](../walk-info/index.md) |

## Constructors

| | |
|---|---|
| [StepInfo](-step-info.md) | [androidJvm]<br>constructor(mode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [mode](mode.md) | [androidJvm]<br>open val [mode](mode.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The mode of transportation. |

## Functions

| Name | Summary |
|---|---|
| [giveTime](give-time.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)<br>fun [giveTime](give-time.md)(point: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Function to return the start or end time based on the point parameter. |
| [logValues](log-values.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)<br>fun [logValues](log-values.md)()<br>Function to log the values of the step info. |
