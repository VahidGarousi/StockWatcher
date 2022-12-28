package dev.garousi.stock_watcher

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.test.SemanticsMatcher
import dev.garousi.stock_watcher.ui.ColorRes
import dev.garousi.stock_watcher.ui.DrawableId
import dev.garousi.stock_watcher.ui.ImageVectorId

fun hasDrawable(@DrawableRes id: Int): SemanticsMatcher = SemanticsMatcher.expectValue(DrawableId, id)

fun hasImageVector(imageVector: ImageVector): SemanticsMatcher = SemanticsMatcher.expectValue(ImageVectorId, imageVector)

fun hasBackgroundColor(expectedBackgroundColor: Color) = SemanticsMatcher.expectValue(ColorRes, expectedBackgroundColor)