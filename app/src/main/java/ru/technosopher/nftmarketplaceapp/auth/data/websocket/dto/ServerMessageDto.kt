package ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto

import com.google.gson.annotations.SerializedName

sealed class ServerMessageDto {
    abstract val type: String
}