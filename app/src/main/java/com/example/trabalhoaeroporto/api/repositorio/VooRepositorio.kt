package com.example.trabalhoaeroporto.api.repositorio

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.trabalhoaeroporto.api.pagina.VooPagingSource
import com.example.trabalhoaeroporto.api.servico.AviationApiService
import javax.inject.Inject

/**
 * Repository que fornece acesso aos dados dos voos
 * Usa Paging para carregar dados em blocos de 20
 */
class VooRepository @Inject constructor(
    private val aviationApiService: AviationApiService
) {

    /**
     * Obter voos com paginação
     * Cada página tem 20 voos
     */
    fun getVoos() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { VooPagingSource(aviationApiService) }
    ).flow

    /**
     * Pesquisar voo específico por número IATA
     * Exemplo: pesquisarVoo("AA100")
     */
    suspend fun pesquisarVoo(flightIata: String) =
        aviationApiService.pesquisarVoo(flightIata = flightIata)

    /**
     * Obter voos de partida de um aeroporto
     * Exemplo: getVoosPartida("JFK")
     */
    suspend fun getVoosPartida(aeroportoIata: String) =
        aviationApiService.getVoosPartida(depIata = aeroportoIata)

    /**
     * Obter voos de chegada de um aeroporto
     * Exemplo: getVoosChegada("LAX")
     */
    suspend fun getVoosChegada(aeroportoIata: String) =
        aviationApiService.getVoosChegada(arrIata = aeroportoIata)
}