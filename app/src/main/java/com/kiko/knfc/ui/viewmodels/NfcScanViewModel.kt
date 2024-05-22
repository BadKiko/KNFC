package com.kiko.knfc.ui.viewmodels

import android.content.Context
import android.nfc.NfcAdapter
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiko.knfc.ui.states.NFCReaderUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hi.baka3k.nfcemulator.command.DigitalKeyPingPong
import hi.baka3k.nfctool.nfcreader.NfcReaderCallback
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NfcScanViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {
    private var nfcAdapter: NfcAdapter? = null

    private var _uiState: MutableState<NFCReaderUIState> = mutableStateOf(NFCReaderUIState.Idle)
    var uiState: State<NFCReaderUIState> = _uiState

    init {
        val nfcReaderCallback = initCardReader()
        observeCardReaderFlow(nfcReaderCallback)
        initNfc(context)
    }

    private fun initCardReader(): NfcReaderCallback {
        return NfcReaderCallback(pingPong = DigitalKeyPingPong())
    }

    private fun observeCardReaderFlow(nfcReaderCallback: NfcReaderCallback) {
        viewModelScope.launch {
            nfcReaderCallback.nfcData.collect { nfcData ->
                if (nfcData.isEmpty()) {
                    setNfcState(NFCReaderUIState.ReceivedEmpty)
                } else {
                    setNfcState(NFCReaderUIState.ReceivedData(data = nfcData))
                }
            }
        }
    }

    private fun initNfc(context: Context) {
        nfcAdapter = NfcAdapter.getDefaultAdapter(context)
    }

    fun setNfcState(newState: NFCReaderUIState) {
        _uiState.value = newState
    }
}