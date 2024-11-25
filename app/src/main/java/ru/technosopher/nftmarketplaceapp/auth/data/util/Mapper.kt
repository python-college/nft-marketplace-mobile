package ru.technosopher.nftmarketplaceapp.auth.data.util

import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthLinkPayloadDto
import ru.technosopher.nftmarketplaceapp.auth.data.websocket.dto.AuthSuccessPayloadDto
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthLinkEntity
import ru.technosopher.nftmarketplaceapp.auth.domain.entities.AuthSuccessEntity

fun AuthLinkPayloadDto.toEntity() = AuthLinkEntity(
    url = authLink
)

fun AuthSuccessPayloadDto.toEntity() = AuthSuccessEntity(
    address = address,
    sessionId = sessionId
)