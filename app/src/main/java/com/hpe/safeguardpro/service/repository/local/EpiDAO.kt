package com.hpe.safeguardpro.service.repository.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hpe.safeguardpro.service.model.Epi

interface EpiDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(epi: Epi)

    @Update
    suspend fun update(epi: Epi): Int

    @Query("DELETE FROM epi WHERE id = :id")
    suspend fun delete(id: Int): Int

    @Query("SELECT * FROM epi WHERE id = :id")
    suspend fun getEpi(id: Int): Epi

    @Query("SELECT * FROM epi")
    suspend fun getAll(): List<Epi>
}