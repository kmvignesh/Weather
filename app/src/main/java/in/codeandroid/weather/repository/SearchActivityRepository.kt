package `in`.codeandroid.weather.repository

import `in`.codeandroid.weather.network.BASE_URL
import `in`.codeandroid.weather.network.WeatherNetwork
import `in`.codeandroid.weather.network.model.Location
import `in`.codeandroid.weather.network.model.WeatherResponse
import `in`.codeandroid.weather.room.WeatherDatabase
import android.app.Application
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val locationList = MutableLiveData<List<Location>>()


    fun changeState() {
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }


    fun searchLocation(searchString: String) {
        showProgress.value = true
        // Networkcall

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()


        val service = retrofit.create(WeatherNetwork::class.java)

        service.getLocation(searchString).enqueue(object : Callback<List<Location>> {
            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error wile accessing the API", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                Log.d("SearchRepository", "Response : ${Gson().toJson(response.body())}")
                locationList.value = response.body()
                if (response.body() != null)
                    InsertLocationData(response.body()!!, application).execute()
                showProgress.value = false
            }

        })
    }


    class InsertLocationData(val list: List<Location>, val application: Application) :
        AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            WeatherDatabase.get(application).getWeatherDao().insertLocation(list)
            WeatherDatabase.get(application).getWeatherDao().getLocation("%ban%").forEach {
                Log.d("SearchRepository", "location : ${it.title}")
            }
            return null
        }

    }
}