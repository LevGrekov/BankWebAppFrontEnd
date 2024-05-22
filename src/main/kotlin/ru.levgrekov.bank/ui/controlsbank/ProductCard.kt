package ru.levgrekov.bank.ui.controlsbank

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ProductCard(
    imagePath: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
                .clip(RoundedCornerShape(16.dp)),
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // Изображение
                Image(
                    painter = painterResource(imagePath),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(0.3f)
                        .clip(RoundedCornerShape(18.dp))
                        .align(Alignment.CenterVertically) // Выравнивание изображения по вертикали
                )

                // Промежуток между изображением и текстом
                Spacer(modifier = Modifier.width(8.dp))

                // Текст
                Column(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .weight(1f) // Распределение доступного пространства
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.body2,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}