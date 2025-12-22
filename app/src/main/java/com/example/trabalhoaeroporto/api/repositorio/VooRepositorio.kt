package com.example.trabalhoaeroporto.api.repositorio

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.trabalhoaeroporto.api.pagina.VooPagingSource
import com.example.trabalhoaeroporto.api.servico.AviationApiService
import javax.inject.Inject


class VooRepository @Inject constructor(
    private val aviationApiService: AviationApiService
) {


    fun getVoos() = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { VooPagingSource(aviationApiService) }
    ).flow


    suspend fun getVoosPartida(aeroportoIata: String) =
        aviationApiService.getVoosPartida(depIata = aeroportoIata)


    suspend fun getVoosChegada(aeroportoIata: String) =
        aviationApiService.getVoosChegada(arrIata = aeroportoIata)
}