package `in`.codeandroid.weather.viewModel

import `in`.codeandroid.weather.network.model.WeatherResponse
import `in`.codeandroid.weather.repository.DetailsActivityRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DetailsActivityRepository(application)
    val showProgress : LiveData<Boolean>
    val response : LiveData<WeatherResponse>

    init {
        showProgress = repository.showProgress
        response = repository.response
    }


    fun getWeather(woeId : Int){
        repository.getWeather(woeId)
    }
}