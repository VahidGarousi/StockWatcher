package dev.garousi.stock_watcher.feature.watchlist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.garousi.stock_watcher.feature.watchlist.data.LightStreamerConnection
import dev.garousi.stock_watcher.feature.watchlist.data.LightStreamerConnectionImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LightStreamerModule {
    @Binds
    @Singleton
    abstract fun bindsLightStreamerService(impl: LightStreamerConnectionImpl): LightStreamerConnection
}