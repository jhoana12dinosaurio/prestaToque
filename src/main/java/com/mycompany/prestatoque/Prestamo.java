/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


import java.util.Date;

public class Prestamo {
    private int prestamoId;
    private int tipoPrestamoId;
    private float montoSolicitado;
    private float montoAprobado;
    private double interesAnual;
    private int plazoDias;
    private Date fechaSolicitud;
    private Date fechaAprobacion;
    private Date fechaDesembolso;
    private Date fechaVencimiento;
    private String descripcionDestino;
    private char estado;
    private String observaciones;

    public Prestamo(int prestamoId, int tipoPrestamoId, float montoSolicitado, float montoAprobado, double interesAnual,
                    int plazoDias, Date fechaSolicitud, Date fechaAprobacion, Date fechaDesembolso,
                    Date fechaVencimiento, String descripcionDestino, char estado, String observaciones) {
        this.prestamoId = prestamoId;
        this.tipoPrestamoId = tipoPrestamoId;
        this.montoSolicitado = montoSolicitado;
        this.montoAprobado = montoAprobado;
        this.interesAnual = interesAnual;
        this.plazoDias = plazoDias;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaDesembolso = fechaDesembolso;
        this.fechaVencimiento = fechaVencimiento;
        this.descripcionDestino = descripcionDestino;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // Getters y setters omitidos por brevedad

    @Override
    public String toString() {
        return "Prestamo{" +
                "prestamoId=" + prestamoId +
                ", tipoPrestamoId=" + tipoPrestamoId +
                ", montoSolicitado=" + montoSolicitado +
                ", montoAprobado=" + montoAprobado +
                ", interesAnual=" + interesAnual +
                ", plazoDias=" + plazoDias +
                ", fechaSolicitud=" + fechaSolicitud +
                ", fechaAprobacion=" + fechaAprobacion +
                ", fechaDesembolso=" + fechaDesembolso +
                ", fechaVencimiento=" + fechaVencimiento +
                ", descripcionDestino='" + descripcionDestino + '\'' +
                ", estado=" + estado +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }
}
