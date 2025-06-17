package com.terraplanistas.rolltogo.ui.screens.characterScreen.SecondaryScreens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terraplanistas.rolltogo.R
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainCharacter
import com.terraplanistas.rolltogo.data.model.creatures.character.DomainItem
import com.terraplanistas.rolltogo.helpers.Resource
import com.terraplanistas.rolltogo.ui.layout.boxes.basicTitle.BasicTitle
import com.terraplanistas.rolltogo.ui.layout.boxes.cateogoryBox.CategoryBox
import com.terraplanistas.rolltogo.ui.screens.characterScreen.CharacterScreenViewModel

@Composable
fun ItemsScreen(id: String,viewModel: CharacterScreenViewModel = viewModel(factory = CharacterScreenViewModel.Factory)){

    val characterResource by viewModel.characterResource.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadDummyCharacter()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(
                Brush.radialGradient(listOf<Color>(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.surfaceContainer),
                    radius = 1300f
                )),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BasicTitle(title = stringResource(R.string.actor_screen_inventory))

        Spacer(Modifier.height(16.dp))

        when (characterResource) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Error -> {
                Text("Error: ${(characterResource as Resource.Error).message}", color = Color.Red)
            }
            is Resource.Success -> {
                val character = (characterResource as Resource.Success<DomainCharacter>).data

                character?.let {

                    LazyColumn {
                        items(character.items) { item ->
                            ExpandableItemCard(item)
                        }
                    }

                } ?: run {

                    Text("Error: Datos del personaje no disponibles.")
                }
            }
        }
    }

}

@Composable
fun ExpandableItemCard(item: DomainItem, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card( // This single Card now wraps everything
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .fillMaxWidth(0.8f),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // This is the always-visible part (title and description)
            CategoryBox(
                title = item.name,
                content = item.description,
                onClick = { expanded = !expanded }
            )

            if (expanded) {
                // This is the expanded details section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Type: ${item.item_type_enum.name}", style = MaterialTheme.typography.bodySmall)
                    Text("Rarity: ${item.rarity_enum.name}", style = MaterialTheme.typography.bodySmall)
                    Text("Weight: ${item.weight} ${if (item.weight == null || item.weight.toFloat() == 0f) "" else "lbs"}", style = MaterialTheme.typography.bodySmall)
                    Text("Cost: ${item.cost_value} ${item.cost_unit.name}", style = MaterialTheme.typography.bodySmall)
                    Text("Attunement Required: ${if (item.attunement_required) "Yes" else "No"}", style = MaterialTheme.typography.bodySmall)
                    Text("Magical: ${if (item.it_magical) "Yes" else "No"}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
