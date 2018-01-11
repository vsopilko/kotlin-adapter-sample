package app.dz.prizes.retrofit2

import com.google.gson.annotations.SerializedName
import java.util.*


abstract class AbstractResponse<T>(data: T) {

    @SerializedName("base_url")
    private val baseUrl: String? = null

    @SerializedName("errors")
    private val errors: ArrayList<ResponseError>? = null

    private val meta: Meta? = null

    @SerializedName("objects")
    var data: T
        protected set

    init {
        this.data = data
    }

    @SerializedName("success")
    val isSuccess: Boolean = false


    override fun toString(): String {
        return "AbstractResponse{" +
                "success=" + isSuccess +
                ", data=" + data +
                '}'
    }

}

