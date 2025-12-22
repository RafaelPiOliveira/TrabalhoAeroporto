package com.example.trabalhoaeroporto.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabalhoaeroporto.ui.theme.CinzaEscuro
import com.example.trabalhoaeroporto.ui.theme.CinzaMedio

@Composable
fun InfoLinha(titulo: String, valor: String?) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {

        Text(
            text = titulo,
            fontSize = 12.sp,
            color = CinzaMedio
        )

        Text(
            text = valor ?: "N/D",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = CinzaEscuro
        )
    }
}