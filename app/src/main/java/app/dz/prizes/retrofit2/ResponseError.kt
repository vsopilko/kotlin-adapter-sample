package app.dz.prizes.retrofit2

import com.google.gson.annotations.SerializedName


class ResponseError {

    @SerializedName("code")
    var code: Int = 0

    @SerializedName("description")
    var description: String? = null

    @SerializedName("text")
    var text: String? = null
}
