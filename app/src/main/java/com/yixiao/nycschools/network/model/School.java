package com.yixiao.nycschools.network.model;

import com.google.gson.annotations.SerializedName;

public class School {
    @SerializedName("dbn")
    public String dbn;

    @SerializedName("school_name")
    public String schoolName;

    @SerializedName("overview_paragraph")
    public String overviewParagraph;

    @SerializedName("location")
    public String location;

    @SerializedName("phone_number")
    public String phoneNumber;

    @SerializedName("school_email")
    public String schoolEmail;
}
