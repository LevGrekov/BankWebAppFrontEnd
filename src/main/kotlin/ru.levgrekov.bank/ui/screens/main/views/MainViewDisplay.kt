package ru.levgrekov.bank.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.levgrekov.bank.logic.AvailableProductLogic
import ru.levgrekov.bank.logic.ProductType
import ru.levgrekov.bank.ui.controlsbank.BankProductBox
import ru.levgrekov.bank.ui.controlsbank.ClickableCard
import ru.levgrekov.bank.ui.controlsbank.spendHistoryCard
import ru.levgrekov.bank.ui.screens.main.models.MainScreenState
import ru.levgrekov.bank.ui.theme.GOLDEN_RATIO
import ru.levgrekov.bank.ui.theme.Orange
import ru.levgrekov.bank.ui.theme.White

@Composable
fun MainViewDisplay(
    state: MainScreenState.Display,
    onShowAllProducts: () -> Unit,
    onOpenHistory: () -> Unit,
    onProductClick: (id: Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(60.dp),
                title = {
                    Text(
                        text = state.userName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                },
                navigationIcon = { },
                actions = { }
            )
        },
    ) {
        val scrollState = rememberScrollState()
        val gradient = Brush.verticalGradient(
            colors = listOf(Orange, White),
            startY = -scrollState.maxValue.toFloat() / 3,
            endY = scrollState.maxValue - scrollState.value.toFloat() / 3
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    spendHistoryCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 0.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                            .aspectRatio(GOLDEN_RATIO),
                        title = "Все операции",
                        subtitle = "трат в мае",
                        amount = state.userExpenses,
                        onClick = onOpenHistory
                    )
                    ClickableCard(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp, top = 8.dp, end = 0.dp, bottom = 8.dp)
                            .fillMaxHeight()
                            .aspectRatio(GOLDEN_RATIO),
                        onClick = {}
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Кэшбек")
                        }
                    }
                }

                Spacer(Modifier.padding(vertical = 8.dp))

                Button(
                    onClick = onShowAllProducts,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize(),
                ) {
                    Text(text = "Новый Продукт")
                }

                Spacer(Modifier.padding(vertical = 8.dp))

                if (state.productsOpen) {
                    ProductType.entries.forEach { productType ->
                        val productsToDisplay = state.availableProductsList.filter { it.productType == productType }
                        if (productsToDisplay.isNotEmpty()) {
                            Spacer(Modifier.padding(vertical = 8.dp))
                            LazyRow {
                                productsToDisplay.forEach { product ->
                                    item {
                                        BankProductBox(product.imagePath, product.name, product.description, onClick = { onProductClick(product.id) })
                                    }
                                }
                            }

                        }
                    }
                }
//                Spacer(Modifier.padding(vertical = 8.dp))
//                ProductCard(R.SAVING_ACC_IMAGE, R.SAVING_ACC_TITLE, R.SAVING_ACC_DESCRIPTION, onClick = {})
//                Spacer(Modifier.padding(vertical = 8.dp))
//                ProductCard(R.DEPOSIT_IMAGE, R.DEPOSIT_TITLE, R.DEPOSIT_DESCRIPTION, onClick = {})
            }
        }
    }
}