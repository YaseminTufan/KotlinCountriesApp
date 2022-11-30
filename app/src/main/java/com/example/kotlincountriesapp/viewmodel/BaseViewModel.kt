package com.example.kotlincountriesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

 abstract class BaseViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    // Arka planda job u yap ve mainthread e dön.

     override fun onCleared() {
         super.onCleared()
         job.cancel()
         //app kapanırsa işlem biterse iptal edilecek.
     }
}