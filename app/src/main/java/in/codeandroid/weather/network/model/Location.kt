package `in`.codeandroid.weather.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Location(
    @PrimaryKey val woeid: Int,
    val latt_long: String,
    val location_type: String,
    val title: String
)