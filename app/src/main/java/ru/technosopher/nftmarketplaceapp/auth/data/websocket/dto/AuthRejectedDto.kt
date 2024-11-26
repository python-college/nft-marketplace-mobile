package ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRejectedDto(
    @SerialName("type")
    override val type: String,
    @SerialName("message")
    val message: String
): ServerMessageDto()
