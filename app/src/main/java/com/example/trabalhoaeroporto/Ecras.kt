package com.example.trabalhoaeroporto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.trabalhoaeroporto.componentes.CardVoo
import com.example.trabalhoaeroporto.componentes.ListaVoos
import com.example.trabalhoaeroporto.ui.theme.AzulAeroporto

import com.example.trabalhoaeroporto.ui.theme.BrancoCard
import com.example.trabalhoaeroporto.ui.theme.CinzaClaro
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
fun Ecra02() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Favoritos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Aqui poderás guardar os teus voos favoritos!",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Ecra03() {
    val viewModel = hiltViewModel<VooViewModel>()

    var aeroportoOrigem by remember { mutableStateOf("OPO") }
    var aeroportoDestino by remember { mutableStateOf("") }
    var mostrarResultados by remember { mutableStateOf(false) }

    val voosIda by viewModel.voosPartida.collectAsState()
    val voosVolta by viewModel.voosChegada.collectAsState()
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
            text = "Pesquisa de Voos",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AzulAeroporto,
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
                // Origem
                Text(
                    text = "DE (Origem)",
                    fontSize = 12.sp,
                    color = CinzaMedio,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = aeroportoOrigem,
                    onValueChange = { aeroportoOrigem = it.uppercase() },
                    label = { Text("Código IATA") },
                    placeholder = { Text("Ex: OPO, LIS, FAO") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Ícone de troca
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                }
                Spacer(modifier = Modifier.height(16.dp))

                // Destino
                Text(
                    text = "PARA (Destino)",
                    fontSize = 12.sp,
                    color = CinzaMedio,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = aeroportoDestino,
                    onValueChange = { aeroportoDestino = it.uppercase() },
                    label = { Text("Código IATA") },
                    placeholder = { Text("Ex: KIX (Osaka), LHR (Londres)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Botão pesquisar
                Button(
                    onClick = {
                        if (aeroportoOrigem.isNotBlank() && aeroportoDestino.isNotBlank()) {
                            viewModel.getVoosPartida(aeroportoOrigem)
                            viewModel.getVoosChegada(aeroportoDestino)
                            mostrarResultados = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    enabled = aeroportoOrigem.isNotBlank() &&
                            aeroportoDestino.isNotBlank() &&
                            !isLoading,
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
                            text = "PESQUISAR VOOS",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Exemplos: OPO→KIX (Osaka), OPO→LHR (Londres), LIS→CDG (Paris)",
                    fontSize = 11.sp,
                    color = CinzaMedio
                )
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
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                // VOOS DE IDA
                item {
                    Text(
                        text = "VOOS DE IDA: $aeroportoOrigem → $aeroportoDestino",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulAeroporto,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }

                voosIda?.let { response ->
                    val voosFiltrados = response.data.filter {
                        it.departure?.iata?.equals(aeroportoOrigem, ignoreCase = true) == true &&
                                it.arrival?.iata?.equals(aeroportoDestino, ignoreCase = true) == true
                    }

                    if (voosFiltrados.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Nenhum voo encontrado",
                                    color = CinzaMedio,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    } else {
                        items(voosFiltrados) { voo ->
                            CardVoo(voo = voo)
                        }
                    }
                }

                // VOOS DE VOLTA
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "VOOS DE VOLTA: $aeroportoDestino → $aeroportoOrigem",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulAeroporto,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }

                voosVolta?.let { response ->
                    val voosVoltaFiltrados = response.data.filter {
                        it.departure?.iata?.equals(aeroportoDestino, ignoreCase = true) == true &&
                                it.arrival?.iata?.equals(aeroportoOrigem, ignoreCase = true) == true
                    }

                    if (voosVoltaFiltrados.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Nenhum voo encontrado",
                                    color = CinzaMedio,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    } else {
                        items(voosVoltaFiltrados) { voo ->
                            CardVoo(voo = voo)
                        }
                    }
                }
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