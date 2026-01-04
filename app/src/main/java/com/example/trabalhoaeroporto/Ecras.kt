package com.example.trabalhoaeroporto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabalhoaeroporto.componentes.BotaoTipoVoo
import com.example.trabalhoaeroporto.componentes.CardVoo
import com.example.trabalhoaeroporto.componentes.CartaoErro
import com.example.trabalhoaeroporto.componentes.CartaoVazio
import com.example.trabalhoaeroporto.componentes.Header
import com.example.trabalhoaeroporto.componentes.InfoLinha
import com.example.trabalhoaeroporto.componentes.ListaVoos
import com.example.trabalhoaeroporto.ui.theme.AmareloAtraso
import com.example.trabalhoaeroporto.ui.theme.AzulCeu
import com.example.trabalhoaeroporto.ui.theme.DouradoPrincipal
import com.example.trabalhoaeroporto.ui.theme.FundoApp
import com.example.trabalhoaeroporto.ui.theme.FundoCard
import com.example.trabalhoaeroporto.ui.theme.FundoCardHover
import com.example.trabalhoaeroporto.ui.theme.LaranjaVivo
import com.example.trabalhoaeroporto.ui.theme.TextoBranco
import com.example.trabalhoaeroporto.ui.theme.TextoCinzaClaro
import com.example.trabalhoaeroporto.ui.theme.TextoCinzaEscuro
import com.example.trabalhoaeroporto.ui.theme.TextoCinzaMedio
import com.example.trabalhoaeroporto.ui.theme.VerdeAterrado
import com.example.trabalhoaeroporto.ui.theme.VerdeAtivo
import com.example.trabalhoaeroporto.ui.theme.VermelhoAlerta


@Composable
fun Ecra01() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoApp)
    ) {

        Header(
            titulo = "VOOS AO VIVO",
            subtitulo = "Monitorização em tempo real"
        )
        ListaVoos()
    }
}


@Composable
fun Ecra02(viewModel: VooViewModel, navController: NavController) {

    var aeroportoCodigo by rememberSaveable { mutableStateOf("") }
    var tipoVoo by rememberSaveable { mutableStateOf("partidas") }


    val voosPartida by viewModel.voosPartida.collectAsState()
    val voosChegada by viewModel.voosChegada.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoApp)
    ) {
        Header(
            titulo = "PESQUISA AVANÇADA",
            subtitulo = "Voos em tempo real por aeroporto"
        )

        Spacer(modifier = Modifier.height(20.dp))


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(24.dp),
                    spotColor = DouradoPrincipal.copy(alpha = 0.5f)
                ),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = FundoCard)
        ) {
            Column {

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

                Column(modifier = Modifier.padding(28.dp)) {
                    Text(
                        text = "CÓDIGO DO AEROPORTO",
                        fontSize = 11.sp,
                        color = DouradoPrincipal,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = aeroportoCodigo,
                        onValueChange = { aeroportoCodigo = it.uppercase() },
                        placeholder = {
                            Text("OPO, LIS, JFK, LAX...", color = TextoCinzaEscuro)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = DouradoPrincipal,
                            unfocusedBorderColor = TextoCinzaMedio,
                            focusedTextColor = TextoBranco,
                            unfocusedTextColor = TextoCinzaClaro,
                            cursorColor = DouradoPrincipal
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "TIPO DE VOO",
                        fontSize = 11.sp,
                        color = DouradoPrincipal,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        BotaoTipoVoo(
                            texto = "PARTIDAS",
                            selecionado = tipoVoo == "partidas",
                            onClick = { tipoVoo = "partidas" },
                            modifier = Modifier.weight(1f)
                        )

                        BotaoTipoVoo(
                            texto = "CHEGADAS",
                            selecionado = tipoVoo == "chegadas",
                            onClick = { tipoVoo = "chegadas" },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Button(
                        onClick = {
                            if (aeroportoCodigo.isNotBlank()) {
                                if (tipoVoo == "partidas") {
                                    viewModel.getVoosPartida(aeroportoCodigo)
                                } else {
                                    viewModel.getVoosChegada(aeroportoCodigo)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(14.dp),
                                spotColor = DouradoPrincipal.copy(alpha = 0.6f)
                            ),
                        enabled = aeroportoCodigo.isNotBlank() && !isLoading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            disabledContainerColor = TextoCinzaEscuro
                        ),
                        shape = RoundedCornerShape(14.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            DouradoPrincipal,
                                            LaranjaVivo
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isLoading) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(22.dp),
                                        color = Color.White,
                                        strokeWidth = 2.5.dp
                                    )
                                    Spacer(modifier = Modifier.width(14.dp))
                                    Text(
                                        "PESQUISANDO...",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = Color.White,
                                        letterSpacing = 1.2.sp
                                    )
                                }
                            } else {
                                Text(
                                    "PESQUISAR VOOS",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    letterSpacing = 1.2.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        error?.let {
            CartaoErro(mensagem = it)
            Spacer(modifier = Modifier.height(20.dp))
        }

            val voos = if (tipoVoo == "partidas") voosPartida else voosChegada

            voos?.let { response ->
                if (response.data.isEmpty()) {
                    CartaoVazio(
                        mensagem = "Nenhum voo encontrado",
                        descricao = "Sem voos em tempo real para $aeroportoCodigo"
                    )
                } else {
                    Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
                        LazyColumn {
                            items(response.data) { voo ->
                                CardVoo(
                                    voo = voo,
                                    onClick = {
                                        viewModel.setVooSelecionado(voo)
                                        navController.navigate(Destino.Ecra03.route)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun Ecra03(viewModel: VooViewModel) {
    val voo by viewModel.vooSelecionado.collectAsState()

    if (voo == null) {
        CartaoVazio(
            mensagem = "Nenhum voo selecionado",
            descricao = "Selecione um voo para ver os detalhes"
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoApp)
    ) {
        Header(
            titulo = "DETALHES DO VOO",
            subtitulo = voo!!.flight?.iata ?: "N/A"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(28.dp),
                    spotColor = DouradoPrincipal.copy(alpha = 0.5f)
                ),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                FundoCard,
                                FundoCardHover
                            )
                        )
                    )
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
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
                            .padding(28.dp)
                    ) {

                        InfoLinha(
                            titulo = "COMPANHIA AÉREA",
                            valor = voo!!.airline?.name
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        val corStatus = when(voo!!.flightStatus?.lowercase()) {
                            "active" -> VerdeAtivo
                            "scheduled" -> AzulCeu
                            "landed" -> VerdeAterrado
                            "cancelled" -> VermelhoAlerta
                            "delayed" -> AmareloAtraso
                            else -> TextoCinzaMedio
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "STATUS",
                                fontSize = 11.sp,
                                color = DouradoPrincipal,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp
                            )

                            Box(
                                modifier = Modifier
                                    .shadow(
                                        elevation = 8.dp,
                                        shape = RoundedCornerShape(12.dp),
                                        spotColor = corStatus.copy(alpha = 0.7f)
                                    )
                                    .background(
                                        Brush.horizontalGradient(
                                            colors = listOf(
                                                corStatus,
                                                corStatus.copy(alpha = 0.85f)
                                            )
                                        ),
                                        RoundedCornerShape(12.dp)
                                    )
                                    .padding(horizontal = 20.dp, vertical = 12.dp)
                            ) {
                                Text(
                                    text = voo!!.flightStatus?.uppercase() ?: "N/A",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    letterSpacing = 1.2.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(28.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            DouradoPrincipal.copy(alpha = 0.5f),
                                            Color.Transparent
                                        )
                                    )
                                )
                        )

                        Spacer(modifier = Modifier.height(28.dp))


                        InfoLinha(
                            titulo = "ORIGEM",
                            valor = "${voo!!.departure?.airport} (${voo!!.departure?.iata})"
                        )

                        Spacer(modifier = Modifier.height(20.dp))


                        InfoLinha(
                            titulo = "DESTINO",
                            valor = "${voo!!.arrival?.airport} (${voo!!.arrival?.iata})"
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(
                                    Brush.horizontalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            DouradoPrincipal.copy(alpha = 0.5f),
                                            Color.Transparent
                                        )
                                    )
                                )
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                InfoLinha(
                                    titulo = "PARTIDA",
                                    valor = voo!!.departure?.scheduled?.substring(0, 16)
                                        ?.replace("T", " "),
                                    compacto = true
                                )
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                InfoLinha(
                                    titulo = "CHEGADA",
                                    valor = voo!!.arrival?.scheduled?.substring(0, 16)
                                        ?.replace("T", " "),
                                    compacto = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



