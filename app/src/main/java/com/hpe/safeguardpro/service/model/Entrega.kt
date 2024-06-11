package com.hpe.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entrega")
data class Entrega(
    @PrimaryKey
    var id: Int = 0,
    var tempo: String = "",
    var dataEntrega: String = "",
    var funcionarioId: Int,
    var epiId: Int
)
