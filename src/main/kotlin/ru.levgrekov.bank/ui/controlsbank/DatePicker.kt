package ru.levgrekov.bank.ui.controlsbank

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


@Composable
fun DatePicker(
    onCancel: () -> Unit,
    onConfirm: (localDate: LocalDate) -> Unit
) {
    var showingMonth by remember { mutableStateOf(Calendar.getInstance()) }
    var selected by remember {
        mutableStateOf(
            Calendar.getInstance().apply { timeInMillis = showingMonth.timeInMillis })
    }
    var list by remember { mutableStateOf(getDatesList(showingMonth)) }
    val weeks = arrayOf("S", "M", "T", "W", "T", "F", "S")
    val cellSize = 40.dp
    val primaryColor = MaterialTheme.colors.primary

    Column(modifier = Modifier.width(IntrinsicSize.Max)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(primaryColor)
                .padding(16.dp)
        ) {
            Text(
                text = SimpleDateFormat("yyyy", Locale.ENGLISH).format(selected.time),
                color = Color.White.copy(alpha = .7f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = SimpleDateFormat("EEE, d MMM", Locale.ENGLISH).format(selected.time),
                color = Color.White, fontSize = 27.sp
            )
        }

        Column(modifier = Modifier.background(Color(0xFFffffff))) {

            Box(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 12.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color(0xFF212121),
                    modifier = Modifier
                        .size((16 * 3).dp)
                        .align(Alignment.CenterStart)
                        .padding(16.dp)
                        .clickable {
                            showingMonth = Calendar.getInstance()
                                .apply { timeInMillis = showingMonth.timeInMillis;add(Calendar.MONTH, -1) }
                            list = getDatesList(showingMonth)
                        }
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = SimpleDateFormat("MMM yyyy", Locale.ENGLISH).format(showingMonth.time),
                    color = Color(0xFF212121),
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "",
                    tint = Color(0xFF212121),
                    modifier = Modifier
                        .size((16 * 3).dp)
                        .align(Alignment.CenterEnd)
                        .padding(16.dp)
                        .clickable {
                            showingMonth = Calendar.getInstance().apply {
                                timeInMillis = showingMonth.timeInMillis;add(Calendar.MONTH, +1)
                            }
                            list = getDatesList(showingMonth)
                        }
                )
            }
            Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                weeks.forEach {
                    Text(
                        text = it,
                        color = Color(0xFF757575),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.size(cellSize)
                    )
                }
            }
            list.chunked(7).forEach {
                Row(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    it.forEach {
                        Box(
                            modifier = Modifier
                                .size(cellSize)
                                .clickable {
                                    if (isSameMonth(it?.first, showingMonth)) {
                                        selected = Calendar.getInstance().apply {
                                            timeInMillis = it!!.first
                                        }
                                    }
                                }
                                .drawBehind {
                                    drawCircle(
                                        color = if (it?.second == true && isSelected(
                                                it?.first!!,
                                                selected,
                                                showingMonth
                                            )
                                        ) primaryColor else Color.Transparent
                                    )
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = toDate(it?.first),
                                textAlign = TextAlign.Center, fontSize = 14.sp,
                                color = if (it?.second == true) {
                                    if (isSelected(it?.first!!, selected, showingMonth))
                                        Color(0xFFffffff)
                                    else
                                        Color(0xFF757575)

                                } else if (it?.second == false) Color(0x11757575) else Color(0),
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onCancel() },
                    text = "Отмена",
                    color = primaryColor
                )
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            selected?.let {
                                onConfirm(LocalDate.ofInstant(it.toInstant(), ZoneId.systemDefault()))
                            }
                        },
                    text = "OK",
                    color = primaryColor
                )
            }
        }
    }
}

private fun getDatesList(calIncoming: Calendar): ArrayList<Pair<Long, Boolean>?> {

    val cal = Calendar.getInstance().apply { timeInMillis = calIncoming.timeInMillis }
    val list = arrayListOf<Pair<Long, Boolean>?>()
    cal.set(Calendar.DATE, 1)
    val currentMont = cal.get(Calendar.MONTH)
    while (cal.get(Calendar.MONTH) == currentMont) {
        list.add(cal.timeInMillis to true)
        cal.add(Calendar.DATE, 1)
    }
    while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        list.add(cal.timeInMillis to false)
        cal.add(Calendar.DATE, 1)
    }
    cal.set(Calendar.DATE, 1)
    cal.add(Calendar.MONTH, -1)
    cal.add(Calendar.DATE, -1)
    while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
        list.add(0, cal.timeInMillis to false)
        cal.add(Calendar.DATE, -1)
    }

    if (list.size < 7 * 6) {
        while (list.size < (7 * 6)) {
            list.add(null)
        }
    }

    return list

}


private fun isSelected(first: Long, selected: Calendar, showing: Calendar): Boolean {
    return (selected.get(Calendar.YEAR) == showing.get(Calendar.YEAR) &&
            selected.get(Calendar.MONTH) == showing.get(Calendar.MONTH)
            && selected.get(Calendar.DATE) == Calendar.getInstance().apply {
        timeInMillis = first
    }.get(Calendar.DATE))
}

private fun isSameMonth(first: Long?, showing: Calendar): Boolean {
    return if (first == null) false
    else {
        val cal = Calendar.getInstance().apply {
            timeInMillis = first
        }
        cal.get(Calendar.MONTH) == showing.get(Calendar.MONTH) && cal.get(Calendar.YEAR) == showing.get(Calendar.YEAR)
    }
}

private fun toDate(first: Long?): String {
    if (first == null) return ""
    return SimpleDateFormat("d", Locale.ENGLISH).format(Date(first))
}
