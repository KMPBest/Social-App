import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import themes.DarkColors
import themes.LightColors

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors, content = content
    )
}