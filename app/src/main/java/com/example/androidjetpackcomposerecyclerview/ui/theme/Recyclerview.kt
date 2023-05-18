package com.example.androidjetpackcomposerecyclerview.ui.theme

import android.media.Image
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidjetpackcomposerecyclerview.R
import com.example.androidjetpackcomposerecyclerview.model.Countries

@Composable
fun CountriesView() {
    val context = LocalContext.current
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

        items(getCountriesFlag()) { country ->
            ItemCountry(country = country, onItemSelected = {
                Toast.makeText(context, it.CountryName, Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@Composable
fun ItemCountry(country: Countries, onItemSelected: (Countries) -> Unit) {
    Card(border = BorderStroke(2.dp, Color.White), modifier = Modifier.width(200.dp)) {
        Column() {
            Image(
                painter = painterResource(id = country.photoFlag),
                contentDescription = "Countries flag",
                modifier = Modifier.fillMaxWidth().height(150.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = country.CountryName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = country.Capital,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.Cursive,
                fontSize = 12.sp
            )
            Text(
                text = country.Continent,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp),
                fontFamily = FontFamily.Cursive,
                fontSize = 10.sp
            )
        }
    }

}

fun getCountriesFlag(): List<Countries> {
    return listOf(
        Countries("Argentina", "Buenos Aires", "South America", R.drawable.argentina_flag),
        Countries("Colombia", "Bogota", "South America", R.drawable.colombia_flag),
        Countries("Colombia", "Bogota", "South America", R.drawable.colombia_flag),
        Countries("Colombia", "Bogota", "South America", R.drawable.colombia_flag),
        Countries("Colombia", "Bogota", "South America", R.drawable.colombia_flag),
        Countries("Colombia", "Bogota", "South America", R.drawable.colombia_flag)
    )

}