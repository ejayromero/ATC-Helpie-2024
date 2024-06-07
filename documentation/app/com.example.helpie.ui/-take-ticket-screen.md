//[app](../../index.md)/[com.example.helpie.ui](index.md)/[TakeTicketScreen](-take-ticket-screen.md)

# TakeTicketScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TakeTicketScreen](-take-ticket-screen.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, takeTicket: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, take: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), easyRide: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))

A composable function representing the TakeTicketScreen. This screen displays instructions for taking or activating a ticket and a button to perform the action.

#### Parameters

androidJvm

| | |
|---|---|
| modifier | The modifier for the TakeTicketScreen layout. |
| takeTicket | Callback function to take/activate the ticket. |
| take | Boolean indicating whether we are activating or destroying a ticket. |
| easyRide | Boolean indicating if the user use EasyRide. |
