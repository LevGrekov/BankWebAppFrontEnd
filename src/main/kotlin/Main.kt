
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ru.levgrekov.bank.ui.MainObject
import ru.levgrekov.bank.ui.ScreenState
import ru.levgrekov.bank.ui.screens.auth.AuthScreen
import ru.levgrekov.bank.ui.screens.main.MainScreen
import ru.levgrekov.bank.ui.screens.product.ProductScreen


val mainObject = MainObject
fun main() = application {
    Window(
        state = rememberWindowState(
            width = 450.dp,
            height = 650.dp,
            placement = WindowPlacement.Floating,
            position = WindowPosition(100.dp, 100.dp),
            isMinimized = false
        ),
        onCloseRequest = { exitApplication() }
    ) {
        val offsetX by updateTransition(targetState = mainObject.screenState).animateFloat({
            keyframes {
                durationMillis = 500
                0f at 0
                800f at 250
            }
        }) { _ -> 0f }

        MaterialTheme(colors = lightColors(primary = Color(255, 104, 0), primaryVariant = Color(255, 79, 0))) {
            Box(
                Modifier.offset(x = offsetX.dp, y = 0.dp)
            ) {
                when (mainObject.screenState) {
                    ScreenState.MAIN -> MainScreen(mainObject.mainViewModel)
                    ScreenState.AUTH -> AuthScreen(mainObject.authViewModel)
                    ScreenState.PRODUCT -> ProductScreen(mainObject.productViewModel)
                }
            }
        }
    }
}






