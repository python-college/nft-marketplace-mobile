package ru.technosopher.nftmarketplaceapp.auth.data.util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.ServerMessageDto

fun parseServerMessage(json: String): ServerMessageDto {
    val jsonObject = Json.parseToJsonElement(json).jsonObject
    val type = jsonObject["type"]?.jsonPrimitive?.content

    return when (type) {
        "auth_link" -> Json.decodeFromString<AuthLinkDto>(json)
        "auth_success" -> Json.decodeFromString<AuthSuccessDto>(json)
        else -> throw IllegalArgumentException("Unknown message type: $type")
    }
}