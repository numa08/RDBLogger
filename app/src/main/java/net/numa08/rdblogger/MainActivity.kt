package net.numa08.rdblogger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.numa08.rdblogger.ui.LogListScreen
import net.numa08.rdblogger.ui.MainScreen
import net.numa08.rdblogger.ui.theme.RDBLoggerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RDBLoggerTheme {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                navigateNext = {
                    navController.navigate("list")
                }
            )
        }
        composable("list") {
            LogListScreen()
        }
    }
}