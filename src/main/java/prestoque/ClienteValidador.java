
package com.mycompany.prestoque;

/**
 *
 * @author brigh
 */
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteValidador {

    private static final Logger logger = LoggerFactory.getLogger(ClienteValidador.class);
    private final EmailValidator emailValidator;

    public ClienteValidador() {
        this.emailValidator = EmailValidator.getInstance();
        logger.debug("ClienteValidator inicializado");
    }

    public List<String> validate(Cliente cliente) {
        List<String> errors = new ArrayList<>();

        if (cliente == null) {
            errors.add("El cliente no puede ser nulo");
            logger.warn("Cliente nulo");
            return errors;
        }

        // Validaciones básicas
        if (StringUtils.isBlank(cliente.getNombres())) {
            errors.add("El nombre es obligatorio");
        }

        if (StringUtils.isBlank(cliente.getApellidos())) {
            errors.add("El apellido es obligatorio");
        }

        if (StringUtils.isBlank(cliente.getNumeroDocumento())) {
            errors.add("El número de documento es obligatorio");
        }

        if (StringUtils.isBlank(cliente.getTipoDocumento())) {
            errors.add("El tipo de documento es obligatorio");
        }

        if (cliente.getFechaNacimiento() == null) {
            errors.add("La fecha de nacimiento es obligatoria");
        } else {
            int edad = LocalDate.now().getYear() - cliente.getFechaNacimiento().getYear();
            if (edad < 18) {
                errors.add("El cliente debe ser mayor de 18 años");
            }
        }

        if (StringUtils.isNotBlank(cliente.getEmail()) && !emailValidator.isValid(cliente.getEmail())) {
            errors.add("El correo electrónico no es válido");
        }

        if (StringUtils.isBlank(cliente.getOcupacion())) {
            errors.add("La ocupación es obligatoria");
        }
        
        if(StringUtils.isBlank(cliente.getTipoNegocio())){
            errors.add("El tipo de negocio es obligatorio");
        }

        if (cliente.getFechaAfiliacion() != null && cliente.getFechaAfiliacion().isAfter(LocalDate.now())) {
            errors.add("La fecha de afiliación no puede ser futura");
        }

        return errors;
    }

    public boolean isValid(Cliente cliente) {
        return validate(cliente).isEmpty();
    }

    public void normalizeCliente(Cliente cliente) {
        if (cliente == null) return;

        logger.debug("Normalizando cliente");

        cliente.setNombres(StringUtils.capitalize(cliente.getNombres().toLowerCase().trim()));
        cliente.setApellidos(StringUtils.capitalize(cliente.getApellidos().toLowerCase().trim()));

        if (StringUtils.isNotBlank(cliente.getEmail())) {
            cliente.setEmail(cliente.getEmail().toLowerCase().trim());
        }

        if (StringUtils.isNotBlank(cliente.getOcupacion())) {
            cliente.setOcupacion(StringUtils.capitalize(cliente.getOcupacion().toLowerCase().trim()));
        }
        
        if (StringUtils.isNotBlank(cliente.getTipoNegocio())) {
            cliente.setTipoNegocio(StringUtils.capitalize(cliente.getTipoNegocio().toLowerCase().trim()));
        }
        
    }
}

