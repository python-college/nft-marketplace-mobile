package ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto

data class AuthErrorDto(
    override val type: String = "error"
): ServerMessageDto()
