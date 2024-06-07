//[app](../../index.md)/[com.example.helpie](index.md)/[HelpieApp](-helpie-app.md)

# HelpieApp

[androidJvm]\

@[RequiresApi](https://developer.android.com/reference/kotlin/androidx/annotation/RequiresApi.html)(value = 26)

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [HelpieApp](-helpie-app.md)(viewModel: [HelpieViewModel](../com.example.helpie.ui/-helpie-view-model/index.md) = viewModel(), navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html) = rememberNavController())

Composable function representing the main UI of the Helpie App. This function is responsible for rendering the entire application UI, including navigation, state management, and content display. The scaffold contains the main element and will add each screen on top of it.

#### Parameters

androidJvm

| | |
|---|---|
| viewModel | The view model instance for managing application state. |
| navController | The navigation controller for managing navigation within the app. |
