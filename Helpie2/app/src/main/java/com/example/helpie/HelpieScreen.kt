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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.helpie.StepInfo
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
import com.example.helpie.ui.SettingsScreen
import kotlinx.coroutines.flow.update
import com.example.helpie.ui.theme.CustomTextView
import com.example.helpie.ui.theme.TemplateButton
import kotlinx.coroutines.launch


enum class HelpieScreen {
    Help,
    Ticket,
    Step,
    TakeTicket,
    StopTicket,
    Summary,
    Destination,
    Start,
    Final,
    ReachStop,
    InBus,
    Walk,
    OutBus,
    WaitingTransport,
    JourneyInTransport,
    Settings
}



@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpieApp(
    viewModel: HelpieViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
    stepInfo: StepInfo = StepInfo()
) {

    val ctx = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = backStackEntry?.destination?.route

    val screenWidth = LocalConfiguration.current.screenWidthDp

    val imageArrivalShiftFraction = 0.415f

    val startFractionBar = -0.95f

    val imageShiftFraction = remember { mutableStateOf(startFractionBar) }

    val percentageBar = remember { mutableStateOf(0)}

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

    val transportPainter = remember { mutableStateOf(R.drawable.train_bar) }

    val transportDescription = remember { mutableStateOf(R.string.TrainBar) }

    LaunchedEffect(key1 = stepInfo.mode) {
        val (painter, description) = when (stepInfo.mode.toString()) {
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

    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        Column(Modifier.padding(start = 80.dp)) {
//                            Spacer(modifier = Modifier.height(.dp)) // Adding space on the top
                            CustomTextView(
                                text = stringResource(R.string.HELPIE),
                                size = 48.sp,
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    actions = {
                        Column {
                            //Spacer(modifier = Modifier.height(25.dp))
                            if (currentScreen == HelpieScreen.Start.name) {
                                Button(
                                    onClick = { navController.navigate(HelpieScreen.Settings.name) },
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                                    ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings, // Replace 'YourIconName' with the desired icon name
                                        contentDescription = "Edit Mode"
                                    )
                                }
                            }
                        }
                    },
                    scrollBehavior = scrollBehavior, //enable scrolling
                )
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
            }
            if (currentScreen != HelpieScreen.Help.name) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 28.dp)
                    .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
            ) {
                TemplateButton(
                    onClick = { navController.navigate(HelpieScreen.Help.name) },
                    text = stringResource(R.string.aide),
                    padding = false,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    sizeButton = "normal"
                    )
                }
            }
        }
    ) { innerPadding ->
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
                                text = "BILLET",
                                padding = false,
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                sizeButton = "normal"
                            )
                        }

                    }
                    if (uiState.tripOngoing) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            Image(
                                painter = painterResource(id = transportPainter.value),
                                contentDescription = stringResource(id = transportDescription.value),
                                modifier = Modifier
                                    .fillMaxSize()
                                    .offset(x = (imageShiftFraction.value * screenWidth).dp)
                            )
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
            NavHost(
                navController = navController,
                startDestination = HelpieScreen.Start.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = HelpieScreen.Settings.name) {
                    SettingsScreen(
                        registeredLocation = uiState.registeredLocation,
                        setLocalisationName = { index, name, _ ->
                            viewModel.setLocalisationName(index, name, uiState.registeredLocation)
                        },
                        setLocalisationAddress = { index, address, _ ->
                            viewModel.setLocalisationAddress(index, address, uiState.registeredLocation, context = ctx)
                        },
                        usePhone = uiState.usePhone,
                        phoneNumber = uiState.phoneNumber,
                        outlineNumber = uiState.outlineNumber,
                        phone = { viewModel.setUsePhone(it) },
                        setPhone = { viewModel.setPhone(it) },
                        modifier = Modifier
                            .fillMaxSize()
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
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Start.name) {
                    StartScreen(
                        onTicket = {
                            navController.navigate(HelpieScreen.Destination.name)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Final.name) {
                    FinalScreen(
                        recommence = {
                            viewModel.setFinish(false)
                            navController.navigate(HelpieScreen.Start.name)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Destination.name) {
                    DestinationScreen(
                        registeredLocation = uiState.registeredLocation,
                        showDialog = uiState.showDialog,
                        onRequest = {
                            viewModel.setWait(true)
                            runBlocking {
                                viewModel.request()
                                Log.d("wait", uiState.wait.toString())
                                while (uiState.wait) { Log.d("wait", "wait")}
                                navController.navigate(HelpieScreen.Summary.name)
                            }

                        },
                        setTarget = {
                            viewModel.setTarget(it)
                        },
                        setLocalisationAddress = { index, address, _ ->
                            viewModel.setLocalisationAddress(index, address, uiState.registeredLocation, context = ctx)
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
                        LaunchedEffect(key1 = Unit) {
                            viewModel.setTripOngoing(false)
                        }
                        SummaryScreen(
                            modifier = Modifier
                                .fillMaxSize(),
                            targetLocation = uiState.targetLocation,
                            summary = it1,
                            steps = uiState.steps,
                            onNext = {
                                navController.navigate(HelpieScreen.TakeTicket.name)
                            },
                            setTripOngoing = {
                                viewModel.setTripOngoing(true)
                            }
                        )
                    }


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
                        take = true
                    )
                }

                composable(route = HelpieScreen.StopTicket.name) {
                    TakeTicketScreen(
                        takeTicket = {
                            viewModel.setTicket(false)
                            viewModel.openLink(ctx,uiState.takeTicket)
                            viewModel.clean()
                            navController.navigate(HelpieScreen.Final.name)
                        },
                        modifier = Modifier
                            .fillMaxSize(),
                        take = false
                    )
                }


                composable(route = HelpieScreen.ReachStop.name) {
                    Log.d("reach", "${uiState.currentStep}")
                    if (uiState.steps[uiState.currentStep] is walkInfo) {
                        ReachStopScreen(
                            stepInfo = uiState.steps[uiState.currentStep] as walkInfo, //walkInfo to be changed in the future
                            modifier = Modifier.fillMaxSize()
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
                            Log.d("JTstep", "GO")
                            navController.navigate(HelpieScreen.OutBus.name)
                            shouldNavigate = false // This will stop the effect from running again
                        }
                    }
                    JourneyInTransportScreen(
                        stepInfo = uiState.steps[uiState.currentStep] as transportInfo,
                        modifier = Modifier.fillMaxSize(),
                        time = uiState.remainingTime
                    )
                }

                composable(route = HelpieScreen.OutBus.name) {
                    Log.d("outbusstep", "${uiState.currentStep}")
                    OutBusScreen(
                        stepInfo = if (uiState.steps[uiState.currentStep] is transportInfo) {
                            uiState.steps[uiState.currentStep] as transportInfo
                        } else {
                            transportInfo()
                        },
                        modifier = Modifier.fillMaxSize()
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

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(20.dp))


                    Row( horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                        if(((currentScreen == HelpieScreen.Destination.name) or (currentScreen == HelpieScreen.Summary.name) or ((currentScreen == HelpieScreen.TakeTicket.name)  and (!uiState.ticket) ) or (currentScreen == HelpieScreen.Help.name) or (currentScreen == HelpieScreen.Ticket.name)  ) and (navController.previousBackStackEntry != null)){
                            TemplateButton(
                                onClick = {
                                    navController.navigateUp()
                                },
                                text = "retour",
                                size = 12.sp,
                                sizeButton = "small",
                                padding = false,
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer
                            )
                        } else if ( (currentScreen != HelpieScreen.Start.name) and (currentScreen != HelpieScreen.Final.name)){
                                TemplateButton(
                                    onClick = {
                                        viewModel.clean()
                                        if (uiState.ticket) {
                                            navController.navigate(HelpieScreen.StopTicket.name)
                                        } else {
                                            navController.navigate(HelpieScreen.Start.name)
                                        }
                                    },
                                    text = "recommencer",
                                    size = 12.sp,
                                    sizeButton = "small",
                                    padding = false,
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                )
                            }
                            else {
                                Spacer(modifier = Modifier.width(127.dp))
                            }
                            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.button_corner_radius)))

                            if((currentScreen == HelpieScreen.ReachStop.name) or (currentScreen == HelpieScreen.InBus.name) or (currentScreen == HelpieScreen.OutBus.name)) {
                                var next = "suivant"
                                if (currentScreen == HelpieScreen.ReachStop.name) {
                                    next = "oui"
                                }
                                TemplateButton(
                                    onClick = {
                                        if ((currentScreen == HelpieScreen.ReachStop.name) or (currentScreen == HelpieScreen.OutBus.name)) {
                                            viewModel.viewModelScope.launch {
                                                navController.navigate(viewModel.launchNext())
                                            }
                                        } else {
                                            navController.navigate(HelpieScreen.JourneyInTransport.name)
                                        }
                                    },
                                    text = next,
                                    size = 18.sp,
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


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HelpiePreview() {
    AppTheme {
        HelpieApp(
        )
    }
}
