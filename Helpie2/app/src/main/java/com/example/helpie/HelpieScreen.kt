package com.example.helpie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.helpie.ui.StartScreen
import com.example.helpie.ui.theme.AppTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helpie.ui.HelpScreen
import com.example.helpie.ui.TicketScreen
import com.example.helpie.ui.HelpieViewModel


enum class HelpieScreen {
    Start,
    Help,
    Ticket
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpieApp(
    viewModel: HelpieViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val uiState by viewModel.uiState.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                TopAppBar(
                    title = {
                        Column {
                            //Spacer(modifier = Modifier.height(.dp)) // Adding space on the top
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = stringResource(R.string.HELPIE),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 60.sp
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
                            Switch(
                                checked = uiState.editMode,
                                onCheckedChange = {viewModel.switchEdit()},
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior, //enable scrolling
                )

                if (uiState.ticket and (currentScreen != HelpieScreen.Ticket.name)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
                    ) {
                        Button(
                            onClick = {navController.navigate(HelpieScreen.Ticket.name) },
                            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                        )
                        {
                            Text(
                                text = "BILLET",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        ,
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
            }
            Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(color = MaterialTheme.colorScheme.primaryContainer) // Change Color.Green to your desired background color
                ) {
                Button(
                    onClick = {
                        navController.navigate(HelpieScreen.Help.name)
                    },
                    shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
                )
                {
                    Text(
                        text = stringResource(R.string.aide),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
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
            NavHost(
                navController = navController,
                startDestination = HelpieScreen.Start.name,
                modifier = Modifier.padding(innerPadding)
            ){
                composable(route = HelpieScreen.Start.name) {
                    StartScreen(
                        onTicket = {viewModel.setTicket(it)},
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Ticket.name) {
                    TicketScreen(
                        onReturnPressed = {
                            if (navController.previousBackStackEntry != null) {
                                navController.navigateUp()
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                composable(route = HelpieScreen.Help.name) {
                    HelpScreen(
                        editMode = uiState.editMode,
                        usePhone = uiState.usePhone,
                        phoneNumber = uiState.phoneNumber,
                        outlineNumber = uiState.outlineNumber,
                        phone = {viewModel.setUsePhone(it)},
                        setPhone = {viewModel.setPhone(it)},
                        onReturnPressed = {
                            if (navController.previousBackStackEntry != null) {
                                navController.navigateUp()
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HelpiePreview() {
    AppTheme {
        HelpieApp(
        )
    }
}
