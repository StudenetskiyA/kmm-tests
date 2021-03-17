package com.example.kmm_test.shared.di

import com.example.kmm_test.shared.*
import com.example.kmm_test.shared.db.Database
import com.example.kmm_test.shared.db.DatabaseDriverFactory
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import kotlin.native.concurrent.ThreadLocal

internal val testModule = DI.Module(
    name = "TestModule",
    init = {
        bind<ISettingsInteractor>() with singleton { SettingsInteractor(instance()) }
        bind<SettingsDataSource>() with singleton { SettingsDataSource() }
        bind<PlayerDataSource>() with singleton { PlayerDataSource() }
        bind<IPLayerInteractor>() with singleton { PlayerInteractor(instance()) }
        bind<ZLog>() with singleton { ZLog() }
        bind<DateTimeDataSource>() with singleton { DateTimeDataSource() }
        bind<DatabaseDriverFactory>() with singleton { DatabaseDriverFactory() }
        bind<Database>() with singleton { Database(instance()) }
        bind<RandomUUIDDataSource>() with singleton { RandomUUIDDataSource() }
        bind<ILatestSongRepository>() with singleton { LatestSongRepository() }
        bind<ILatestSongInteractor>() with singleton { LatestSongInteractor(instance()) }

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

val EngineSDK.playerInteractor: IPLayerInteractor
    get() = EngineSDK.di.instance()

val EngineSDK.playerDataSource: PlayerDataSource
    get() = EngineSDK.di.instance()

val EngineSDK.databaseDriverFactory: DatabaseDriverFactory
    get() = EngineSDK.di.instance()

val EngineSDK.database: Database
    get() = EngineSDK.di.instance()