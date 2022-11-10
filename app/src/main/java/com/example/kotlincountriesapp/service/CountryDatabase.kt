package com.example.kotlincountriesapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlincountriesapp.model.Country
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao() : CountryDAO

    // Singleton (tek bir obje oluşturulan sınıf)
    // companion -> her yerden ulaşılabilen sınıf
    // volatile -> farklı threadlerde de kullanılsın,çagırılabilsin die volatile kullanılıyoruz.
    //intance var mı yok mu kontrol edilir -> invoke
    //synchronized -> aynı anda 2 thread calısmasın farklı zamanlarda çalıssın.

    //singleton
    companion object {
       @Volatile private var instance : CountryDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: kotlin.synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,"countryDatabase").build()

    }
}