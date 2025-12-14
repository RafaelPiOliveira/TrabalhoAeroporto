package com.example.trabalhoaeroporto.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabalhoaeroporto.api.Voo

@Composable
fun CardVoo(voo: Voo, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Número do voo e companhia aérea
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = voo.flight?.iata ?: "N/A",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Text(
                    text = voo.airline?.name ?: "Desconhecida",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Status do voo
            Text(
                text = "Status: ${voo.flightStatus ?: "N/A"}",
                fontSize = 12.sp,
                color = when(voo.flightStatus) {
                    "active" -> Color(0xFF4CAF50) // Verde
                    "landed" -> Color(0xFF2196F3) // Azul
                    "cancelled" -> Color(0xFFF44336) // Vermelho
                    else -> Color.Gray
                },
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Partida
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "PARTIDA",
                        fontSize = 10.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = voo.departure?.iata ?: "N/A",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = voo.departure?.airport ?: "",
                        fontSize = 11.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }

                Text(
                    text = "→",
                    fontSize = 24.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

                // Chegada
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "CHEGADA",
                        fontSize = 10.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = voo.arrival?.iata ?: "N/A",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = voo.arrival?.airport ?: "",
                        fontSize = 11.sp,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }
            }

            // Informações adicionais (portão, terminal)
            if (voo.departure?.gate != null || voo.departure?.terminal != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    voo.departure?.terminal?.let {
                        Text(
                            text = "Terminal: $it",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    voo.departure?.gate?.let {
                        Text(
                            text = "Portão: $it",
                            fontSize = 11.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}