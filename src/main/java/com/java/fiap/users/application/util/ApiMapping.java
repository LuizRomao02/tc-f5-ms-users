package com.java.fiap.users.application.util;

public class ApiMapping {

  public static final String API_V1 = "api/v1";

  public interface DoctorApi {
    String MAPPING = API_V1 + "/doctor";
  }

  public interface PatientApi {
    String MAPPING = API_V1 + "/patient";
  }

  public interface SpecialtyApi {
    String MAPPING = API_V1 + "/specialty";
  }

  public interface Actions {
    String ADD = "/add";
    String LIST = "/list";
    String DETAIL = "/detail/{id}";
    String EDIT = "/edit/{id}";
    String DELETE = "/delete/{id}";
  }
}
