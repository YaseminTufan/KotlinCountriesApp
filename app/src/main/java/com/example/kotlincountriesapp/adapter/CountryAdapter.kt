package com.example.kotlincountriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincountriesapp.R
import com.example.kotlincountriesapp.model.Country
import com.example.kotlincountriesapp.util.downloadFromUrl
import com.example.kotlincountriesapp.util.placeHolderProgressBar
import com.example.kotlincountriesapp.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList : ArrayList<Country>):RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(var view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.downloadFromUrl(countryList[position].imageUrl,
            placeHolderProgressBar(holder.view.context))

    }

    override fun getItemCount(): Int {
        return countryList.size
    }
    //feed fragmenta swiperefreshlayout koyduk kullanıcı onu çekip güncellerse bunu adaptere bildirmek için bu fonksiyonu oluşturuyoruz.
    //böylece önce temizleyip sonra güncel listeyi verebilicez.
    fun updateCountryList (newCountryList:List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()

    }
}