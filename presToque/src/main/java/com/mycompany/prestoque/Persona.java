package com.mycompany.prestoque;

/**
 *
 * @author brigh
 */
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.time.Period;
import java.time.LocalDateTime;

public class Persona {
    private Long personaId;
    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String genero;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String telefono;
    private String email;
    private String ocupacion;
    private String tipoNegocio;
    private LocalDate fechaRegistro;
    private boolean activo;

    public Persona() {
    }

    
    public Persona(Long personaId, String tipoDocumento, String numeroDocumento, String nombres, String apellidos, LocalDate fechaNacimiento, String genero, String direccion, String distrito, String provincia, String departamento, String telefono, String email, String ocupacion, String tipoNegocio, LocalDate fechaRegistro, boolean activo) {
        this.personaId = personaId;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.direccion = direccion;
        this.distrito = distrito;
        this.provincia = provincia;
        this.departamento = departamento;
        this.telefono = telefono;
        this.email = email;
        this.ocupacion = ocupacion;
        this.tipoNegocio = tipoNegocio;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    public String getFullName() {
        return StringUtils.join(
            StringUtils.defaultString(nombres),
            " ",
            StringUtils.defaultString(apellidos)
        ).trim();
    }

    public int getAge() {
        if (fechaNacimiento == null) {
            return 0;
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public boolean isValid() {
    return StringUtils.isNotBlank(nombres) &&
           StringUtils.isNotBlank(apellidos) &&
           StringUtils.isNotBlank(email) &&
           StringUtils.isNotBlank(numeroDocumento) &&
           StringUtils.isNotBlank(tipoDocumento) &&
           StringUtils.isNotBlank(genero) &&
           StringUtils.isNotBlank(direccion) &&
           StringUtils.isNotBlank(ocupacion) &&
           StringUtils.isNotBlank(tipoNegocio) &&
           fechaNacimiento != null;
}


    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTipoNegocio() {
        return tipoNegocio;
    }

    public void setTipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

 
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        return Objects.equal(personaId, persona.personaId) &&
               Objects.equal(numeroDocumento, persona.numeroDocumento) &&
               Objects.equal(email, persona.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(personaId, numeroDocumento, email);
    }

    @Override
public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
            .append("personaId", personaId)
            .append("fullName", getFullName())
            .append("email", email)
            .append("edad", getAge())
            .append("tipoDocumento", tipoDocumento)
            .append("genero", genero)
            .append("direccion", direccion)
            .append("ocupacion", ocupacion)
            .append("tipoNegocio", tipoNegocio)
            .toString();
}

}
