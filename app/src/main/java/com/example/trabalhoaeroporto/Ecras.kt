package com.example.trabalhoaeroporto

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.trabalhoaeroporto.componentes.CardVoo
import com.example.trabalhoaeroporto.componentes.InfoLinha
import com.example.trabalhoaeroporto.componentes.ListaVoos
import com.example.trabalhoaeroporto.ui.theme.AmareloAtraso
import com.example.trabalhoaeroporto.ui.theme.AzulAeroporto
import com.example.trabalhoaeroporto.ui.theme.AzulCeu

import com.example.trabalhoaeroporto.ui.theme.BrancoCard
import com.example.trabalhoaeroporto.ui.theme.CinzaClaro
import com.example.trabalhoaeroporto.ui.theme.CinzaEscuro
import com.example.trabalhoaeroporto.ui.theme.CinzaMedio
import com.example.trabalhoaeroporto.ui.theme.Vermelho

@Composable
fun Ecra01() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Text(
            text = "Voos em Tempo Real",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        ListaVoos()
    }
}


@Composable
fun Ecra02(viewModel: VooViewModel, navController: NavController) {
    var aeroportoCodigo by remember { mutableStateOf("") }
    var tipoVoo by remember { mutableStateOf("partidas") } // "partidas" ou "chegadas"
    var mostrarResultados by remember { mutableStateOf(false) }

    val voosPartida by viewModel.voosPartida.collectAsState()
    val voosChegada by viewModel.voosChegada.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CinzaClaro)
            .padding(16.dp)
    ) {
        // Header
        Text(
            text = "Voos em Tempo Real",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AzulAeroporto,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Ver voos que estão a voar AGORA",
            fontSize = 14.sp,
            color = CinzaMedio,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Card de pesquisa
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = BrancoCard)
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                // Campo aeroporto
                Text(
                    text = "Aeroporto",
                    fontSize = 12.sp,
                    color = CinzaMedio,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = aeroportoCodigo,
                    onValueChange = { aeroportoCodigo = it.uppercase() },
                    label = { Text("Código IATA") },
                    placeholder = { Text("Ex: OPO, LIS, LHR, JFK") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tipo de voo (Partidas ou Chegadas)
                Text(
                    text = "Tipo de Voo",
                    fontSize = 12.sp,
                    color = CinzaMedio,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Botão Partidas
                    OutlinedButton(
                        onClick = { tipoVoo = "partidas" },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (tipoVoo == "partidas") AzulCeu.copy(alpha = 0.1f) else Color.Transparent
                        ),
                        border = BorderStroke(
                            2.dp,
                            if (tipoVoo == "partidas") AzulCeu else CinzaMedio
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                "PARTIDAS",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (tipoVoo == "partidas") AzulCeu else CinzaMedio
                            )
                        }
                    }

                    // Botão Chegadas
                    OutlinedButton(
                        onClick = { tipoVoo = "chegadas" },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (tipoVoo == "chegadas") AzulCeu.copy(alpha = 0.1f) else Color.Transparent
                        ),
                        border = BorderStroke(
                            2.dp,
                            if (tipoVoo == "chegadas") AzulCeu else CinzaMedio
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                "CHEGADAS",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (tipoVoo == "chegadas") AzulCeu else CinzaMedio
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão pesquisar
                Button(
                    onClick = {
                        if (aeroportoCodigo.isNotBlank()) {
                            if (tipoVoo == "partidas") {
                                viewModel.getVoosPartida(aeroportoCodigo)
                            } else {
                                viewModel.getVoosChegada(aeroportoCodigo)
                            }
                            mostrarResultados = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = aeroportoCodigo.isNotBlank() && !isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulAeroporto
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("A pesquisar...", fontSize = 16.sp)
                    } else {
                        Text(
                            text = "PESQUISAR",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Info sobre limitação
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = AmareloAtraso.copy(alpha = 0.1f)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mostra apenas voos a voar AGORA (tempo real)",
                            fontSize = 11.sp,
                            color = CinzaEscuro
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar erro
        error?.let {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Vermelho.copy(alpha = 0.1f)
                )
            ) {
                Text(
                    text = "$it",
                    color = Vermelho,
                    modifier = Modifier.padding(12.dp),
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Resultados
        if (mostrarResultados) {
            val voos = if (tipoVoo == "partidas") voosPartida else voosChegada
            val titulo = if (tipoVoo == "partidas") "PARTIDAS" else "CHEGADAS"

            voos?.let { response ->
                if (response.data.isEmpty()) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = CinzaClaro
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Nenhum voo encontrado",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = CinzaEscuro
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Não há voos a voar AGORA de/para $aeroportoCodigo",
                                fontSize = 12.sp,
                                color = CinzaMedio,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    Column {
                        Text(
                            text = "$titulo DE $aeroportoCodigo",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulAeroporto,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )

                        Text(
                            text = "${response.data.size} voos encontrados",
                            fontSize = 12.sp,
                            color = CinzaMedio,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LazyColumn {
                            items(response?.data ?: emptyList()) { voo ->
                                CardVoo(
                                    voo = voo,
                                    onClick = {
                                        viewModel.setVooSelecionado(voo)
                                        navController.navigate("ecra02")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Ecra03( viewModel: VooViewModel) {

    val voo by viewModel.vooSelecionado.collectAsState()

    if (voo == null) {
        // Caso raro: utilizador entrou direto no ecrã
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Nenhum voo selecionado", color = Color.Gray)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CinzaClaro)
            .padding(16.dp)
    ) {

        Text(
            text = "Detalhes do Voo",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = AzulAeroporto
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            colors = CardDefaults.cardColors(containerColor = BrancoCard)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                InfoLinha("Companhia", voo!!.airline?.name)
                InfoLinha("Voo", voo!!.flight?.iata)
                InfoLinha("Estado", voo!!.flightStatus?.uppercase())

                Spacer(modifier = Modifier.height(12.dp))

                Divider()

                Spacer(modifier = Modifier.height(12.dp))

                InfoLinha(
                    "Origem",
                    "${voo!!.departure?.airport} (${voo!!.departure?.iata})"
                )

                InfoLinha(
                    "Destino",
                    "${voo!!.arrival?.airport} (${voo!!.arrival?.iata})"
                )

                Spacer(modifier = Modifier.height(12.dp))

                Divider()

                Spacer(modifier = Modifier.height(12.dp))

                InfoLinha("Partida prevista", voo!!.departure?.scheduled)
                InfoLinha("Chegada prevista", voo!!.arrival?.scheduled)
            }
        }
    }
}


/**
 * ECRÃ 04 - Partidas
 * Mostra voos de partida de um aeroporto
 */
@Composable
fun Ecra04() {
    val viewModel = hiltViewModel<VooViewModel>()
    val voosPartida by viewModel.voosPartida.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var aeroportoCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Partidas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo aeroporto
        OutlinedTextField(
            value = aeroportoCode,
            onValueChange = { aeroportoCode = it.uppercase() },
            label = { Text("Código do aeroporto") },
            placeholder = { Text("Ex: JFK, LAX, LIS...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (aeroportoCode.isNotBlank()) {
                    viewModel.getVoosPartida(aeroportoCode.trim())
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = aeroportoCode.isNotBlank() && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = if (isLoading) "A carregar..." else "Ver Partidas")
        }

        Spacer(modifier = Modifier.height(16.dp))

        error?.let {
            Text(
                text = "Erro: $it",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        voosPartida?.let { response ->
            if (response.data.isEmpty()) {
                Text(
                    text = "Nenhuma partida encontrada para '$aeroportoCode'",
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn {
                    items(response.data) { voo ->
                        CardVoo(voo = voo)
                    }
                }
            }
        }
    }
}

/**
 * ECRÃ 05 - Chegadas
 * Mostra voos de chegada a um aeroporto
 */
@Composable
fun Ecra05() {
    val viewModel = hiltViewModel<VooViewModel>()
    val voosChegada by viewModel.voosChegada.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var aeroportoCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Chegadas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = aeroportoCode,
            onValueChange = { aeroportoCode = it.uppercase() },
            label = { Text("Código do aeroporto") },
            placeholder = { Text("Ex: JFK, LAX, LIS...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (aeroportoCode.isNotBlank()) {
                    viewModel.getVoosChegada(aeroportoCode.trim())
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = aeroportoCode.isNotBlank() && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = if (isLoading) "A carregar..." else "Ver Chegadas")
        }

        Spacer(modifier = Modifier.height(16.dp))

        error?.let {
            Text(
                text = "Erro: $it",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        voosChegada?.let { response ->
            if (response.data.isEmpty()) {
                Text(
                    text = "Nenhuma chegada encontrada para '$aeroportoCode'",
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn {
                    items(response.data) { voo ->
                        CardVoo(voo = voo)
                    }
                }
            }
        }
    }
}