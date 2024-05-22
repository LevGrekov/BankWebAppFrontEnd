package ru.levgrekov.bank.ui.controlsbank

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.Calendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerInput(
    localDate: LocalDate,
    onDateChanged: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDialog by remember { mutableStateOf(false) }
    var dateString by remember { mutableStateOf(localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))) }
    TextField(
        modifier = modifier,
        value = dateString,
        singleLine = true,
        onValueChange = { },
        label = { Text(text = "Дата Рождения") },
        readOnly = true,
        leadingIcon = {
            IconButton(
                onClick = { showDialog = true }
            ) {
                Icon(
                    imageVector = LineAwesomeIcons.Calendar,
                    contentDescription = null,
                )
            }
        },
    )

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false }
        ) {
            DatePicker(
                onConfirm = {
                    onDateChanged(it)
                    dateString = it.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    showDialog = false
                },
                onCancel = { showDialog = false },
            )
        }
    }
}