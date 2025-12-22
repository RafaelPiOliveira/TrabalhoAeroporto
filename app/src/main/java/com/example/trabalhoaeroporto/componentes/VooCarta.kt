package com.example.trabalhoaeroporto.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
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
import com.example.trabalhoaeroporto.ui.theme.AmareloAtraso
import com.example.trabalhoaeroporto.ui.theme.AzulAeroporto
import com.example.trabalhoaeroporto.ui.theme.AzulAterrado
import com.example.trabalhoaeroporto.ui.theme.AzulCeu
import com.example.trabalhoaeroporto.ui.theme.BrancoCard
import com.example.trabalhoaeroporto.ui.theme.CinzaClaro
import com.example.trabalhoaeroporto.ui.theme.CinzaEscuro
import com.example.trabalhoaeroporto.ui.theme.CinzaMedio
import com.example.trabalhoaeroporto.ui.theme.Laranja
import com.example.trabalhoaeroporto.ui.theme.VerdeAtivo
import com.example.trabalhoaeroporto.ui.theme.Vermelho
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CardVoo(voo: Voo, onClick: () -> Unit = {}) {

    // Função para formatar hora (de ISO 8601 para HH:mm)
    fun formatarHora(isoDate: String?): String {
        if (isoDate == null) return "--:--"
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date = inputFormat.parse(isoDate)
            outputFormat.format(date ?: return "--:--")
        } catch (e: Exception) {
            "--:--"
        }
    }

    // Cor do status
    val corStatus = when(voo.flightStatus?.lowercase()) {
        "active", "scheduled" -> VerdeAtivo
        "landed" -> AzulAterrado
        "cancelled" -> Vermelho
        "delayed" -> AmareloAtraso
        else -> CinzaMedio
    }

    val textoStatus = when(voo.flightStatus?.lowercase()) {
        "active" -> "ATIVO"
        "scheduled" -> "AGENDADO"
        "landed" -> "ATERRADO"
        "cancelled" -> "CANCELADO"
        "delayed" -> "ATRASADO"
        else -> voo.flightStatus?.uppercase() ?: "N/A"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BrancoCard)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: Número do voo + Companhia + Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    // Número do voo
                    Text(
                        text = voo.flight?.iata ?: "N/A",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulAeroporto
                    )

                    // Companhia aérea
                    Text(
                        text = voo.airline?.name ?: "Companhia Desconhecida",
                        fontSize = 13.sp,
                        color = CinzaMedio
                    )
                }

                // Badge de status
                Box(
                    modifier = Modifier
                        .background(corStatus, RoundedCornerShape(6.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = textoStatus,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Rota: Partida → Chegada (com horas)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // PARTIDA
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "PARTIDA",
                        fontSize = 10.sp,
                        color = CinzaMedio,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Hora de partida
                    Text(
                        text = formatarHora(voo.departure?.scheduled),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulAeroporto
                    )

                    // Código IATA
                    Text(
                        text = voo.departure?.iata ?: "N/A",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CinzaEscuro
                    )

                    // Nome do aeroporto
                    Text(
                        text = voo.departure?.airport ?: "",
                        fontSize = 11.sp,
                        color = CinzaMedio,
                        maxLines = 2
                    )
                }

                // Seta
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "✈",
                        fontSize = 24.sp,
                        color = AzulCeu
                    )
                    Text(
                        text = "→",
                        fontSize = 20.sp,
                        color = CinzaMedio
                    )
                }

                // CHEGADA
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "CHEGADA",
                        fontSize = 10.sp,
                        color = CinzaMedio,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Hora de chegada
                    Text(
                        text = formatarHora(voo.arrival?.scheduled),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulAeroporto
                    )

                    // Código IATA
                    Text(
                        text = voo.arrival?.iata ?: "N/A",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CinzaEscuro
                    )

                    // Nome do aeroporto
                    Text(
                        text = voo.arrival?.airport ?: "",
                        fontSize = 11.sp,
                        color = CinzaMedio,
                        maxLines = 2
                    )
                }
            }
        }
    }
}
