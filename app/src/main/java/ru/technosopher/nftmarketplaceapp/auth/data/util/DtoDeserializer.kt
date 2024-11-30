package ru.technosopher.nftmarketplaceapp.auth.data.util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthErrorDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.ServerMessageDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthRejectedDto

fun parseServerMessage(json: String): ServerMessageDto {
    val jsonObject = Json.parseToJsonElement(json).jsonObject
    val type = jsonObject["type"]?.jsonPrimitive?.content
    return when (type) {
        "auth_link" -> Json.decodeFromString<AuthLinkDto>(json)
        "auth_success" -> Json.decodeFromString<AuthSuccessDto>(json)
        "auth_user_rejects" -> Json.decodeFromString<AuthRejectedDto>(json)
        else -> AuthErrorDto()
    }
    //        "auth_user_rejects" -> AuthErrorDto(error = Error.USER_REJECTED)
    //        null -> AuthErrorDto(error = Error.UNKNOWN_ERROR)
    //        else -> AuthErrorDto(error = Error.UNKNOWN_TYPE)
}