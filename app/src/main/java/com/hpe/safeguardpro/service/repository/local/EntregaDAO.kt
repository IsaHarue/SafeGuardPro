package com.hpe.safeguardpro.service.repository.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hpe.safeguardpro.service.model.Entrega

interface EntregaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entrega: Entrega)

    @Update
    suspend fun update(entrega: Entrega): Int

    @Query("DELETE FROM entrega WHERE id = :id")
    suspend fun delete(id: Int): Int

    @Query("SELECT * FROM entrega WHERE id = :id")
    suspend fun getEntrega(id: Int): Entrega

    @Query("SELECT * FROM entrega")
    suspend fun getAll(): List<Entrega>
}