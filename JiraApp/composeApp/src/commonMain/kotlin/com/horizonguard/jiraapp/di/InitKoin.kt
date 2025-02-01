package com.horizonguard.jiraapp.di

import com.horizonguard.jiraapp.ui.app_comp.AppComponent
import com.horizonguard.jiraapp.ui.app_comp.DefaultAppComponent
import com.horizonguard.jiraapp.ui.login_comp.DefaultLoginComponent
import com.horizonguard.jiraapp.ui.login_comp.LoginComponent
import com.horizonguard.jiraapp.ui.login_comp.signin.DefaultSignInComponent
import com.horizonguard.jiraapp.ui.login_comp.signin.SignInComponent
import com.horizonguard.jiraapp.ui.root.DefaultRootComponent
import com.horizonguard.jiraapp.ui.root.RootComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun initKoin(appDeclaration: KoinAppDeclaration? = null): KoinApplication

val appModule = module {

    // dispatcher
    single<CoroutineDispatcher> { Dispatchers.IO }

    // sign in
    /*single<SignInComponent.KoinFactory> {
        DefaultSignInComponent.KoinFactory(
            preferenceRepository = get(),
            signUpComponentFactory = get(),
            otpComponentFactory = get(),
            dispatcher = get()
        )
    }*/

    // login
    single<LoginComponent.KoinFactory> {
        DefaultLoginComponent.KoinFactory(
            signInComponentFactory = get(),
            signUpComponentFactory = get(),
            otpComponentFactory = get(),
            dispatcher = get()
        )
    }

    // app
    single<AppComponent.KoinFactory> {
        DefaultAppComponent.KoinFactory(
            spaceComponentFactory = get(),
        )
    }

    // root
    single<RootComponent.KoinFactory> {
        DefaultRootComponent.KoinFactory(
            appComponentFactory = get(),
            loginComponentFactory = get(),
            preferenceRepository = get(),
            dispatcher = get()
        )
    }
}
