package com.kiko.knfc.ui.screens

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kiko.knfc.R
import com.kiko.knfc.ui.screens.destinations.NfcEmulateScreenDestination
import com.kiko.knfc.ui.viewmodels.NfcCardsViewModel
import com.kiko.knfc.utils.isColorDark
import com.kiko.knfc.utils.randomNeutral
import com.maxkeppeler.sheets.input.InputDialog
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.input.models.InputTextField
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnspecifiedRegisterReceiverFlag")
@Composable
@RootNavGraph(start = true)
@Destination
fun NfcScanScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    nfcCardsViewModel: NfcCardsViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    DisposableEffect(context) {
        val filter = IntentFilter()
        filter.addAction("NFC_CARD_DISCOVERED")

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                nfcCardsViewModel.setHex(intent?.extras?.getString("card") ?: "")
                nfcCardsViewModel.showCardDialog()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            context.registerReceiver(receiver, filter)
        }

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    val inputOptions = listOf(
        InputTextField(
            header = InputHeader(
                title = "Введите имя карты"
            )
        )
    )

    InputDialog(
        state = nfcCardsViewModel.dialogState,
        selection = InputSelection(
            input = inputOptions,
            onPositiveClick = { result ->
                nfcCardsViewModel.addCard(result.getString("0") ?: "")
            },
        )
    )

    if (nfcCardsViewModel.cardsList.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(nfcCardsViewModel.cardsList) {
                val color = Color.randomNeutral()

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    onClick = { navigator.navigate(NfcEmulateScreenDestination) },
                    colors = CardDefaults.cardColors(
                        containerColor = color,
                        contentColor = Color.Black.copy(0.7f)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = it.name)
                            Text(text = it.hex, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Прислоните карту для добавления",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}