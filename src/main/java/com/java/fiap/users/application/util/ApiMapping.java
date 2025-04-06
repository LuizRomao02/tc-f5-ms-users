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
    String ADD = API_V1 + "/add";
    String LIST = API_V1 + "/list";
    String DETAIL = API_V1 + "/detail/{id}";
    String EDIT = API_V1 + "/edit/{id}";
    String DELETE = API_V1 + "/delete/{id}";
  }
}
