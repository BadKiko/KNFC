package com.kiko.knfc.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kiko.knfc.data.nfc.Card
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NfcCardsViewModel @Inject constructor() : ViewModel() {

    ///////////////////////////////////////////////////////////////////////////
    // Variables
    ///////////////////////////////////////////////////////////////////////////

    val dialogState = UseCaseState()

    private var _hexOfCard = mutableStateOf("")

    val cardsList = mutableStateListOf<Card>()

    ///////////////////////////////////////////////////////////////////////////
    // Setters
    ///////////////////////////////////////////////////////////////////////////

    fun showCardDialog() {
        dialogState.show()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Database
    ///////////////////////////////////////////////////////////////////////////

    fun addCard(name: String) {
        cardsList.add(Card(name, _hexOfCard.value))
    }

    fun setHex(value: String) {
        _hexOfCard.value = value
    }
}