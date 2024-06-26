package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {

    private String userId;

    @JsonProperty("username")
    private String userName;

    private String password, token, expires;

    @JsonProperty("created_date")
    private String createdDate;

    private String isActive;
}