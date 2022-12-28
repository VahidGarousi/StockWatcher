package dev.garousi.stock_watcher.feature.watchlist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.garousi.stock_watcher.feature.watchlist.domain.models.Stock
import dev.garousi.stock_watcher.ui.colorRes
import dev.garousi.stock_watcher.ui.imageVectorId

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StockItem(
    stock: Stock,
    index: Int,
    backgroundColor: Color = MaterialTheme.colors.surface,
    onClick: (Stock) -> Unit = {}
) {
    val density = LocalDensity.current
    Card(
        backgroundColor = backgroundColor,
        contentColor = MaterialTheme.colors.onSurface,
        modifier = Modifier
            .padding(
                bottom = 8.dp
            )
            .testTag(StockItemTestTags.card + index)
            .drawBehind {
                val shadowColor = Color.Black.toArgb()
                val transparentColor = backgroundColor
                    .copy(alpha = 0f)
                    .toArgb()
                drawIntoCanvas {
                    val paint = Paint()
                    val frameworkPaint = paint.asFrameworkPaint()
                    frameworkPaint.color = transparentColor
                    frameworkPaint.setShadowLayer(
                        density.run { 8.dp.toPx() },
                        density.run { 2.dp.toPx() },
                        density.run { 2.dp.toPx() },
                        shadowColor
                    )
                    it.drawRoundRect(
                        0f,
                        0f,
                        this.size.width,
                        this.size.height,
                        density.run { 2.dp.toPx() },
                        density.run { 2.dp.toPx() },
                        paint
                    )
                }
                drawRoundRect(
                    color = if (stock.change > 0) Color.Green else Color.Red,
                    topLeft = Offset(
                        x = size.width - density.run { 12.dp.toPx() },
                        y = density.run { 4.dp.toPx() }
                    ),
                    size = Size(
                        width = density.run { 17.dp.toPx() },
                        height = density.run { 48.dp.toPx() }
                    ),
                    cornerRadius = CornerRadius(
                        x = density.run { 8.dp.toPx() },
                        y = density.run { 8.dp.toPx() }
                    )
                )
            },
        onClick = { onClick(stock) },
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(12.dp)
        ) {
            Text(
                text = stock.name,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .testTag(StockItemTestTags.name + index)
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${stock.last}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .drawBehind {
                            drawRoundRect(
                                color = if (stock.change > 0) Color(0XFF48BE62) else Color(
                                    0XFFBE4848
                                ),
                                cornerRadius = CornerRadius(
                                    x = density.run { 4.dp.toPx() },
                                    y = density.run { 4.dp.toPx() }
                                )
                            )
                        }
                        .padding(vertical = 2.dp, horizontal = 16.dp)
                        .testTag(StockItemTestTags.lastPrice + index)
                )
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = buildAnnotatedString {
                        if (stock.change > 0) append("+") else append("-")
                        append(" ")
                        append("${stock.change}")
                    },
                    modifier = Modifier.testTag(StockItemTestTags.priceChange + index)
                )
                Icon(
                    imageVector = if (stock.change > 0) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    tint = if (stock.change > 0) Color(0XFF48BE62) else Color(0XFFBE4848),
                    modifier = Modifier
                        .testTag(StockItemTestTags.arrow + index)
                        .semantics {
                            imageVectorId =
                                if (stock.change > 0) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
                            colorRes =
                                if (stock.change > 0) Color(0XFF48BE62) else Color(0XFFBE4848)
                        }
                )
            }
        }
    }
}


object StockItemTestTags {
    const val card = "stock_card_"
    const val name = "stock_name_"
    const val lastPrice = "stock_lastPrice_"
    const val priceChange = "stock_priceChange_"
    const val arrow = "stock_arrow_"
}