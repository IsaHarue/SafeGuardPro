package com.hpe.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Funcionario(
    var id: Int = 0,
    var nome: String = "",
    var email: String = "",
    var cpf: String = "",
    var dataNasc: String = "",
    var senha: String = "",
    var admin: Boolean = false,
)