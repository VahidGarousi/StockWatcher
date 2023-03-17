@file:Suppress("UnnecessaryAbstractClass")

package dev.garousi.stockwatcher.feature.watchlist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import garousi.dev.lightstreamer.connection.DefaultLightStreamerConnection
import garousi.dev.lightstreamer.connection.LightStreamerConnection
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LightStreamerModule {
    @Binds
    @Singleton
    abstract fun bindsLightStreamerService(impl: DefaultLightStreamerConnection): LightStreamerConnection
}
