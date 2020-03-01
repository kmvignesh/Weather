package `in`.codeandroid.weather.room

import `in`.codeandroid.weather.network.model.ConsolidatedWeather
import `in`.codeandroid.weather.network.model.Location
import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1,entities = [Location::class,ConsolidatedWeather::class])
abstract class WeatherDatabase  : RoomDatabase(){
    companion object {
        fun get(application: Application) : WeatherDatabase{
            return  Room.databaseBuilder(application,WeatherDatabase::class.java,"weather")
                .build()
        }
    }

    abstract fun getWeatherDao() : WeatherDao
}