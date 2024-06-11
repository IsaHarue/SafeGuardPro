package com.hpe.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "epi")
data class Epi(
    @PrimaryKey
    var id: Int = 0,
    var nome: String = "",
    var dataF: String = "",
    var dataCA: String = "",
    var validade: String = "",
    var descricao: String = "",
)