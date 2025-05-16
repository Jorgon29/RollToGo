package com.terraplanistas.rolltogo.ui.screens.actorCreation.steps.playstyleStep
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationContext
import com.terraplanistas.rolltogo.ui.screens.actorCreation.ActorCreationStep

class PlaystyleStep() : ActorCreationStep {
    override fun execute(context: ActorCreationContext, onNext: (ActorCreationContext) -> Unit) {

    }

    @Composable
    fun Sreen(){
        Text(text = stringResource(R.string.actor_creation_playstyle_title), fontSize = 32.sp)
        Spacer(Modifier.height(8.dp))
        Text(text = stringResource(R.string.actor_creation_playstyle_subtitle), fontSize = 8.sp)


    }
}