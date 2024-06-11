package com.hpe.safeguardpro.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hpe.safeguardpro.service.model.Epi
import com.hpe.safeguardpro.service.model.Funcionario

//Colocar as entidades do projeto
@Database(entities = [Funcionario::class],[Epi::class], version = 1)
abstract class SafeGuardProDataBase : RoomDatabase() {

    // DAOs
    abstract fun FuncionarioDAO(): FuncionarioDAO
    abstract fun EpiDAO(): EpiDAO
    abstract fun EntregaDAO(): EntregaDAO


    companion object {
        @Volatile
        private lateinit var INSTANCE: SafeGuardProDataBase

        fun getDataBase(context: Context): SafeGuardProDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        SafeGuardProDataBase::class.java,
                        "safeguardpro_database" //Nome do banco de dados
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
