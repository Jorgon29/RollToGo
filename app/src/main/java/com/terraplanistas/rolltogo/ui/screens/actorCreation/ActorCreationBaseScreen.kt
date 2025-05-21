package com.terraplanistas.rolltogo.ui.screens.actorCreation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.biographyStep.BiographyStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.characteristicsStep.CharacteristicsStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.classStep.ClassStep
import com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.playstyleStep.PlaystyleStep
import com.terraplanistas.rolltogo.ui.screens.baseHomeScreen.BaseHomeScreen

@Composable
fun ActorCreationHomeScreen(navController: NavController, viewModel: ActorCreationViewModel = viewModel( factory = ActorCreationViewModel.Factory)) {
    val context = remember { ActorCreationContext() }

    var currentStep by remember { mutableStateOf<ActorCreationStep?>(null) }

    LaunchedEffect(Unit) {
        val step4 = BiographyStep(viewModel)
        val step3 = CharacteristicsStep(viewModel, step4)
        val step2 = ClassStep(viewModel, step3)
        val step1 = PlaystyleStep(viewModel, step2)
        currentStep = step1
    }

    BaseHomeScreen(
        navController = navController,
        content = {
                currentStep?.let {
                    currentStep!!.Screen(context) { }
                }

            Row {
                Button(
                    onClick = {navController.popBackStack()}
                ) {
                    Text(stringResource(R.string.cancel))
                }
                Button(
                    onClick = {
                        if(currentStep!!.hasNext()){
                            currentStep!!.execute(context, {context -> currentStep = currentStep?.getNext()})
                        }
                    }
                ) {
                    Text(stringResource(R.string.next))
                }
            }
            }
    )
}
