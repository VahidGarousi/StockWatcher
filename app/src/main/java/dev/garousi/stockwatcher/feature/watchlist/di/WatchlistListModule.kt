package dev.garousi.stockwatcher.feature.watchlist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.garousi.stockwatcher.feature.watchlist.data.StockListDto
import dev.garousi.stockwatcher.feature.watchlist.data.StockListLightStreamerService
import garousi.dev.lightstreamer.service.LightStreamerService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WatchlistListModule {
    @Binds
    @Singleton
    fun bindsStockListLightStreamerService(
        impl: StockListLightStreamerService
    ): LightStreamerService<StockListDto>
}
