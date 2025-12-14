package com.example.trabalhoaeroporto

sealed class Destino(val route: String, val icon: Int, val title: String) {
    object Ecra01 : Destino(route = "ecra01", icon = R.drawable.list, title = "Voos")
    object Ecra02 : Destino(route = "ecra02", icon = R.drawable.favorite, title = "Favoritos")
    object Ecra03 : Destino(route = "ecra03", icon = R.drawable.search, title = "Pesquisar")
    object Ecra04 : Destino(route = "ecra04", icon = R.drawable.info, title = "Partidas")
    object Ecra05 : Destino(route = "ecra05", icon = R.drawable.perfil, title = "Chegadas")
    companion object {
        val toList = listOf(Ecra01, Ecra02, Ecra03, Ecra04, Ecra05)
    }
}