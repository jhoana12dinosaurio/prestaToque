package com.mycompany.prestoque;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrestamoValidador {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoValidador.class);
    private static final List<String> ESTADOS_VALIDOS = Arrays.asList("SOLICITADO", "APROBADO", "DESEMBOLSADO", "VENCIDO", "CANCELADO", "ANULADO");

    public PrestamoValidador() {
        logger.debug("PrestamoValidador inicializado");
    }

    public List<String> validate(Prestamo prestamo) {
        List<String> errors = new ArrayList<>();

        if (prestamo == null) {
            errors.add("El préstamo no puede ser nulo");
            logger.warn("Préstamo nulo");
            return errors;
        }

        if (prestamo.getTipoPrestamoId() == null) {
            errors.add("El tipo de préstamo es obligatorio");
        }

        if (prestamo.getMonedaId() == null) {
            errors.add("La moneda es obligatoria");
        }

        if (prestamo.getMontoSolicitado() == null || prestamo.getMontoSolicitado().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("El monto solicitado debe ser mayor que cero");
        }

        if (prestamo.getMontoAprobado() == null || prestamo.getMontoAprobado().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("El monto aprobado debe ser mayor que cero");
        }

        if (prestamo.getMontoSolicitado() != null &&
            prestamo.getMontoAprobado() != null &&
            prestamo.getMontoAprobado().compareTo(prestamo.getMontoSolicitado()) < 0) {
            errors.add("El monto aprobado no puede ser menor que el solicitado");
        }

        if (prestamo.getInteresAnual() == null || prestamo.getInteresAnual().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("El interés anual debe ser mayor que cero");
        }

        if (prestamo.getPlazoDias() == null || prestamo.getPlazoDias() <= 0) {
            errors.add("El plazo en días debe ser mayor que cero");
        }

        if (prestamo.getFechaSolicitud() == null) {
            errors.add("La fecha de solicitud es obligatoria");
        }

        if (prestamo.getFechaAprobacion() != null &&
            prestamo.getFechaSolicitud() != null &&
            prestamo.getFechaAprobacion().isBefore(prestamo.getFechaSolicitud())) {
            errors.add("La fecha de aprobación no puede ser anterior a la fecha de solicitud");
        }

        if (prestamo.getFechaDesembolso() != null &&
            prestamo.getFechaAprobacion() != null &&
            prestamo.getFechaDesembolso().isBefore(prestamo.getFechaAprobacion())) {
            errors.add("La fecha de desembolso no puede ser anterior a la fecha de aprobación");
        }

        if (prestamo.getFechaVencimiento() == null ||
            prestamo.getFechaVencimiento().isBefore(LocalDate.now())) {
            errors.add("La fecha de vencimiento no puede ser pasada");
        }

        if (prestamo.getPlazoDias() != null &&
            prestamo.getFechaDesembolso() != null &&
            prestamo.getFechaVencimiento() != null) {
            LocalDate fechaEsperada = prestamo.getFechaDesembolso().toLocalDate().plusDays(prestamo.getPlazoDias());
            if (!fechaEsperada.equals(prestamo.getFechaVencimiento())) {
                errors.add("La fecha de vencimiento no coincide con el plazo desde la fecha de desembolso");
            }
        }

        if (StringUtils.isBlank(prestamo.getEstado())) {
            errors.add("El estado del préstamo es obligatorio");
        } else if (!ESTADOS_VALIDOS.contains(prestamo.getEstado().toUpperCase())) {
            errors.add("El estado del préstamo no es válido");
        }

        return errors;
    }

    public boolean isValid(Prestamo prestamo) {
        return validate(prestamo).isEmpty();
    }

    public void normalize(Prestamo prestamo) {
        if (prestamo == null) return;

        logger.debug("Normalizando campos de préstamo");

        if (StringUtils.isNotBlank(prestamo.getDescripcionDestino())) {
            prestamo.setDescripcionDestino(
                StringUtils.capitalize(prestamo.getDescripcionDestino().trim().toLowerCase())
            );
        }

        if (StringUtils.isNotBlank(prestamo.getEstado())) {
            prestamo.setEstado(prestamo.getEstado().trim().toUpperCase());
        }

        if (StringUtils.isNotBlank(prestamo.getObservaciones())) {
            prestamo.setObservaciones(prestamo.getObservaciones().trim());
        }
    }
}
