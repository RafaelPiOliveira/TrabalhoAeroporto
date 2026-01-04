package com.example.trabalhoaeroporto


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.trabalhoaeroporto.ui.theme.TrabalhoAeroportoTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trabalhoaeroporto.ui.theme.FundoApp
import com.example.trabalhoaeroporto.ui.theme.TextoCinzaClaro
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrabalhoAeroportoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = FundoApp) {
                    ProgramaPrincipal()
                }
            }
        }
    }
}
@Composable
fun ProgramaPrincipal() {
    val navController = rememberNavController()
    Scaffold(
        containerColor = FundoApp,
        bottomBar = { BottomNavigationBar(navController = navController, appItems = Destino.toList) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AppNavigation(navController = navController)
            }
        }
    )
}

@Composable
fun AppNavigation(navController: NavHostController) {
    val viewModel: VooViewModel = hiltViewModel()
    NavHost(navController, startDestination = Destino.Ecra01.route) {
        composable(Destino.Ecra01.route) {
            Ecra01()
        }
        composable(Destino.Ecra02.route) {
            Ecra02(viewModel,navController)
        }
        composable(Destino.Ecra03.route) {
            Ecra03( viewModel )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, appItems: List<Destino>) {
    BottomNavigation(
        backgroundColor = Color.Transparent,
        contentColor = TextoCinzaClaro,
        elevation = 0.dp,
        modifier = Modifier.height(72.dp)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        appItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.title,
                        tint = if (currentRoute == item.route) Color.White else Color.White.copy(.4F)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        color = if (currentRoute == item.route) Color.White else Color.White.copy(
                            .4F
                        )
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(0) { saveState = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}