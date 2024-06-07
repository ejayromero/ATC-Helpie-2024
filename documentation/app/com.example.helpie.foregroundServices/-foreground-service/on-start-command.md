//[app](../../../index.md)/[com.example.helpie.foregroundServices](../index.md)/[ForegroundService](index.md)/[onStartCommand](on-start-command.md)

# onStartCommand

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)

open override fun [onStartCommand](on-start-command.md)(intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?, flags: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), startId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Handles the start command for the service.

#### Return

The service type code.

#### Parameters

androidJvm

| | |
|---|---|
| intent | The intent containing the action to be performed. |
| flags | Additional data about this start request. |
| startId | A unique identifier for the start request. |
