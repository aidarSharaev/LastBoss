package com.horizonguard.projectflow.ui.root

internal sealed interface RootScreenState {

    data object Loading : RootScreenState

    class Error(exc: Throwable) : RootScreenState

    data object Success : RootScreenState
}