package com.example.trabalhoaeroporto.componentes

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.trabalhoaeroporto.VooViewModel


@Composable
fun ListaVoos() {
    val viewModel = hiltViewModel<VooViewModel>()
    val voos = viewModel.getVoos().collectAsLazyPagingItems()
    val oContexto = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = voos.itemCount,
            key = voos.itemKey { it.flight?.iata ?: it.hashCode() },
            contentType = voos.itemContentType { "vooContentType" }
        ) { index ->
            val item = voos[index]
            item?.let {
                CardVoo(voo = it)
            }
        }

        // Estado de carregamento inicial
        when (voos.loadState.refresh) {
            is LoadState.Error -> {
                val errorMsg = (voos.loadState.refresh as LoadState.Error).error.message
                Toast.makeText(
                    oContexto,
                    "Erro ao carregar: $errorMsg",
                    Toast.LENGTH_LONG
                ).show()
            }
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = Color.Blue)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "A carregar voos...", color = Color.Gray)
                    }
                }
            }
            else -> {}
        }

        // Estado de carregamento de mais itens (paginação)
        when (voos.loadState.append) {
            is LoadState.Error -> {
                val errorMsg = (voos.loadState.append as LoadState.Error).error.message
                Toast.makeText(
                    oContexto,
                    "Erro ao carregar mais: $errorMsg",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = Color.Blue)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "A carregar mais...", color = Color.Gray)
                    }
                }
            }
            else -> {}
        }
    }
}