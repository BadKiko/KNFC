package com.kiko.knfc.ui.states

sealed interface NFCCardsUIState {
    data object Idle : NFCCardsUIState
    data class ReceivedData(val data: String) : NFCCardsUIState
    data object ReceivedEmpty : NFCCardsUIState
}