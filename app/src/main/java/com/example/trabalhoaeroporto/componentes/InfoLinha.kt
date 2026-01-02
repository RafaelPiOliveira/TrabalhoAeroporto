package com.example.trabalhoaeroporto.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*

import com.example.trabalhoaeroporto.ui.theme.DouradoPrincipal
import com.example.trabalhoaeroporto.ui.theme.FundoApp
import com.example.trabalhoaeroporto.ui.theme.FundoCard
import com.example.trabalhoaeroporto.ui.theme.LaranjaVivo
import com.example.trabalhoaeroporto.ui.theme.TextoBranco
import com.example.trabalhoaeroporto.ui.theme.TextoCinzaMedio

@Composable
fun InfoLinha(  icone: String,
    titulo: String,
    valor: String?,
    compacto: Boolean = false
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = icone,
                    fontSize = if (compacto) 16.sp else 18.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = titulo,
                    fontSize = if (compacto) 10.sp else 11.sp,
                    color = DouradoPrincipal,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = valor ?: "N/D",
                fontSize = if (compacto) 14.sp else 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextoBranco,
                modifier = Modifier.padding(start = if (compacto) 24.dp else 26.dp)
            )
        }
}
@Composable
fun HeaderPremium(icone: String, titulo: String, subtitulo: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        FundoCard,
                        FundoApp
                    )
                )
            )
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "$icone $titulo",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextoBranco,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subtitulo,
                fontSize = 14.sp,
                color = DouradoPrincipal,
                fontWeight = FontWeight.Medium
            )
        }

        // Linha dourada inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            DouradoPrincipal,
                            LaranjaVivo,
                            DouradoPrincipal,
                            Color.Transparent
                        )
                    )
                )
        )
    }
}

/**
 * Componente reutilizável: Botão Tipo de Voo
 */
@Composable
fun BotaoTipoVoo(
    texto: String,
    selecionado: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(60.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor= if (selecionado)
                DouradoPrincipal.copy(alpha = 0.2f) else Color.Transparent
        ),
        border = androidx.compose.foundation.BorderStroke(
            2.5.dp,
            if (selecionado) DouradoPrincipal else TextoCinzaMedio
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Text(
            texto,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = if (selecionado) DouradoPrincipal else TextoCinzaMedio
        )
    }
}