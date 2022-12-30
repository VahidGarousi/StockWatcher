package dev.garousi.stock_watcher.feature.watchlist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.garousi.stock_watcher.feature.watchlist.data.LightStreamerConnection
import dev.garousi.stock_watcher.feature.watchlist.data.LightStreamerService
import dev.garousi.stock_watcher.feature.watchlist.data.StockListDto
import dev.garousi.stock_watcher.feature.watchlist.data.StockListLightStreamerService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WatchlistListModule {
//    @Provides
//    fun provideGetStockListUseCase(
//        repository: StockRepository
//    ): GetStockListUseCase {
//        return GetStockListUseCase(repository)
//    }
//
//    @Provides
//    fun provideStockListUseCases(
//        getStockListUseCase: GetStockListUseCase
//    ): StockListUseCases {
//        return StockListUseCases(getStockList = getStockListUseCase)
//    }

    @Provides
    @Singleton
    fun provideStockListLightStreamerService(
        connection: LightStreamerConnection
    ): LightStreamerService<StockListDto> {
        return StockListLightStreamerService(connection = connection)
    }
}