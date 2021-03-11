package com.example.kmm_test.shared.di

import com.example.kmm_test.shared.*
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val testModule = DI.Module(
    name = "TestModule",
    init = {
        bind<ISettingsInteractor>() with singleton { SettingsInteractor(instance()) }
        bind<SettingsDataSource>() with singleton { SettingsDataSource() }
        bind<ZLog>() with singleton { ZLog() }
        bind<ILatestSongInteractor>() with singleton { LatestSongInteractor(instance()) }
        bind<ILatestSongRepository>() with singleton { LatestSongRepository(instance()) }
    }
)

val EngineSDK.latestSongInteractor: ILatestSongInteractor
    get() = EngineSDK.di.instance()

val EngineSDK.logger: ZLog
    get() = EngineSDK.di.instance()

val EngineSDK.settingsInteractor: ISettingsInteractor
    get() = EngineSDK.di.instance()

val EngineSDK.settingsDataSource: SettingsDataSource
    get() = EngineSDK.di.instance()