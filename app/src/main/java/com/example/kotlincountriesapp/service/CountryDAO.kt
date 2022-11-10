package com.example.kotlincountriesapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlincountriesapp.model.Country

@Dao
interface CountryDAO {
    // Data Access Object
    //coroutines işlemleri farklı threadda yapmamızı sağlıyacak.
    //suspend -> coroutine,pause & resume
    // suspend coroutines için kullanılıyor.
    //vararg -> multiple country objects(ne kadar veri alıcağımızı bilmediğimiz için)
    //List<Long> -> Primary Keys

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId") //country id si .. olanları çek demek istedik.
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}