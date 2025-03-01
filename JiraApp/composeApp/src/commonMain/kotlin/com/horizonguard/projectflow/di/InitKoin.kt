package com.horizonguard.projectflow.di

import com.horizonguard.projectflow.data.iteractor.ValidateUseCaseImpl
import com.horizonguard.projectflow.data.repository.LoginRepositoryImpl
import com.horizonguard.projectflow.domain.iteractor.ValidateUseCase
import com.horizonguard.projectflow.domain.remote.LoginApi
import com.horizonguard.projectflow.domain.remote.LoginApiImpl
import com.horizonguard.projectflow.domain.repository.LoginRepository
import com.horizonguard.projectflow.ui.app_comp.AppComponent
import com.horizonguard.projectflow.ui.app_comp.DefaultAppComponent
import com.horizonguard.projectflow.ui.app_comp.space_comp.DefaultSpaceComponent
import com.horizonguard.projectflow.ui.app_comp.space_comp.SpaceComponent
import com.horizonguard.projectflow.ui.login_comp.DefaultLoginComponent
import com.horizonguard.projectflow.ui.login_comp.LoginComponent
import com.horizonguard.projectflow.ui.login_comp.otp.DefaultOtpComponent
import com.horizonguard.projectflow.ui.login_comp.otp.OtpComponent
import com.horizonguard.projectflow.ui.login_comp.signin.DefaultSignInComponent
import com.horizonguard.projectflow.ui.login_comp.signin.SignInComponent
import com.horizonguard.projectflow.ui.login_comp.signup.DefaultSignUpComponent
import com.horizonguard.projectflow.ui.login_comp.signup.SignUpComponent
import com.horizonguard.projectflow.ui.root.DefaultRootComponent
import com.horizonguard.projectflow.ui.root.RootComponent
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinApplication
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

expect fun initKoin(appDeclaration: KoinAppDeclaration? = null): KoinApplication

val appModule = module {

    // dispatcher
    run {
        single<CoroutineContext>(named("ioContext")) { Dispatchers.IO }
        single<CoroutineContext>(named("mainContext")) { Dispatchers.Main.immediate }
    }

    // api
    run {
        single<LoginApi> { LoginApiImpl() }
    }

    // repository
    run {
        single<LoginRepository> {
            LoginRepositoryImpl(
                loginApi = get(),
                preferenceRepository = get(),
            )
        }
    }

    // use case
    run {
        single<ValidateUseCase> {
            ValidateUseCaseImpl(
                preferenceRepository = get(),
                loginApi = get()
            )
        }
    }

    // decompose
    run {
        // sign in
        single<SignInComponent.KoinFactory> {
            DefaultSignInComponent.KoinFactory(
                ioContext = get(qualifier = named("ioContext")),
                mainContext = get(qualifier = named("mainContext")),
                loginRepository = get(),
            )
        }

        // sign up
        single<SignUpComponent.KoinFactory> {
            DefaultSignUpComponent.KoinFactory(

            )
        }

        // otp
        single<OtpComponent.KoinFactory> {
            DefaultOtpComponent.KoinFactory(
                ioContext = get(qualifier = named("ioContext")),
                mainContext = get(qualifier = named("mainContext")),
                preferenceRepository = get(),
                loginRepository = get(),
            )
        }

        // login
        single<LoginComponent.KoinFactory> {
            DefaultLoginComponent.KoinFactory(
                signInComponentFactory = get(),
                signUpComponentFactory = get(),
                otpComponentFactory = get(),
            )
        }

        // space
        single<SpaceComponent.KoinFactory> {
            DefaultSpaceComponent.KoinFactory()
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
                validateUseCase = get(),
                ioContext = get(qualifier = named("ioContext")),
                mainContext = get(qualifier = named("mainContext")),
            )
        }
    }
}
