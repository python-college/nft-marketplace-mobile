package ru.technosopher.nftmarketplaceapp.auth.data.websocket

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import ru.technosopher.nftmarketplaceapp.auth.data.util.parseServerMessage
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthErrorDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkPayloadDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthRejectedDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessPayloadDto
import java.net.ConnectException
import javax.inject.Inject

class AuthWebSocketClient @Inject constructor(
    private val httpClient: HttpClient
) {
    public val TAG: String = "AUTH_WEBSOCKET_CLIENT"
    private val authLinkFlow = MutableSharedFlow<AuthLinkPayloadDto>()
    private val authSuccessFlow = MutableSharedFlow<AuthSuccessPayloadDto>()
    private val authRejectedFlow = MutableSharedFlow<AuthRejectedDto>()
    private val authErrorFlow = MutableSharedFlow<AuthErrorDto>()

    fun observeAuthLink(): SharedFlow<AuthLinkPayloadDto> = authLinkFlow
    fun observeAuthSuccess(): SharedFlow<AuthSuccessPayloadDto> = authSuccessFlow
    fun observeAuthReject(): SharedFlow<AuthRejectedDto> = authRejectedFlow
    fun observeAuthError(): SharedFlow<AuthErrorDto> = authErrorFlow

    suspend fun connect() {
        try {
            httpClient.webSocket("/main/api/v1/ws/auth") {
                Log.d(TAG, "Websocket connected!")
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        handleMessage(frame.readText())
                    }
                }
            }
        } catch (e: ConnectException) {
            handleMessage("""{"type": "error"}""")
        }
        Log.d(TAG, "Websocket closed!")
    }

    private suspend fun handleMessage(json: String) {
        Log.d(TAG, json)

        when (val message = parseServerMessage(json)) {
            is AuthLinkDto -> authLinkFlow.emit(message.payload)
            is AuthSuccessDto -> authSuccessFlow.emit(message.payload)
            is AuthRejectedDto -> authRejectedFlow.emit(message)
            is AuthErrorDto -> authErrorFlow.emit(message)
        }
    }
}