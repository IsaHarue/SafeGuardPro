package com.hpe.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "funcionario")
data class Funcionario(
    @PrimaryKey
    var id: Int = 0,
    var nome: String = "",
    var email: String = "",
    var cpf: String = "",
    var dataNasc: Int = 0,
)