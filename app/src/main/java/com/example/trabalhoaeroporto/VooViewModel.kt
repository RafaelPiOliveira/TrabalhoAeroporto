package com.example.trabalhoaeroporto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.trabalhoaeroporto.api.Voo
import com.example.trabalhoaeroporto.api.VooResponse
import com.example.trabalhoaeroporto.api.repositorio.VooRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel principal da aplicação
 * Gere os dados dos voos e comunica com o Repository
 */
@HiltViewModel
class VooViewModel @Inject constructor(
    private val repository: VooRepository
) : ViewModel() {
    private val VooSelecionado = MutableStateFlow<Voo?>(null)
    val vooSelecionado: StateFlow<Voo?> = VooSelecionado

    fun setVooSelecionado(voo: Voo) {
        VooSelecionado.value = voo
    }

    // Flow com voos paginados (para lista principal)
    fun getVoos(): Flow<PagingData<Voo>> =
        repository.getVoos().cachedIn(viewModelScope)

    // Estado para resultado de pesquisa de voo específico
    private val _vooPesquisado = MutableStateFlow<VooResponse?>(null)
    val vooPesquisado: StateFlow<VooResponse?> = _vooPesquisado


    private val _voosPartida = MutableStateFlow<VooResponse?>(null)
    val voosPartida: StateFlow<VooResponse?> = _voosPartida


    private val _voosChegada = MutableStateFlow<VooResponse?>(null)
    val voosChegada: StateFlow<VooResponse?> = _voosChegada


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    /**
     * Obter voos de partida de um aeroporto
     */
    fun getVoosPartida(aeroportoIata: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val resultado = repository.getVoosPartida(aeroportoIata)
                _voosPartida.value = resultado
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao carregar partidas"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Obter voos de chegada de um aeroporto
     */
    fun getVoosChegada(aeroportoIata: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val resultado = repository.getVoosChegada(aeroportoIata)
                _voosChegada.value = resultado
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao carregar chegadas"
            } finally {
                _isLoading.value = false
            }
        }
    }
}