package com.terraplanistas.rolltogo.ui.screens.actorCreation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.navigations.ActorScreenNavigation
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep.BiographyStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.characteristicsStep.CharacteristicsStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.classStep.ClassStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.playstyleStep.PlaystyleStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.raceStep.RaceStep
import com.terraplanistas.rolltogo.ui.screens.baseHomeScreen.BaseHomeScreen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ActorCreationHomeScreen(
    navController: NavController,
    viewModel: ActorCreationViewModel = viewModel( factory = ActorCreationViewModel.Factory),
    //goToCharacterScreen: (String) -> Unit
) {
    val context = remember { ActorCreationContext() }
    val snackbarHostState = remember { SnackbarHostState() }
    val incompleteData: String = stringResource(R.string.actor_creation_incomplete_fields)
    val coroutineScope = rememberCoroutineScope()
    val id = viewModel.id.collectAsStateWithLifecycle()

    var currentStep by remember { mutableStateOf<ActorCreationStep?>(null) }

    LaunchedEffect(Unit) {
        val step5 = CharacteristicsStep(viewModel)
        val step4 =  BiographyStep(viewModel, step5)
        val step3 = RaceStep(viewModel, step4, snackbarHostState)
        val step2 = ClassStep(viewModel, step3, snackbarHostState)
        val step1 = PlaystyleStep(viewModel, step2, snackbarHostState)
        currentStep = step1
    }

    LaunchedEffect(id.value) {
        Log.d("buildCharacter", id.value)
        if(id.value.isNotBlank()){
            navController.navigate(ActorScreenNavigation(id = id.value))
        }
    }
        BaseHomeScreen(
            navController = navController,
            snackBarHostState = snackbarHostState,
            content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        currentStep?.Screen(context)
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { navController.popBackStack() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.White
                            ),
                            elevation = null,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(stringResource(R.string.cancel))
                        }
                        Spacer(Modifier.width(16.dp))
                        Button(
                            onClick = {

                                currentStep?.hasNext()?.let {
                                    if (!it){
                                        Log.d("createActor", context.toString())
                                        viewModel.buildCharacter(context)
                                    }
                                }

                                currentStep?.let {
                                    if(currentStep!!.isDone()){
                                        if(currentStep!!.hasNext()){
                                            currentStep = currentStep!!.getNext()
                                        }
                                    } else {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar(incompleteData)
                                        }
                                    }
                                }
                                      },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.White
                            ),
                            elevation = null,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(stringResource(R.string.next))
                        }
                    }
                }
            },

        )
    }

