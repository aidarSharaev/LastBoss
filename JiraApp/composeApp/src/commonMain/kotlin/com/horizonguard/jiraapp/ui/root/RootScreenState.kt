package com.horizonguard.jiraapp.ui.root

internal interface RootScreenState {

    object Loading : RootScreenState

    object Error : RootScreenState

    object Success : RootScreenState
}