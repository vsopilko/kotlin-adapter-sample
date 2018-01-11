package app.dz.prizes.retrofit2.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegParam implements Serializable {

    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("phone")
    String phone;
    @SerializedName("device_id")
    String deviceId;
}
