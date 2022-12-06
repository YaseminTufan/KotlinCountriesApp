package com.example.kotlincountriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.kotlincountriesapp.R
import com.example.kotlincountriesapp.databinding.FragmentCountryBinding
import com.example.kotlincountriesapp.util.downloadFromUrl
import com.example.kotlincountriesapp.util.placeHolderProgressBar
import com.example.kotlincountriesapp.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {
    private lateinit var viewModel: CountryViewModel
    private var countryUuid = 0
    private lateinit var dataBinding : FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        observeLiveData()
    }
    private fun initViewModel() {

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)
    }
    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country->
            country?.let {
                dataBinding.selectedCountry = country
               /* countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryRegion.text = country.countryRegion
                countryLanguage.text = country.countryLanguage
                context?.let {
                    country_image.downloadFromUrl(country.imageUrl, placeHolderProgressBar(it))
                }

                */
            }
        })
    }
}