package com.kiko.knfc.data.nfc

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val name: String,
    val hex: String
)
