package ru.levgrekov.bank.ui.screens.main.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.LongArrowAltLeftSolid
import ru.levgrekov.bank.ui.screens.main.models.MainScreenState


@Composable
fun MainViewNewProduct(
    state: MainScreenState.NewProduct,
    onGetNewProduct: (id: Int) -> Unit,
    onFloatingButtonClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.9f)
                    translationY = 0.4f * scrollState.value
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(state.product.imagePath),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
            )
        }

        Spacer(Modifier.padding(10.dp))

        Text(
            text = state.product.name,
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )

        Spacer(Modifier.padding(10.dp))

        Text(
            text = state.product.text,
            modifier = Modifier.padding(10.dp),
            style = TextStyle(fontSize = 18.sp)
        )

        Button(
            onClick = { onGetNewProduct(state.product.id) },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
        ) {
            Text(text = "Заиметь ${state.product.name}")
        }

    }
    FloatingActionButton(
        onClick = onFloatingButtonClick,
        modifier = Modifier.padding(16.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Icon(LineAwesomeIcons.LongArrowAltLeftSolid, contentDescription = "Назад")
    }
}