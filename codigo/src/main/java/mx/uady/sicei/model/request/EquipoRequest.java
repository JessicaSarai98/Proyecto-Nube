package mx.uady.sicei.model.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EquipoRequest {
  
  @NotEmpty
  @Size(min = 1, max = 255)
  private String modelo;

  public EquipoRequest() {}

  public String getModelo() {
    return this.modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public EquipoRequest modelo(String modelo) {
    this.modelo = modelo;
    return this;
  }

  @Override
  public String toString() {
    return "{" +
      " modelo='" + getModelo() + "'" +
    "}";
  }
    
}
