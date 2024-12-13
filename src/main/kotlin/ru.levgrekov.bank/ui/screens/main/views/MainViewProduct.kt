package ru.levgrekov.bank.ui.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.LongArrowAltLeftSolid
import ru.levgrekov.bank.ui.screens.main.models.MainScreenState
import java.time.LocalDate

data class Transaction(val date: LocalDate, val amount: Double, val description: String)

@Composable
fun ProductDisplayView(
    state: MainScreenState.Product,
    onChangeRecipient: (String) -> Unit,
    onChangeAmount: (String) -> Unit,
    onFloatingButtonClick: () -> Unit,
    onMakeTransaction: () -> Unit,
) {

    val transactions = listOf(
        Transaction(LocalDate.now(), -100.0, "Магазин"),
        Transaction(LocalDate.now().minusDays(1), 500.0, "Перевод от друга"),
        Transaction(LocalDate.now().minusDays(2), -50.0, "Кафе")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = Color(0xFFF0F0F0))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .shadow(4.dp, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = state.product.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = "Тип: ${state.product.type}", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                Text(text = "Баланс: ${state.product.balance} ₽", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .shadow(4.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Сделать перевод", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        OutlinedTextField(
                            value = state.recipientAccount,
                            onValueChange = onChangeRecipient,
                            label = { Text("Получатель") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = state.amountToTransfer,
                            onValueChange = onChangeAmount,
                            label = { Text("Сумма") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = onMakeTransaction,
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Перевести")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "История транзакций", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = onFloatingButtonClick,
            backgroundColor = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(LineAwesomeIcons.LongArrowAltLeftSolid, contentDescription = "Назад")
        }
    }

}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(2.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = transaction.date.toString(), fontWeight = FontWeight.Bold)
                Text(text = transaction.description)
            }
            Text(
                text = if (transaction.amount > 0) "+${transaction.amount} ₽" else "${transaction.amount} ₽",
                color = if (transaction.amount > 0) MaterialTheme.colors.primary else MaterialTheme.colors.error
            )
        }
    }
}

//@Preview()
//@Composable
//fun ProductScreenPreview() {
//    ProductScreen()
//}