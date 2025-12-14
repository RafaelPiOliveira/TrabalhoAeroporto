package com.example.trabalhoaeroporto.api.pagina

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.trabalhoaeroporto.api.Voo
import com.example.trabalhoaeroporto.api.servico.AviationApiService

/**
 * PagingSource para carregar voos paginados
 * Carrega 20 voos de cada vez
 */
class VooPagingSource(
    private val aviationApiService: AviationApiService
) : PagingSource<Int, Voo>() {

    override fun getRefreshKey(state: PagingState<Int, Voo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(20)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Voo> {
        return try {
            // Offset para a paginação (0, 20, 40, 60...)
            val offset = params.key ?: 0

            // Fazer chamada à API
            val response = aviationApiService.getVoos(offset = offset)

            // Retornar resultado com dados paginados
            LoadResult.Page(
                data = response.data,
                prevKey = if (offset == 0) null else offset - 20,
                nextKey = if (response.data.isEmpty()) null else offset + 20,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}