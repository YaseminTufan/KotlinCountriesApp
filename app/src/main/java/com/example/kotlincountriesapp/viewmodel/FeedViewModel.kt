package com.example.kotlincountriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlincountriesapp.model.Country
import com.example.kotlincountriesapp.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FeedViewModel(application: Application) : BaseViewModel(application) {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData () {
        getDataFromAPI()

    }
    private fun getDataFromAPI () {
        countryLoading.value = true

        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)

                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }
    // onsuccess altında verileri room database e kaydedip öyle göstericez.
    private fun showCountries(countryList : List<Country>) {
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
    //Aldığımız verileri SQL a kaydetmek istiyoruz.
    //coroutine kullanıcaz ve hangi threadde kullanıcaz vs.belirtmemiz lazım bunun için BaseViewModel oluşturuyoruz ve extend ediyoruz viewmodellarımıza.
    private fun storeInSQLite (list : List<Country>) {
        launch {

        }

    }

}