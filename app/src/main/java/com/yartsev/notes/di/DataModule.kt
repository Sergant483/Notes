package com.yartsev.notes.di

import android.content.Context
import com.yartsev.notes.data.database.AppDataBase
import com.yartsev.notes.data.repository.NotesRepository
import com.yartsev.notes.data.repository.NotesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    fun bindNotesRepositoryImpl(impl: NotesRepositoryImpl): NotesRepository

    companion object {

        @Provides
        fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
            AppDataBase.newInstance(context)
    }
}