package dev.garousi.stockwatcher.feature.watchlist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.garousi.stockwatcher.feature.watchlist.domain.repository.StockRepository
import dev.garousi.stockwatcher.feature.watchlist.domain.repository.StockRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StockRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsStockRepository(impl: StockRepositoryImpl): StockRepository
}
