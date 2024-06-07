/**
 * This file contains the main composable function for the Helpie App, along with related
 * composable functions and necessary imports.
 */

package com.example.helpie

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.helpie.ui.DestinationScreen
import com.example.helpie.ui.FinalScreen
import com.example.helpie.ui.HelpScreen
import com.example.helpie.ui.HelpieViewModel
import com.example.helpie.ui.InBusScreen
import com.example.helpie.ui.JourneyInTransportScreen
import com.example.helpie.ui.OutBusScreen
import com.example.helpie.ui.ReachStopScreen
import com.example.helpie.ui.StartScreen
import com.example.helpie.ui.SummaryScreen
import com.example.helpie.ui.TakeTicketScreen
import com.example.helpie.ui.TicketScreen
import com.example.helpie.ui.WaitingTransportScreen
import com.example.helpie.ui.WalkScreen
import com.example.helpie.ui.theme.AppTheme
import kotlinx.coroutines.runBlocking
import androidx.lifecycle.viewModelScope
import com.example.helpie.foregroundServices.ForegroundService
import com.example.helpie.ui.PopUpScreen
import com.example.helpie.ui.SettingsScreen
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton
import kotlinx.coroutines.launch


/**
 * Enum representing different screens or destinations within the Helpie App.
 * Each enum value corresponds to a specific screen or destination.
 */
enum class HelpieScreen {
    Help, // Screen for getting help or assistance
    Ticket, // Ticket information screen
    TakeTicket, // Screen for taking a ticket
    StopTicket, // Screen for stopping ticket services
    Summary, // Summary screen
    Destination, // Destination screen
    Start, // Start screen
    Final, // Final screen
    ReachStop, // Screen for reaching a stop
    InBus, // Screen for being inside a transport
    Walk, // Walking screen
    OutBus, // Screen for being outside a transport
    WaitingTransport, // Screen for waiting for transport
    JourneyInTransport, // Screen for journeying in transport
    Settings, // Settings screen
    PopUp // Pop-up screen
}


/**
 * Composable function representing the main UI of the Helpie App.
 * This function is responsible for rendering the entire application UI,
 * including navigation, state management, and content display.
 * The scaffold contains the main element and will add each screen on top of it.
 *
 * @param viewModel The view model instance for managing application state.
 * @param navController The navigation controller for managing navigation within the app.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpieApp(
    viewModel: HelpieViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {

    // Retrieve the current context
    val ctx = LocalContext.current

    // Retrieve the UI state from the view model
    val uiState by viewModel.uiState.collectAsState()

    // Define the scroll behavior for the top app bar
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    // Retrieve the current back stack entry from the navigation controller
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Determine the current screen based on the back stack entry
    val currentScreen = backStackEntry?.destination?.route

    // Retrieve the screen width from the current configuration
    val screenWidth = LocalConfiguration.current.screenWidthDp

    // Constants for image fraction calculation
    val imageArrivalShiftFraction = 0.415f
    val startFractionBar = -0.5f

    // State variables for image shift fraction and percentage bar
    val imageShiftFraction = remember { mutableStateOf(startFractionBar) }
    val percentageBar = remember { mutableStateOf(0) }

    // Calculate image shift fraction and percentage bar based on current UI state
    LaunchedEffect(uiState.currentStep) {
        val currentStep = uiState.currentStep + 1
        val totalSteps = uiState.steps.size + 1

        // Calculate the fraction based on the current step and total steps
        if (currentStep == 0 || totalSteps == 0) {
            imageShiftFraction.value = startFractionBar
            percentageBar.value = 0
        } else if (currentStep == totalSteps - 1) {
            imageShiftFraction.value = imageArrivalShiftFraction
            percentageBar.value = 100
        } else {
            imageShiftFraction.value = startFractionBar + (currentStep.toFloat() / totalSteps.toFloat()) //* 1.315f
            percentageBar.value = ((currentStep.toFloat() / totalSteps.toFloat()) * 100).toInt()
        }
    }

    // State variables for transport painter and description
    val transportPainter = remember { mutableStateOf(R.drawable.walking_bar) }
    val transportDescription = remember { mutableStateOf(R.string.WalkBar) }

    // Update transport painter and description based on UI state
    LaunchedEffect(uiState.currentStep) {
        if (uiState.steps.isNotEmpty() && uiState.currentStep in uiState.steps.indices) {
            val (painter, description) = when (uiState.steps[uiState.currentStep].mode.toString()) {
                "bus" -> Pair(R.drawable.bus_bar, R.string.BusBar)
                "rail" -> Pair(R.drawable.train_bar, R.string.TrainBar)
                "walk" -> Pair(R.drawable.walking_bar, R.string.WalkBar)
                "metro" -> Pair(R.drawable.metro_bar, R.string.MetroBar)
                "boat" -> Pair(R.drawable.boat_bar, R.string.BoatBar)
                else -> Pair(R.drawable.train_bar, R.string.TrainBar)
            }
            transportPainter.value = painter
            transportDescription.value = description
        }
    }


    // Scaffold for the entire app layout
    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    // Top app bar containing title and settings button
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Render title and settings button
                            if (uiState.debugging) {
                                // Render debug mode elements
                                TemplateButton(
                                    onClick = {
                                        viewModel.UpSkip()
                                    },
                                    text = stringResource(R.string.skip),
                                    size = 18.sp,
                                    sizeButton = "small",
                                    padding = false,
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                )

                                Column {
                                    CustomTextView(
                                        text = stringResource(R.string.HELPIE),
                                        size = 48.sp,
                                    )
                                }
                            } else {// Render normal mode title
                                Column(Modifier.padding(start = 80.dp)) {
                                    CustomTextView(
                                        text = stringResource(R.string.HELPIE),
                                        size = 48.sp,
                                    )
                                }}
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    // Add settings button
                    actions = {
                        Column {
                            if (currentScreen == HelpieScreen.Start.name) {
                                Button(
                                    onClick = { navController.navigate(HelpieScreen.Settings.name) },
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = stringResource(R.string.edit)
                                    )
                                }
                            }
                        }
                    },
                    scrollBehavior = scrollBehavior, // Enable scrolling
                )

            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
            }
            // Add help button
            if (currentScreen != HelpieScreen.Help.name) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 28.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
            ) {
                TemplateButton(
                    onClick = {
                        navController.navigate(HelpieScreen.Help.name) },
                    text = stringResource(R.string.aide),
                    padding = false,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    sizeButton = "normal"
                    )
                }
            }
        }
    ) { innerPadding ->
        // Main content area of the app
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                // Render ticket button if required
                    if (uiState.ticket and (currentScreen != HelpieScreen.Ticket.name)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            TemplateButton(
                                onClick = { navController.navigate(HelpieScreen.Ticket.name)},
                                text = stringResource(R.string.ticket),
                                padding = false,
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                sizeButton = "normal"
                            )
                        }

                    }
                // Render progress bar and transport image
                    if ((currentScreen != HelpieScreen.Summary.name) and (currentScreen != HelpieScreen.Start.name) and (currentScreen != HelpieScreen.Destination.name) and (currentScreen != HelpieScreen.Final.name)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            val transportOffset = (imageShiftFraction.value * screenWidth).dp

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .offset(x = transportOffset)
                            ) {
                                Image(
                                    painter = painterResource(id = transportPainter.value),
                                    contentDescription = stringResource(id = transportDescription.value),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            // Overlay to make the right portion of the image transparent
                            Image(
                                painter = painterResource(id = R.drawable.arrivee_panel),
                                contentDescription = stringResource(id = R.string.PanneauArrivee),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent)
                                    .offset(x = (imageArrivalShiftFraction * screenWidth).dp) // Shift the image 2/10th out of the screen to the left
                            )

                        }
                        Text(
                            text = percentageBar.value.toString() + "%",
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(10.dp),
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
            }
            // Navigation host for handling different screens
            NavHost(
                navController = navController,
                startDestination = HelpieScreen.Start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                // Define composable destinations for different screens

                composable(route = HelpieScreen.Settings.name) {
                    SettingsScreen(
                        registeredLocation = uiState.registeredLocation,
                        setLocalisationName = { index, name, _ ->
                            viewModel.setLocalisationName(index, name, uiState.registeredLocation)
                        },
                        setLocalisationAddress = { index, address ->
                            viewModel.setLocalisationAddress(index, address, context = ctx)
                        },
                        usePhone = uiState.usePhone,
                        phoneNumber = uiState.phoneNumber,
                        outlineNumber = uiState.outlineNumber,
                        phone = { viewModel.setUsePhone(it) },
                        setPhone = { viewModel.setPhone(it) },
                        debugging = uiState.debugging,
                        switchDebug = {viewModel.SwitchDebug()},
                        easyRide = uiState.easyRide,
                        switchTicket = {viewModel.SwitchTicket()},
                        setLangage = {viewModel.setLangage(it)},
                        currentLangage = uiState.langage
                    )
                }

                composable(route = HelpieScreen.Help.name) {
                    HelpScreen(
                        usePhone = uiState.usePhone,
                        phoneNumber = uiState.phoneNumber,
                        outlineNumber = uiState.outlineNumber,
                    )
                }
                composable(route = HelpieScreen.Ticket.name) {
                    TicketScreen(
                        showTicket = {
                            viewModel.openLink(ctx,uiState.urlTicket)
                        },
                        easyRide = uiState.easyRide,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Start.name) {
                    StartScreen(
                        onTicket = {
                            viewModel.clean()
                            navController.navigate(HelpieScreen.Destination.name)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Final.name) {
                    FinalScreen(
                        recommence = {
                            navController.navigate(HelpieScreen.Start.name)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Destination.name) {
                    DestinationScreen(
                        _uiState = uiState,
                        context = ctx,
                        registeredLocation = uiState.registeredLocation,
                        showDialog = uiState.showDialog,
                        onRequest = {
                            viewModel.setWait(true)
                            runBlocking {
                                viewModel.request()
                                while (uiState.wait) { Log.d("wait", "wait")}
                                navController.navigate(HelpieScreen.Summary.name)
                            }

                        },
                        setTarget = {
                            viewModel.setTarget(it)
                        },
                        setLocalisationAddress = { index, address, _ ,_ ->
                            viewModel.setLocalisationAddress(index, address, context = ctx)
                        },
                        switchDialog = {
                            viewModel.switchDialog()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Summary.name) {
                    uiState.summary?.let { it1 ->
                        SummaryScreen(
                            modifier = Modifier
                                .fillMaxSize(),
                            summary = it1,
                            steps = uiState.steps,
                            onNext = {
                                viewModel.lauchTrip(true)
                                if (!uiState.ticket) {
                                    navController.navigate(HelpieScreen.TakeTicket.name)
                                } else {
                                    viewModel.viewModelScope.launch {
                                        val nextScreen = viewModel.launchNext()
                                        navController.navigate(nextScreen)
                                    }
                                }
                            }
                        )
                    }


                }

                composable(route = HelpieScreen.PopUp.name) {
                        PopUpScreen(
                            onStop = { if (uiState.ticket and uiState.easyRide) {
                                navController.navigate(HelpieScreen.StopTicket.name)
                            } else {
                                viewModel.setClean()
                                navController.navigate(HelpieScreen.Start.name)
                            }
                                     },
                            onRestart = {
                                viewModel.setWait(true)
                                runBlocking {
                                    viewModel.request()
                                    while (uiState.wait) { Log.d("wait", "wait")}
                                    navController.navigate(HelpieScreen.Summary.name)
                                }
                            },
                            onDismiss = {navController.navigateUp()}
                        )


                }


                composable(route = HelpieScreen.TakeTicket.name) {
                    TakeTicketScreen(
                        takeTicket = {
                            viewModel.setTicket(true)
                            viewModel.openLink(ctx,uiState.takeTicket)

                            viewModel.viewModelScope.launch {
                                val nextScreen = viewModel.launchNext()
                                navController.navigate(nextScreen)
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize(),
                        easyRide = uiState.easyRide,
                        take = true
                    )
                }

                composable(route = HelpieScreen.StopTicket.name) {
                    viewModel.isClose()
                    TakeTicketScreen(
                        takeTicket = {
                            viewModel.setTicket(false)
                            viewModel.openLink(ctx,uiState.takeTicket)
                            viewModel.setClean()
                            if (uiState.isFinish) {
                                navController.navigate(HelpieScreen.Final.name)
                            } else {
                                navController.navigate(HelpieScreen.Start.name)
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize(),
                        easyRide = uiState.easyRide,
                        take = false
                    )
                }


                composable(route = HelpieScreen.ReachStop.name) {
                    Log.d("reach", "${uiState.currentStep}")
                    if (uiState.steps[uiState.currentStep] is walkInfo) {
                        ReachStopScreen(
                            stepInfo = uiState.steps[uiState.currentStep] as walkInfo, //walkInfo to be changed in the future
                            nextStep = uiState.steps[uiState.currentStep + 1] as transportInfo
                        )
                    }
                }

                composable(route = HelpieScreen.WaitingTransport.name) {
                    Log.d("waitingstep", "${uiState.currentStep}")
                    viewModel.setRemainingTime(uiState.timeNeeded, uiState.steps[uiState.currentStep])
                    viewModel.startUpdatingRemainingTime()

                    var shouldNavigate by remember { mutableStateOf(true) }

                    LaunchedEffect(uiState.remainingTime) {
                        if (uiState.remainingTime < 2 && shouldNavigate) {
                            Log.d("JTstep", "GO")
                            viewModel.sendNotification(ForegroundService.Actions.Monter)
                            navController.navigate(HelpieScreen.InBus.name)
                            shouldNavigate = false // This will stop the effect from running again
                        }
                    }
                    WaitingTransportScreen(
                        time = uiState.remainingTime,
                        stepInfo = uiState.steps[uiState.currentStep] as transportInfo,
                    )

                }

                composable(route = HelpieScreen.InBus.name) {
                    Log.d("inbus", "${uiState.currentStep}")
                    InBusScreen(
                        stepInfo = uiState.steps[uiState.currentStep] as transportInfo,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.JourneyInTransport.name) {
                    Log.d("JTstep", "${uiState.currentStep}")
                    viewModel.setRemainingTime("end", uiState.steps[uiState.currentStep])

                    var shouldNavigate by remember { mutableStateOf(true) }

                    LaunchedEffect(uiState.remainingTime) {
                        if (uiState.remainingTime < 2 && shouldNavigate) {
                            viewModel.sendNotification(ForegroundService.Actions.Descendre)
                            Log.d("JTstep", "GO")
                            navController.navigate(HelpieScreen.OutBus.name)
                            shouldNavigate = false // This will stop the effect from running again
                        }
                    }
                    JourneyInTransportScreen(
                        stepInfo = uiState.steps[uiState.currentStep] as transportInfo,
                        time = uiState.remainingTime
                    )
                }

                composable(route = HelpieScreen.OutBus.name) {
                    Log.d("outbusstep", "${uiState.currentStep}")
                    OutBusScreen(
                        stepInfo = if (uiState.steps[uiState.currentStep] is transportInfo) {
                            uiState.steps[uiState.currentStep] as transportInfo
                        } else {
                            uiState.steps[uiState.currentStep-1] as transportInfo
                        }
                    )
                }

                composable(route = HelpieScreen.Walk.name) {
                    Log.d("walk", "${uiState.currentStep}")
                    WalkScreen(
                        stepInfo = uiState.steps[uiState.currentStep] as walkInfo,
                        lauchMaps = {
                            viewModel.launchGoogleMaps(ctx)
                            if (uiState.currentStep == (uiState.summary?.npSteps?.minus(1)) ) {
                                viewModel.setFinish(true)
                                navController.navigate(HelpieScreen.StopTicket.name)
                            } else {
                                navController.navigate(HelpieScreen.ReachStop.name)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Render navigation buttons
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(20.dp))


                    Row( horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                        var past = stringResource(R.string.retour)
                        if(
                            (currentScreen == HelpieScreen.Destination.name) or
                            (currentScreen == HelpieScreen.Settings.name) or
                            (currentScreen == HelpieScreen.Summary.name) or
                            (currentScreen == HelpieScreen.Help.name) or
                            (currentScreen == HelpieScreen.Ticket.name) or
                            ((currentScreen == HelpieScreen.TakeTicket.name) and (!uiState.ticket) and (navController.previousBackStackEntry != null))
                            ){
                            TemplateButton(
                                onClick = {
                                    navController.navigateUp()
                                },
                                text = past,
                                size = 18.sp,
                                sizeButton = "small",
                                padding = false,
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            )
                        } else if (
                            (currentScreen == HelpieScreen.InBus.name) or
                            (currentScreen == HelpieScreen.OutBus.name) or
                            (currentScreen == HelpieScreen.Walk.name) or
                            (currentScreen == HelpieScreen.WaitingTransport.name) or
                            (currentScreen == HelpieScreen.ReachStop.name) or
                            (currentScreen == HelpieScreen.JourneyInTransport.name)
                            ){
                                past = stringResource(R.string.arr_ter)
                            if (currentScreen == HelpieScreen.ReachStop.name) {
                                past = stringResource(R.string.non)
                            } else if (currentScreen == HelpieScreen.JourneyInTransport.name) {
                                past = stringResource(R.string.probl_me)
                            }
                                TemplateButton(
                                    onClick = {
                                        navController.navigate(HelpieScreen.PopUp.name)
                                    },
                                    text = past,
                                    size = 14.sp,
                                    sizeButton = "small",
                                    padding = false,
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                )
                            }
                            else {
                                Spacer(modifier = Modifier.width(127.dp))
                            }
                            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.button_corner_radius)))

                            if((currentScreen == HelpieScreen.ReachStop.name) or (currentScreen == HelpieScreen.InBus.name) or (currentScreen == HelpieScreen.OutBus.name) or ((currentScreen == HelpieScreen.TakeTicket.name) and (!uiState.easyRide) )) {
                                var next = stringResource(R.string.suivant)
                                if (currentScreen == HelpieScreen.ReachStop.name) {
                                    next = stringResource(R.string.oui)
                                } else if (currentScreen == HelpieScreen.TakeTicket.name) {
                                    next = stringResource(R.string.ok)
                                }
                                TemplateButton(
                                    onClick = {
                                        if ((currentScreen == HelpieScreen.ReachStop.name) or (currentScreen == HelpieScreen.OutBus.name)) {
                                            viewModel.viewModelScope.launch {
                                                navController.navigate(viewModel.launchNext())
                                            }
                                        } else if (currentScreen == HelpieScreen.TakeTicket.name) {
                                            viewModel.setTicket(true)
                                            viewModel.viewModelScope.launch {
                                                val nextScreen = viewModel.launchNext()
                                                navController.navigate(nextScreen)
                                            }
                                        }
                                        else {
                                            navController.navigate(HelpieScreen.JourneyInTransport.name)
                                        }
                                    },
                                    text = next,
                                    size = 14.sp,
                                    sizeButton = "small",
                                    padding = false,
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                )
                            } else {
                                Spacer(modifier = Modifier.width(127.dp))
                            }

                }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
}


/**
 * A preview composable function for the HelpieApp.
 * This function is annotated with @Preview and @Composable, allowing it to be previewed in Android Studio's Layout Editor.
 * It displays the HelpieApp wrapped inside the AppTheme.
 *
 * @see HelpieApp
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HelpiePreview() {
    AppTheme {
        HelpieApp(
        )
    }
}
