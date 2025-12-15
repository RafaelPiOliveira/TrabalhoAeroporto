package com.example.trabalhoaeroporto

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
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
    val vooPesquisado by viewModel.vooPesquisado.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pesquisar Voo",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de pesquisa
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Número do voo (ex: AA100)") },
            placeholder = { Text("Ex: AA100, BA123...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Botão de pesquisar
        Button(
            onClick = {
                if (searchText.isNotBlank()) {
                    viewModel.pesquisarVoo(searchText.trim())
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = searchText.isNotBlank() && !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(text = if (isLoading) "A pesquisar..." else "Pesquisar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar erro se houver
        error?.let {
            Text(
                text = "Erro: $it",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Mostrar resultados
        vooPesquisado?.let { response ->
            if (response.data.isEmpty()) {
                Text(
                    text = "Nenhum voo encontrado com o código '$searchText'",
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