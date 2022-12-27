package dev.garousi.stock_watcher.feature.watchlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.garousi.stock_watcher.feature.watchlist.domain.repository.StockRepository
import dev.garousi.stock_watcher.feature.watchlist.domain.usecases.GetStockListUseCase
import dev.garousi.stock_watcher.feature.watchlist.domain.usecases.StockListUseCases

@Module
@InstallIn(SingletonComponent::class)
object WatchlistListModule {
    @Provides
    fun provideGetStockListUseCase(
        repository: StockRepository
    ) : GetStockListUseCase {
        return GetStockListUseCase(repository)
    }

    @Provides
    fun provideStockListUseCases(
        getStockListUseCase: GetStockListUseCase
    ) : StockListUseCases {
        return StockListUseCases(getStockList = getStockListUseCase)
    }
}