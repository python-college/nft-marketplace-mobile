package ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthSuccessDto(
    @SerialName("type")
    override val type: String,
    @SerialName("payload")
    val payload: AuthSuccessPayloadDto
): ServerMessageDto()

@Serializable
data class AuthSuccessPayloadDto(
    @SerialName("address")
    val address: String,
    @SerialName("session_id")
    val sessionId: String
)