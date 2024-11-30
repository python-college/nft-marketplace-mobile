package ru.technosopher.nftmarketplaceapp.auth.data.util

import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthErrorDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkPayloadDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthRejectedDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessPayloadDto
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthErrorEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthLinkEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthRejectedEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthSuccessEntity

fun AuthLinkPayloadDto.toEntity() = AuthLinkEntity(
    url = authLink
)

fun AuthSuccessPayloadDto.toEntity() = AuthSuccessEntity(
    address = address,
    sessionId = sessionId
)

fun AuthRejectedDto.toEntity() = AuthRejectedEntity(
    type = type,
    message = message
)

fun AuthErrorDto.toEntity() = AuthErrorEntity(
    type = type
)