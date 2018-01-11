package app.dz.prizes.retrofit2.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class Subscription (

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("day_duration")
    var dayDuration: String? = null,

    @SerializedName("price")
    var price: String? = null

)