package ru.technosopher.nftmarketplaceapp.auth.data.websocket

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.technosopher.nftmarketplaceapp.auth.data.util.parseServerMessage
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkPayloadDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessPayloadDto
import javax.inject.Inject

class AuthWebSocketClient @Inject constructor(
    private val httpClient: HttpClient
) {
    public val TAG: String = "AUTH_WEBSOCKET_CLIENT"
    private val authLinkFlow = MutableSharedFlow<AuthLinkPayloadDto>()
    private val authSuccessFlow = MutableSharedFlow<AuthSuccessPayloadDto>()

    fun observeAuthLink(): SharedFlow<AuthLinkPayloadDto> = authLinkFlow
    fun observeAuthSuccess(): SharedFlow<AuthSuccessPayloadDto> = authSuccessFlow

    suspend fun connect() {
        httpClient.webSocket("/ws/auth") {
            Log.d(TAG, "Websocket connected!")
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    handleMessage(frame.readText())
                }
            }
        }
        httpClient.close()
        Log.d(TAG, "Websocket closed!")
    }

    private suspend fun handleMessage(json: String) {
        Log.d(TAG, json)
        when (val message = parseServerMessage(json)) {
            is AuthLinkDto -> authLinkFlow.emit(message.payload)
            is AuthSuccessDto -> authSuccessFlow.emit(message.payload)
        }
    }
}