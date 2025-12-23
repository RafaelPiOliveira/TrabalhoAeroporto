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

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface

import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrabalhoAeroportoTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
        composable(Destino.Ecra04.route) {
            Ecra04()
        }
        composable(Destino.Ecra05.route) {
            Ecra05()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, appItems: List<Destino>) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.purple_700),
        contentColor = Color.White
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
                selectedContentColor = Color.White, // esta instrução devia funcionar para o efeito (animação), para o ícone e para a cor do texto, mas só funciona para o efeito
                unselectedContentColor = Color.White.copy(0.4f), // esta instrução não funciona, por isso resolve-se acima no 'tint' do icon e na 'color' da label
                alwaysShowLabel = true, // colocar 'false' significa que o texto só aparece debaixo do ícone selecionado (em vez de debaixo de todos)
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