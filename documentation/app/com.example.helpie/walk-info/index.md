//[app](../../../index.md)/[com.example.helpie](../index.md)/[walkInfo](index.md)

# walkInfo

[androidJvm]\
data class [walkInfo](index.md)(val mode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val startName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val startLongitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, val startLatitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, val endName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var endLongitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, var endLatitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, val startTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val endTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val length: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, val buffer: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) : [StepInfo](../-step-info/index.md)

Data class representing information for a walk step. Subclass of Stepinfo.

## Constructors

| | |
|---|---|
| [walkInfo](walk-info.md) | [androidJvm]<br>constructor(mode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startLongitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, startLatitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, endName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, endLongitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, endLatitude: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, startTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, endTime: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, duration: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, length: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0, buffer: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [buffer](buffer.md) | [androidJvm]<br>val [buffer](buffer.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The buffer time for the walk. (how many time do we have ? how many time does it take ?) |
| [duration](duration.md) | [androidJvm]<br>val [duration](duration.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The duration of the walk. |
| [endLatitude](end-latitude.md) | [androidJvm]<br>var [endLatitude](end-latitude.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)?<br>The end latitude. |
| [endLongitude](end-longitude.md) | [androidJvm]<br>var [endLongitude](end-longitude.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)?<br>The end longitude. |
| [endName](end-name.md) | [androidJvm]<br>val [endName](end-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>Name of the end point. |
| [endTime](end-time.md) | [androidJvm]<br>val [endTime](end-time.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The end time of the walk. |
| [length](length.md) | [androidJvm]<br>val [length](length.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0<br>The length of the walk in meters. |
| [mode](mode.md) | [androidJvm]<br>open override val [mode](mode.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The mode of transportation. |
| [startLatitude](start-latitude.md) | [androidJvm]<br>val [startLatitude](start-latitude.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0<br>The start latitude. |
| [startLongitude](start-longitude.md) | [androidJvm]<br>val [startLongitude](start-longitude.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)? = 0.0<br>The start longitude. |
| [startName](start-name.md) | [androidJvm]<br>val [startName](start-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>Name of the start point. |
| [startTime](start-time.md) | [androidJvm]<br>val [startTime](start-time.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The start time of the walk. |

## Functions

| Name | Summary |
|---|---|
| [giveTime](../-step-info/give-time.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)<br>fun [giveTime](../-step-info/give-time.md)(point: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Function to return the start or end time based on the point parameter. |
| [logValues](../-step-info/log-values.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)<br>fun [logValues](../-step-info/log-values.md)()<br>Function to log the values of the step info. |
