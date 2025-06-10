package com.mycompany.prestoque;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.time.Period;

public class Cliente extends Persona {
    private Long clienteId;
    private LocalDate fechaAfiliacion;
    private String tipoCliente;
    private String estado;
    private int puntajeCrediticio;
    private String observaciones;
    
    public Cliente(Long clienteId, LocalDate fechaAfiliacion, String tipoCliente, String estado, int puntajeCrediticio, String observaciones, Long personaId, String tipoDocumento, String numeroDocumento, String nombres, String apellidos, LocalDate fechaNacimiento, String genero, String direccion, String distrito, String provincia, String departamento, String telefono, String email, String ocupacion, String tipoNegocio, LocalDate fechaRegistro, boolean activo) {
        super(personaId, tipoDocumento, numeroDocumento, nombres, apellidos, fechaNacimiento, genero,
              direccion, distrito, provincia, departamento, telefono, email, ocupacion,
              tipoNegocio, fechaRegistro, activo);
        this.clienteId = clienteId;
        this.fechaAfiliacion = fechaAfiliacion;
        this.tipoCliente = tipoCliente;
        this.estado = estado;
        this.puntajeCrediticio = puntajeCrediticio;
        this.observaciones = observaciones;
    }
    
public Cliente() {
    super(); // Llama al constructor vacío de Persona (asegúrate de que exista)
}

     
    public int getAniosAfiliado() {
        return fechaAfiliacion == null ? 0 :
               Period.between(fechaAfiliacion, LocalDate.now()).getYears();
    }

    @Override
    public boolean isValid() {
        return  StringUtils.isNotBlank(tipoCliente) &&
                StringUtils.isNotBlank(estado)&&
                fechaAfiliacion != null ;
                
    }

    // Getters y Setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public LocalDate getFechaAfiliacion() { return fechaAfiliacion; }
    public void setFechaAfiliacion(LocalDate fechaAfiliacion) { this.fechaAfiliacion = fechaAfiliacion != null ? fechaAfiliacion : LocalDate.now();}

    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getPuntajeCrediticio() { return puntajeCrediticio; }
    public void setPuntajeCrediticio(int puntajeCrediticio) { this.puntajeCrediticio = puntajeCrediticio; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        if (!super.equals(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equal(clienteId, cliente.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clienteId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("clienteId", clienteId)
                .append("estado", estado)
                .append("tipoCliente", tipoCliente)
                .append("puntajeCrediticio", puntajeCrediticio)
                .append("aniosAfiliado", getAniosAfiliado())
                .toString();
    }
}
