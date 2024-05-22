import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.levgrekov.bank.navigation.NavigationHost
import ru.levgrekov.bank.navigation.ScreenState
import ru.levgrekov.bank.navigation.composable
import ru.levgrekov.bank.navigation.rememberNavController
import ru.levgrekov.bank.ui.screens.auth.AuthScreen
import ru.levgrekov.bank.ui.screens.auth.AuthViewModel
import ru.levgrekov.bank.ui.screens.main.MainScreen
import ru.levgrekov.bank.ui.screens.main.MainViewModel
import ru.levgrekov.bank.ui.theme.CustomTheme

fun main() = application {
    val authViewModel = AuthViewModel()
    val mainViewModel = MainViewModel()


    val navController by rememberNavController(ScreenState.AUTH.name)

    Window(
        state = rememberWindowState(
            width = 450.dp,
            height = 650.dp,
            placement = WindowPlacement.Floating,
        ),
        onCloseRequest = { exitApplication() }
    ) {
        CustomTheme {
            Surface(
                modifier = Modifier.background(color = MaterialTheme.colors.background)
            ) {
                NavigationHost(navController) {
                    composable(ScreenState.MAIN.name) {
                        MainScreen(mainViewModel, navController)
                    }

                    composable(ScreenState.AUTH.name) {
                        AuthScreen(authViewModel, navController)
                    }

//                    composable(ScreenState.PRODUCT::class.toString()) {
//                        ProductScreen(productViewModel,navController)
//                    }

                }.build()
            }
        }
    }
}






