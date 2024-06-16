package com.hpe.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Entrega(
    var id: Int = 0,
    var tempo: String = "",
    var dataEntrega: String = "",
    var funcionarioId: Int = 0,
    var epiId: Int = 0
)
