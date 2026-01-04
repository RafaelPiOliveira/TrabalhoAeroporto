package com.example.trabalhoaeroporto.api.servico

import com.example.trabalhoaeroporto.api.VooResponse
import com.example.trabalhoaeroporto.constantes.Constants
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface Retrofit para comunicar com a AviationStack API
 *
 * Documentação: https://aviationstack.com/documentation
 */
interface AviationApiService {

    /**
     * Obter voos em tempo real
     *
     * @param apiKey - Chave da API (já definida nas constantes)
     * @param limit - Número de resultados por página (default: 20)
     * @param offset - Offset para paginação
     */
    @GET("flights")
    suspend fun getVoos(
        @Query("access_key") apiKey: String = Constants.API_KEY,
        @Query("dep_iata") depIata: String = "OPO",
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): VooResponse
    @GET("flights")
    suspend fun getVoosPartida(
        @Query("access_key") apiKey: String = Constants.API_KEY,
        @Query("dep_iata") depIata: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): VooResponse


    @GET("flights")
    suspend fun getVoosChegada(
        @Query("access_key") apiKey: String = Constants.API_KEY,
        @Query("arr_iata") arrIata: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): VooResponse
}