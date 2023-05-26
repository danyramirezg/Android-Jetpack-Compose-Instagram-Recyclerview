package com.example.androidjetpackcomposerecyclerview.ui.theme

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidjetpackcomposerecyclerview.R
import com.example.androidjetpackcomposerecyclerview.model.Countries
import kotlinx.coroutines.launch

@Composable
fun CountriesHorizontalRecyclerView() {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getCountriesFlag()) { country ->
            ItemCountry(country = country) {
                Toast.makeText(context, it.CountryName, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun CountriesGridRecyclerView() {
    val context = LocalContext.current
    val recyclerviewState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()
    
    Column() {
        LazyVerticalGrid(GridCells.Fixed(2), state = recyclerviewState, modifier = Modifier.weight(1f),
            content = {
            items(getCountriesFlag()) { country ->
                ItemCountry(country = country)
                { Toast.makeText(context, it.CountryName, Toast.LENGTH_SHORT).show() }
            }
        })

        val showButton by remember {
            // derivedStateOf optimize the recomposition
            derivedStateOf {
                recyclerviewState.firstVisibleItemIndex > 0 // Shows the button after scrolled the first item.
            }
        }
         if (showButton) {
             TextButton(
                 modifier = Modifier
                     .align(Alignment.CenterHorizontally)
                     .padding(16.dp),
                 onClick = {
                           coroutineScope.launch {
                               // TextButton scrolls up to go to the first items in the recyclerview.
                               recyclerviewState.animateScrollToItem(0)
                           }
                 },
             ) {
                 Text(text = "Scroll up", fontWeight = FontWeight.ExtraBold, fontSize = 32.sp, color = Color(R.color.black))
             }
         }
    }
}

@Composable
fun ItemCountry(country: Countries, onItemSelected: (Countries) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.White),
        modifier = Modifier
            .width(200.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onItemSelected(country) }) {
        Column {
            Image(
                painter = painterResource(id = country.photoFlag),
                contentDescription = "Countries flag",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = country.CountryName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
            )
            Text(
                text = country.Capital,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = country.Continent,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Black,
                fontSize = 14.sp
            )
        }
    }
}

fun getCountriesFlag(): List<Countries> {
    return listOf(
        Countries("Argentina", "Buenos Aires", "South America", R.drawable.argentina_flag),
        Countries("Colombia", "Bogota", "South America", R.drawable.colombia_flag),
        Countries("France", "Paris", "Europe", R.drawable.france_flag),
        Countries("Italy", "Rome", "Europe", R.drawable.italy_flag),
        Countries("Spain", "Madrid", "Europe", R.drawable.spain_flag),
        Countries("Germany", "Berlin", "Europe", R.drawable.germany_flag),
        Countries("Ukraine", "Kyiv", "Europe", R.drawable.ukraine_flag),
        Countries("Switzerland", "Bern", "Europe", R.drawable.switzerland_flag),
        Countries(
            "United States",
            "Washington, D.C.",
            "North America",
            R.drawable.unitates_states_flag
        ),
        Countries("Uruguay", "Montevideo", "South America", R.drawable.uruguay_flag),
        Countries("Liechtenstein", "Vaduz", "Europe", R.drawable.liechestein_flag),
        Countries("Ecuador", "Quito", "South America", R.drawable.ecuador_flag),
    )
}