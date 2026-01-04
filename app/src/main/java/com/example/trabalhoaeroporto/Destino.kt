package com.example.trabalhoaeroporto

sealed class Destino(val route: String, val icon: Int, val title: String) {
    object Ecra01 : Destino(route = "ecra01", icon = R.drawable.voos, title = "Voos")
    object Ecra02 : Destino(route = "ecra02", icon = R.drawable.search, title = "Pesquisar")
    object Ecra03 : Destino(route = "ecra03", icon = R.drawable.details, title = "Detalhes")
    companion object {
        val toList = listOf(Ecra01, Ecra02, Ecra03)
    }
}