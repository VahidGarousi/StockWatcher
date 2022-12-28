package dev.garousi.stock_watcher.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

val DrawableId = SemanticsPropertyKey<Int>("DrawableResId")
var SemanticsPropertyReceiver.drawableId by DrawableId


val ImageVectorId = SemanticsPropertyKey<ImageVector>("ImageVectorId")
var SemanticsPropertyReceiver.imageVectorId by ImageVectorId


val ColorRes = SemanticsPropertyKey<Color>("ColorRes")
var SemanticsPropertyReceiver.colorRes by ColorRes