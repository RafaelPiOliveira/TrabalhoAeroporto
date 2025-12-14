package com.example.trabalhoaeroporto.di

import com.example.trabalhoaeroporto.api.repositorio.VooRepository
import com.example.trabalhoaeroporto.api.servico.AviationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Módulo Hilt que fornece o Repository
 * ViewModelComponent - instância criada para cada ViewModel
 */
@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideVooRepository(aviationApiService: AviationApiService): VooRepository =
        VooRepository(aviationApiService)
}