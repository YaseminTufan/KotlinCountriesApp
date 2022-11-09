package com.example.kotlincountriesapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlincountriesapp.R

// extension
/*
fun String.myExtension(myParameter : String) {

    println(myParameter)

 */
     // glide tek tek tanımlamadan bu kısıma hepsi için bir fonksiyon oluşturucaz ve bu extensionu vericez imagelara

fun ImageView.downloadFromUrl(url : String?,progressDrawable: CircularProgressDrawable) {
    //görseller biraz geç geleceğinden placeholder(progress bar) koyduk.
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}
fun placeHolderProgressBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}