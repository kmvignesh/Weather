package `in`.codeandroid.weather.room

import `in`.codeandroid.weather.network.model.Location
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(list : List<Location>)

    @Query("SELECT * FROM Location where title like :search")
    fun getLocation(search:String) : List<Location>

}