package com.kiko.knfc.ui.viewmodels

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.XmlResourceParser
import android.nfc.NfcAdapter
import android.nfc.cardemulation.CardEmulation
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kiko.knfc.ui.states.NFCEmulatorUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.xmlpull.v1.XmlPullParser
import javax.inject.Inject

@HiltViewModel
class NfcEmulateViewModel @Inject constructor(@ApplicationContext val context: Context) :
    ViewModel() {

}