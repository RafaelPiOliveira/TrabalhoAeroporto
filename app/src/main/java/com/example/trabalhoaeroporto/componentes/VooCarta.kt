package com.example.trabalhoaeroporto.componentes

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabalhoaeroporto.api.Voo
import com.example.trabalhoaeroporto.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CardVoo(voo: Voo, onClick: () -> Unit = {}) {

    // Função para formatar hora
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

    // Cor do status (cores vibrantes tipo Flightradar24)
    val corStatus = when(voo.flightStatus?.lowercase()) {
        "active" -> VerdeAtivo
        "scheduled" -> AzulCeu
        "landed" -> VerdeAterrado
        "cancelled" -> VermelhoAlerta
        "delayed" -> AmareloAtraso
        else -> TextoCinzaMedio
    }

    val textoStatus = when(voo.flightStatus?.lowercase()) {
        "active" -> "EM VOO"
        "scheduled" -> "AGENDADO"
        "landed" -> "ATERRADO"
        "cancelled" -> "CANCELADO"
        "delayed" -> "ATRASADO"
        else -> voo.flightStatus?.uppercase() ?: "N/A"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = DouradoPrincipal.copy(alpha = 0.3f)
            )
            .clickable { onClick() }
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = FundoCard)
    ) {
        // Barra dourada no topo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
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

        Column(modifier = Modifier.fillMaxWidth()) {
            // Barra dourada superior
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header: Número do voo + Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        // Número do voo
                        Text(
                            text = voo.flight?.iata ?: "N/A",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextoBranco,
                            letterSpacing = 1.5.sp
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        // Companhia aérea
                        Text(
                            text = voo.airline?.name ?: "Companhia Desconhecida",
                            fontSize = 14.sp,
                            color = TextoCinzaMedio,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Badge de status com glow
                    Box(
                        modifier = Modifier
                            .shadow(
                                elevation = 6.dp,
                                shape = RoundedCornerShape(10.dp),
                                spotColor = corStatus.copy(alpha = 0.6f)
                            )
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        corStatus,
                                        corStatus.copy(alpha = 0.85f)
                                    )
                                ),
                                RoundedCornerShape(10.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = textoStatus,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = if (textoStatus.contains("ATRASADO")) Color.Black else Color.White,
                            letterSpacing = 0.8.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Linha divisória dourada sutil
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    DouradoPrincipal.copy(alpha = 0.4f),
                                    Color.Transparent
                                )
                            )
                        )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Rota: Partida → Chegada
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // PARTIDA
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "PARTIDA",
                            fontSize = 10.sp,
                            color = DouradoPrincipal,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Hora
                        Text(
                            text = formatarHora(voo.departure?.scheduled),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextoBranco,
                            letterSpacing = (-1).sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Código IATA
                        Text(
                            text = voo.departure?.iata ?: "N/A",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulCiano
                        )

                        // Nome do aeroporto
                        Text(
                            text = voo.departure?.airport ?: "",
                            fontSize = 11.sp,
                            color = TextoCinzaMedio,
                            maxLines = 2,
                            lineHeight = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    // Ícone de avião central com linha
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "✈",
                            fontSize = 32.sp,
                            color = DouradoPrincipal
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .height(2.dp)
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            AzulCiano,
                                            DouradoPrincipal
                                        )
                                    )
                                )
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
                            color = DouradoPrincipal,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Hora
                        Text(
                            text = formatarHora(voo.arrival?.scheduled),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextoBranco,
                            letterSpacing = (-1).sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Código IATA
                        Text(
                            text = voo.arrival?.iata ?: "N/A",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulCiano
                        )

                        // Nome do aeroporto
                        Text(
                            text = voo.arrival?.airport ?: "",
                            fontSize = 11.sp,
                            color = TextoCinzaMedio,
                            maxLines = 2,
                            lineHeight = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun CartaoErro(mensagem: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = VermelhoAlerta.copy(alpha = 0.15f)
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "⚠",
                fontSize = 24.sp,
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = mensagem,
                color = VermelhoAlerta,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

/**
 * Componente reutilizável: Cartão Vazio (sem resultados)
 */
@Composable
fun CartaoVazio(mensagem: String, descricao: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = FundoCard
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "✈",
                fontSize = 56.sp,
                color = TextoCinzaMedio
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = mensagem,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextoCinzaClaro
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = descricao,
                fontSize = 14.sp,
                color = TextoCinzaMedio,
                textAlign = TextAlign.Center
            )
        }
    }
}