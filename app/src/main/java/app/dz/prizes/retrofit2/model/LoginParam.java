package app.dz.prizes.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginParam implements Serializable {
    @SerializedName("phone")
    String phone;
    @SerializedName("device_id")
    String deviceId;
}
