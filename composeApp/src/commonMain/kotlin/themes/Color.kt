package themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val LightColors = lightColors(
    primary = Color.parseHex("#FA6650"),
    primaryVariant = Color.parseHex("#FF9988"),
    secondary = Color.parseHex("#D24C3A"),
    secondaryVariant = Color.parseHex("#C83D32"),
    background = Color.parseHex("#FFFFFF"),
    surface = Color.parseHex("#F5F5F5"),
    error = Color.parseHex("#FF0000"),
    onPrimary = Color.parseHex("#000000"),
    onSecondary = Color.parseHex("#FFFFFF"),
    onBackground = Color.parseHex("#000000"),
    onSurface = Color.parseHex("#000000"),
    onError = Color.parseHex("#FFFFFF"),
)


val DarkColors = darkColors(
    primary = Color.parseHex("#FA6650"),
    primaryVariant = Color.parseHex("#E74D3C"),
    secondary = Color.parseHex("#A03226"),
    secondaryVariant = Color.parseHex("#B82826"),
    background = Color.parseHex("#121212"),
    surface = Color.parseHex("#212121"),
    error = Color.parseHex("#D32F2F"),
    onPrimary = Color.parseHex("#FFFFFF"),
    onSecondary = Color.parseHex("#000000"),
    onBackground = Color.parseHex("#FFFFFF"),
    onSurface = Color.parseHex("#FFFFFF"),
    onError = Color.parseHex("#000000"),
)

//color extension function to parse hex color
fun Color.Companion.parseHex(hex: String): Color {
    return Color(hex.removePrefix("#").toLong(16) or 0x00000000FF000000)
}
