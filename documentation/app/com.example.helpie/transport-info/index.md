//[app](../../../index.md)/[com.example.helpie](../index.md)/[transportInfo](index.md)

# transportInfo

[androidJvm]\
data class [transportInfo](index.md)(val mode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val startName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val startTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val startTimeEstimated: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val endName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val endTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val endTimeEstimated: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val line: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val startQuay: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val endQuay: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val way: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [StepInfo](../-step-info/index.md)

Data class representing information for a transport step. Subclass of Stepinfo.

## Constructors

| | |
|---|---|
| [transportInfo](transport-info.md) | [androidJvm]<br>constructor(mode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startTimeEstimated: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, endName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, endTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, endTimeEstimated: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, line: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startQuay: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, endQuay: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, way: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [endName](end-name.md) | [androidJvm]<br>val [endName](end-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The name of the end point. |
| [endQuay](end-quay.md) | [androidJvm]<br>val [endQuay](end-quay.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The end quay of the transport. (if train for ex) |
| [endTime](end-time.md) | [androidJvm]<br>val [endTime](end-time.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The end time of the transport step. |
| [endTimeEstimated](end-time-estimated.md) | [androidJvm]<br>val [endTimeEstimated](end-time-estimated.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The estimated end time of the transport step. (according to the data) |
| [line](line.md) | [androidJvm]<br>val [line](line.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The line of the transport. (if bus for ex) |
| [mode](mode.md) | [androidJvm]<br>open override val [mode](mode.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The mode of transportation. |
| [startName](start-name.md) | [androidJvm]<br>val [startName](start-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The name of the start point. |
| [startQuay](start-quay.md) | [androidJvm]<br>val [startQuay](start-quay.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The start quay of the transport. (if train for ex) |
| [startTime](start-time.md) | [androidJvm]<br>val [startTime](start-time.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The start time of the transport step. |
| [startTimeEstimated](start-time-estimated.md) | [androidJvm]<br>val [startTimeEstimated](start-time-estimated.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The estimated start time of the transport step. (according to the data) |
| [way](way.md) | [androidJvm]<br>val [way](way.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The way of the transport. |

## Functions

| Name | Summary |
|---|---|
| [giveTime](../-step-info/give-time.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)<br>fun [giveTime](../-step-info/give-time.md)(point: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Function to return the start or end time based on the point parameter. |
| [logValues](../-step-info/log-values.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)<br>fun [logValues](../-step-info/log-values.md)()<br>Function to log the values of the step info. |
