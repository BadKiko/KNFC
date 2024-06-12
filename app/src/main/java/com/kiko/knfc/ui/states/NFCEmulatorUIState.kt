package com.kiko.knfc.ui.states

sealed interface NFCEmulatorUIState {
    data object Idle : NFCEmulatorUIState
    data class EmulatorAvailable(val data: String) : NFCEmulatorUIState
    data class EmulatorNotSupported(val data: String) : NFCEmulatorUIState
}