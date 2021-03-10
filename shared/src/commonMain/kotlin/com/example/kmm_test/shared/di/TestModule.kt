package com.example.kmm_test.shared.di

import com.example.kmm_test.shared.PageGenerator
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal val testModule = DI.Module(
    name = "TestModule",
    init = {
        bind<PageGenerator>() with singleton { PageGenerator() }
    }
)

val EngineSDK.pageGenerator: PageGenerator
    get() = EngineSDK.di.instance()