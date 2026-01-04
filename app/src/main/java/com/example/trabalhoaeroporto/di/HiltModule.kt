package com.example.trabalhoaeroporto.di

import com.example.trabalhoaeroporto.api.repositorio.VooRepository
import com.example.trabalhoaeroporto.api.servico.AviationApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class HiltModule {

    @Provides
    fun provideVooRepository(aviationApiService: AviationApiService): VooRepository =
        VooRepository(aviationApiService)
}