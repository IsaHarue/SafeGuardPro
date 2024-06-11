package com.hpe.safeguardpro.service.repository.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hpe.safeguardpro.service.model.Funcionario

interface FuncionarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(funcionario: Funcionario)

    @Update
    suspend fun update(funcionario: Funcionario): Int

    @Query("DELETE FROM funcionario WHERE id = :id")
    suspend fun delete(id: Int): Int

    @Query("SELECT * FROM funcionario WHERE id = :id")
    suspend fun getFuncionario(id: Int): Funcionario

    @Query("SELECT * FROM funcionario")
    suspend fun getAll(): List<Funcionario>
}
