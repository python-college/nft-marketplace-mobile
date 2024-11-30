package ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthLinkDto(
    @SerialName("type")
    override val type: String,
    @SerialName("payload")
    val payload: AuthLinkPayloadDto
) : ServerMessageDto()

@Serializable
data class AuthLinkPayloadDto(
    @SerialName("auth_link")
    val authLink: String
)