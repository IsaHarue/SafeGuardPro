package com.hpe.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "epi")
data class Epi(
    @PrimaryKey
    var id: Int = 0,
    var nome: String = "",
    var dataFabricacao: Int = 0,
    var validade: String = "",
    var descricao: String = ""
)
