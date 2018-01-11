package app.dz.prizes.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TokenParam implements Serializable {
    @SerializedName("phone")
    String phone;
    @SerializedName("check_code")
    String checkCode;
}
