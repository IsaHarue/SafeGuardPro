package com.hpe.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entrega")
data class Entrega(
    @PrimaryKey
    var id: Int = 0,
    var tempo: String = "",
    var dataFabricacao: Int = 0,
    var funcionario: Funcionario,
    var epi: Epi
)
