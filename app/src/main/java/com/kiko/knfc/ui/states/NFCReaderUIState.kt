package com.kiko.knfc.ui.states

sealed interface NFCReaderUIState {
    data object Idle : NFCReaderUIState
    data class ReceivedData(val data: String) : NFCReaderUIState
    data object ReceivedEmpty : NFCReaderUIState
}