package com.example.trabalhoaeroporto.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val FundoApp = Color(0xFF0A0E1A)              // Azul escuro quase preto (fundo principal)
val FundoCard = Color(0xFF1A1F35)             // Azul escuro para cards
val FundoCardHover = Color(0xFF252B42)        // Azul escuro hover/pressed
val FundoSecundario = Color(0xFF141829)       // Alternativo mais escuro

// === DOURADOS E LARANJAS (PREMIUM/DESTAQUE) ===
val DouradoPrincipal = Color(0xFFFFB020)      // Dourado vibrante principal
val DouradoEscuro = Color(0xFFCC8800)         // Dourado mais escuro
val DouradoClaro = Color(0xFFFFD666)          // Dourado claro (hover)
val LaranjaVivo = Color(0xFFFF8C00)           // Laranja intenso
val LaranjaEscuro = Color(0xFFFF6B35)         // Laranja avermelhado

// === AZUIS (INFORMAÇÃO/CÉUS) ===
val AzulCiano = Color(0xFF00D4FF)             // Ciano brilhante (aviões ativos)
val AzulCeu = Color(0xFF4FB3F7)               // Azul céu claro
val AzulElectrico = Color(0xFF2196F3)         // Azul eléctrico (links)
val AzulEscuroPrincipal = Color(0xFF0F1419)   // Azul muito escuro

// === TEXTOS ===
val TextoBranco = Color(0xFFFFFFFF)           // Texto principal branco puro
val TextoCinzaClaro = Color(0xFFE5E5E5)       // Texto secundário claro
val TextoCinzaMedio = Color(0xFFAAAAAA)       // Texto terciário
val TextoCinzaEscuro = Color(0xFF707070)      // Texto desativado

// === ESTADOS DOS VOOS (VIBRANTES) ===
val VerdeAtivo = Color(0xFF00E676)            // Verde neon - voo ativo/em voo
val VerdeAterrado = Color(0xFF00C853)         // Verde escuro - aterrado com sucesso
val AmareloAtraso = Color(0xFFFFD700)         // Amarelo ouro - atrasado
val VermelhoAlerta = Color(0xFFFF1744)        // Vermelho vibrante - cancelado/alerta
val LaranjaEmbarque = Color(0xFFFF9100)       // Laranja - embarque/preparação

// === TRANSPARÊNCIAS ===
val BrancoTransparente10 = Color(0x1AFFFFFF)
val BrancoTransparente20 = Color(0x33FFFFFF)
val BrancoTransparente30 = Color(0x4DFFFFFF)
val PretoTransparente50 = Color(0x80000000)
val PretoTransparente70 = Color(0xB3000000)

// === GRADIENTES (use com Brush.horizontalGradient ou verticalGradient) ===
val GradienteDouradoInicio = DouradoPrincipal
val GradienteDouradoFim = LaranjaVivo
val GradienteAzulInicio = Color(0xFF1E3C72)
val GradienteAzulFim = Color(0xFF2A5298)
val GradienteFundoInicio = FundoApp
val GradienteFundoFim = FundoSecundario