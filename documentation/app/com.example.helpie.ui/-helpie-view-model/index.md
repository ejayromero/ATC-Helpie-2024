//[app](../../../index.md)/[com.example.helpie.ui](../index.md)/[HelpieViewModel](index.md)

# HelpieViewModel

[androidJvm]\
@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)

class [HelpieViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

ViewModel class for managing the UI state and logic of the Helpie application.

## Constructors

| | |
|---|---|
| [HelpieViewModel](-helpie-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [uiState](ui-state.md) | [androidJvm]<br>val [uiState](ui-state.md): StateFlow&lt;[UiState](../../com.example.helpie/-ui-state/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [clean](clean.md) | [androidJvm]<br>fun [clean](clean.md)()<br>Cleans up the UI state. |
| [getlangswitch](getlangswitch.md) | [androidJvm]<br>fun [getlangswitch](getlangswitch.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Gets the language switch status. |
| [getNotification](get-notification.md) | [androidJvm]<br>fun [getNotification](get-notification.md)(): [ForegroundService.Actions](../../com.example.helpie.foregroundServices/-foreground-service/-actions/index.md)<br>Gets the notification action. |
| [getUIstate](get-u-istate.md) | [androidJvm]<br>fun [getUIstate](get-u-istate.md)(): [UiState](../../com.example.helpie/-ui-state/index.md)<br>Gets the current UI state. |
| [haveATrip](have-a-trip.md) | [androidJvm]<br>fun [haveATrip](have-a-trip.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks if there is an active trip. |
| [isClose](is-close.md) | [androidJvm]<br>fun [isClose](is-close.md)()<br>Checks if the user is close to a destination during a walk. |
| [lauchTrip](lauch-trip.md) | [androidJvm]<br>fun [lauchTrip](lauch-trip.md)(haveATrip: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Launches the trip. Important for notification |
| [launchGoogleMaps](launch-google-maps.md) | [androidJvm]<br>fun [launchGoogleMaps](launch-google-maps.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Launches Google Maps for navigation. |
| [launchNext](launch-next.md) | [androidJvm]<br>fun [launchNext](launch-next.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Launches the next step in the trip. |
| [needToClose](need-to-close.md) | [androidJvm]<br>fun [needToClose](need-to-close.md)()<br>Checks if the UI needs to be cleaned. |
| [openLink](open-link.md) | [androidJvm]<br>fun [openLink](open-link.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), url: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Opens a link in the default browser. |
| [request](request.md) | [androidJvm]<br>fun [request](request.md)()<br>Sends a trip request and updates the UI accordingly. |
| [restoreUI](restore-u-i.md) | [androidJvm]<br>fun [restoreUI](restore-u-i.md)(newState: [UiState](../../com.example.helpie/-ui-state/index.md))<br>Restores the UI state. Memory management |
| [sendNotification](send-notification.md) | [androidJvm]<br>fun [sendNotification](send-notification.md)(type: [ForegroundService.Actions](../../com.example.helpie.foregroundServices/-foreground-service/-actions/index.md))<br>Sends a notification. |
| [setClean](set-clean.md) | [androidJvm]<br>fun [setClean](set-clean.md)()<br>Sets the flag indicating the UI needs to be cleaned up. |
| [setFinish](set-finish.md) | [androidJvm]<br>fun [setFinish](set-finish.md)(finish: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Sets the flag indicating if the trip has finished. |
| [setLangage](set-langage.md) | [androidJvm]<br>fun [setLangage](set-langage.md)(langage: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Sets the language of the app. |
| [setLocalisationAddress](set-localisation-address.md) | [androidJvm]<br>fun [setLocalisationAddress](set-localisation-address.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), address: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Sets the address of a registered location. |
| [setLocalisationName](set-localisation-name.md) | [androidJvm]<br>fun [setLocalisationName](set-localisation-name.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), registeredLocalisation: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Localisation](../../com.example.helpie/-localisation/index.md)&gt;)<br>Sets the name of a registered location. |
| [setPhone](set-phone.md) | [androidJvm]<br>fun [setPhone](set-phone.md)(number: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Sets the phone number. |
| [setRemainingTime](set-remaining-time.md) | [androidJvm]<br>fun [setRemainingTime](set-remaining-time.md)(point: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), step: [StepInfo](../../com.example.helpie/-step-info/index.md))<br>Sets the remaining time for the current step. |
| [setTarget](set-target.md) | [androidJvm]<br>fun [setTarget](set-target.md)(target: [Localisation](../../com.example.helpie/-localisation/index.md))<br>Sets the target location. |
| [setTicket](set-ticket.md) | [androidJvm]<br>fun [setTicket](set-ticket.md)(isTicket: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Sets the flag indicating if a ticket is being used. |
| [setUsePhone](set-use-phone.md) | [androidJvm]<br>fun [setUsePhone](set-use-phone.md)(isUse: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Sets the flag indicating if the phone is being used. |
| [setWait](set-wait.md) | [androidJvm]<br>fun [setWait](set-wait.md)(wait: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Sets the flag indicating if the app is waiting. |
| [startUpdatingRemainingTime](start-updating-remaining-time.md) | [androidJvm]<br>@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)<br>fun [startUpdatingRemainingTime](start-updating-remaining-time.md)()<br>Starts updating the remaining time for the current step. |
| [SwitchDebug](-switch-debug.md) | [androidJvm]<br>fun [SwitchDebug](-switch-debug.md)()<br>Toggles the debugging mode. |
| [switchDialog](switch-dialog.md) | [androidJvm]<br>fun [switchDialog](switch-dialog.md)()<br>Toggles the dialog visibility. |
| [SwitchTicket](-switch-ticket.md) | [androidJvm]<br>fun [SwitchTicket](-switch-ticket.md)()<br>Toggles the easy ride mode. |
| [updateCurrentLocation](update-current-location.md) | [androidJvm]<br>fun [updateCurrentLocation](update-current-location.md)(current: LatLng)<br>Updates the current location. |
| [UpSkip](-up-skip.md) | [androidJvm]<br>fun [UpSkip](-up-skip.md)()<br>Increases the skipper count. |
