package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {
    String userId;

    @JsonProperty("username")
    String userName;

    String password, token, expires;

    @JsonProperty("created_date")
    String createdDate;

    String isActive;
}