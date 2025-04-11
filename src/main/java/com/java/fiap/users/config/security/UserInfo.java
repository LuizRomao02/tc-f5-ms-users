package com.java.fiap.users.config.security;

import com.java.fiap.users.application.dto.UserInfoDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.security.oauth2.jwt.Jwt;

@Data
public class UserInfo {

  private String userName;
  private String userId;
  private String email;
  private String lastName;
  private String firstName;
  private String realmName;
  private String userType;

  public static UserInfo getUserInfo(Jwt jwt) {
    UserInfo keycloakUserInfo = new UserInfo();

    keycloakUserInfo.userName = jwt.getClaimAsString("preferred_username");
    keycloakUserInfo.email = jwt.getClaimAsString("email");
    keycloakUserInfo.lastName = jwt.getClaimAsString("family_name");
    keycloakUserInfo.firstName = jwt.getClaimAsString("given_name");
    keycloakUserInfo.realmName = jwt.getIssuer().toString();
    keycloakUserInfo.userType = jwt.getClaimAsString("userType");
    keycloakUserInfo.userId = jwt.getClaimAsString("userId");

    return keycloakUserInfo;
  }

  public UserInfoDTO getUserInfoDtoByJwt(Jwt jwt) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(getUserInfo(jwt), UserInfoDTO.class);
  }
}
