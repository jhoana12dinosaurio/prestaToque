package com.mycompany.prestoque;

/**
 *
 * @author brigh
 */
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prestamo {
    private Long prestamoId;
    private Long clienteId;
    private String tipoPrestamoId;
    private String monedaId;
    private BigDecimal montoSolicitado;
    private BigDecimal montoAprobado;
    private BigDecimal interesAnual;
    private Short plazoDias;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaAprobacion;
    private LocalDateTime fechaDesembolso;
    private LocalDate fechaVencimiento;
    private byte destinoId;
    private String nombreDestino;
    private String descripcionDestino;
    private String estado;
    private String observaciones;
    private String nombreMonedas;
    private String nombresCliente;
    private String apellidosCliente;
    private String tipoDocumentoCliente;
    private String numeroDocumentoCliente;

    public Prestamo() {
    }

    public Prestamo(Long prestamoId, Long clienteId, String tipoPrestamoId, String monedaId, BigDecimal montoSolicitado, BigDecimal montoAprobado, BigDecimal interesAnual, Short plazoDias, LocalDateTime fechaSolicitud, LocalDateTime fechaAprobacion, LocalDateTime fechaDesembolso, LocalDate fechaVencimiento, byte destinoId, String nombreDestino, String descripcionDestino, String estado, String observaciones, String nombreMonedas, String nombresCliente, String apellidosCliente, String tipoDocumentoCliente, String numeroDocumentoCliente) {
        this.prestamoId = prestamoId;
        this.clienteId = clienteId;
        this.tipoPrestamoId = tipoPrestamoId;
        this.monedaId = monedaId;
        this.montoSolicitado = montoSolicitado;
        this.montoAprobado = montoAprobado;
        this.interesAnual = interesAnual;
        this.plazoDias = plazoDias;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaDesembolso = fechaDesembolso;
        this.fechaVencimiento = fechaVencimiento;
        this.destinoId = destinoId;
        this.nombreDestino = nombreDestino;
        this.descripcionDestino = descripcionDestino;
        this.estado = estado;
        this.observaciones = observaciones;
        this.nombreMonedas = nombreMonedas;
        this.nombresCliente = nombresCliente;
        this.apellidosCliente = apellidosCliente;
        this.tipoDocumentoCliente = tipoDocumentoCliente;
        this.numeroDocumentoCliente = numeroDocumentoCliente;
    }

    

    

   

    public boolean isValid() {
        return clienteId != null &&
               tipoPrestamoId != null &&
               monedaId != null &&
               montoSolicitado != null &&
               montoAprobado != null &&
               interesAnual != null &&
               plazoDias != null &&
               fechaSolicitud != null &&
               fechaVencimiento != null &&
               StringUtils.isNotBlank(estado);
    }

    // Getters y Setters
    public Long getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Long prestamoId) {
        this.prestamoId = prestamoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoPrestamoId() {
        return tipoPrestamoId;
    }

    public void setTipoPrestamoId(String tipoPrestamoId) {
        this.tipoPrestamoId = tipoPrestamoId;
    }

    public String getMonedaId() {
        return monedaId;
    }

    public void setMonedaId(String monedaId) {
        this.monedaId = monedaId;
    }

    public String getNombreMonedas() {
        return nombreMonedas;
    }

    public void setNombreMonedas(String nombreMonedas) {
        this.nombreMonedas = nombreMonedas;
    }

    
    
    public BigDecimal getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(BigDecimal montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public BigDecimal getMontoAprobado() {
        return montoAprobado;
    }

    public void setMontoAprobado(BigDecimal montoAprobado) {
        this.montoAprobado = montoAprobado;
    }

    public BigDecimal getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(BigDecimal interesAnual) {
        this.interesAnual = interesAnual;
    }

    public Short getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(Short plazoDias) {
        this.plazoDias = plazoDias;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDateTime getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDateTime fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public LocalDateTime getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(LocalDateTime fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

   public byte getDestinoId() { return destinoId; }
public void setDestinoId(byte destinoId) { this.destinoId = destinoId; }

public String getNombreDestino() { return nombreDestino; }
public void setNombreDestino(String nombreDestino) { this.nombreDestino = nombreDestino; }

public String getDescripcionDestino() { return descripcionDestino; }
public void setDescripcionDestino(String descripcionDestino) { this.descripcionDestino = descripcionDestino; }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombresCliente() {
        return nombresCliente;
    }

    public void setNombresCliente(String nombresCliente) {
        this.nombresCliente = nombresCliente;
    }

    public String getApellidosCliente() {
        return apellidosCliente;
    }

    public void setApellidosCliente(String apellidosCliente) {
        this.apellidosCliente = apellidosCliente;
    }

    public String getTipoDocumentoCliente() {
        return tipoDocumentoCliente;
    }

    public void setTipoDocumentoCliente(String tipoDocumentoCliente) {
        this.tipoDocumentoCliente = tipoDocumentoCliente;
    }

    public String getNumeroDocumentoCliente() {
        return numeroDocumentoCliente;
    }

    public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
        this.numeroDocumentoCliente = numeroDocumentoCliente;
    }
    
    
    // equals, hashCode, toString
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Prestamo prestamo = (Prestamo) obj;
        return Objects.equal(prestamoId, prestamo.prestamoId) &&
               Objects.equal(clienteId, prestamo.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prestamoId, clienteId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("prestamoId", prestamoId)
                .append("clienteId", clienteId)
                .append("montoSolicitado", montoSolicitado)
                .append("montoAprobado", montoAprobado)
                .append("interesAnual", interesAnual)
                .append("plazoDias", plazoDias)
                .append("estado", estado)
                .toString();
    }
}

