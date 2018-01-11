package app.dz.prizes.retrofit2

import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("limit")
    var limit: Int = 0

    @SerializedName("offset")
    var offset: Int = 0

    @SerializedName("totalCount")
    var totalCount: Int = 0

    @SerializedName("next")
    var next: String? = null

    @SerializedName("previous")
    var previous: String? = null
}
