package com.mycompany.prestoque;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDataGeneratorCliente {

    private final Random random = new Random();

    private final String[] nombres = {
        "Luis", "Lucía", "Andrés", "Camila", "Renzo", "Natalia",
        "Oscar", "Gabriela", "Sergio", "Vanessa"
    };

    private final String[] apellidos = {
        "Pérez", "Castillo", "Ramírez", "Torres", "Mendoza",
        "Rojas", "Fernández", "Campos", "Salazar", "Vargas"
    };

    private final String[] dominios = {
        "gmail.com", "outlook.com", "hotmail.com", "empresa.com"
    };

    private final String[] tiposDocumento = {"DNI", "CE"};
    private final String[] generos = {"M", "F"};
    private final String[] ocupaciones = {"Dependiente", "Independiente", "Estudiante", "Desempleado"};
    private final String[] tiposNegocio = {"Bodega", "Tienda", "Servicios", "Tecnología", "Restaurante"};
    private final String[] calles = {"Av. Perú", "Jr. Lima", "Calle Bolívar", "Psje. Grau", "Av. La Marina"};

    private boolean isClienteComplete(Cliente cliente) {
        return cliente != null &&
               cliente.getClienteId()!= null &&
               cliente.getNombres() != null && !cliente.getNombres().trim().isEmpty() &&
               cliente.getApellidos() != null && !cliente.getApellidos().trim().isEmpty() &&
               cliente.getTipoDocumento() != null &&
               cliente.getNumeroDocumento()!= null && !cliente.getNumeroDocumento().isEmpty() &&
               cliente.getEmail() != null && cliente.getEmail().contains("@") &&
               cliente.getTelefono() != null && cliente.getTelefono().matches("\\d{9}") &&
               cliente.getFechaNacimiento() != null;
    }

    private Cliente generateRandomCliente(Long id) {
        Cliente cliente = new Cliente();

        try {
            cliente.setClienteId(id);

            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];
            cliente.setNombres(nombre);
            cliente.setApellidos(apellido);

            String tipoDoc = tiposDocumento[random.nextInt(tiposDocumento.length)];
            cliente.setTipoDocumento(tipoDoc);
            if ("DNI".equals(tipoDoc)) {
                cliente.setNumeroDocumento(String.format("%08d", random.nextInt(100_000_000)));
            } else {
                cliente.setNumeroDocumento("CE" + String.format("%07d", random.nextInt(10_000_000)));
            }

            cliente.setTelefono("9" + String.format("%08d", random.nextInt(100_000_000)));

            String dominio = dominios[random.nextInt(dominios.length)];
            String email = nombre.toLowerCase() + "." + apellido.toLowerCase() +
                          (random.nextInt(100) < 20 ? String.valueOf(random.nextInt(99)) : "") +
                          "@" + dominio;
            cliente.setEmail(email);

            int edad = 18 + random.nextInt(50);
            LocalDate birthDate = LocalDate.now().minusYears(edad).minusDays(random.nextInt(365));
            cliente.setFechaNacimiento(birthDate);

            cliente.setGenero(generos[random.nextInt(generos.length)]);
            cliente.setDireccion(calles[random.nextInt(calles.length)] + " " + (random.nextInt(900) + 100));
            cliente.setOcupacion(ocupaciones[random.nextInt(ocupaciones.length)]);

            if ("Independiente".equals(cliente.getOcupacion())) {
                cliente.setTipoNegocio(tiposNegocio[random.nextInt(tiposNegocio.length)]);
            } else {
                cliente.setTipoNegocio(null);
            }

            LocalDate startDate = LocalDate.of(2025, 1, 1);  // 1 de enero 2025
            LocalDate endDate = LocalDate.now();             // fecha actual

            // Calcular días entre startDate y endDate
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

           // Generar un número aleatorio de días a sumar desde startDate
            long randomDays = (long) (random.nextDouble() * daysBetween);

           // Crear fecha aleatoria
            LocalDate fechaAfiliacion = startDate.plusDays(randomDays);

            cliente.setFechaAfiliacion(fechaAfiliacion);

            cliente.setPuntajeCrediticio(300 + random.nextInt(701)); // 300 a 1000

            return cliente;

        } catch (Exception e) {
            System.err.println("❌ Error generando cliente " + id + ": " + e.getMessage());
            return null;
        }
    }

    public List<Cliente> generateTestClientes(int cantidad) {
        List<Cliente> clientes = new ArrayList<>();
        for (long i = 1; i <= cantidad; i++) {
            Cliente cliente = generateRandomCliente(i);
            if (isClienteComplete(cliente)) {
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public void generateTestFile(String filePath, int cantidadClientes) throws IOException {
        System.out.println("🔄 Generando archivo de clientes: " + filePath);
        System.out.println("👥 Clientes a generar: " + cantidadClientes);

        List<Cliente> clientes = generateTestClientes(cantidadClientes);

        ExcelServiceCliente excelService = new ExcelServiceCliente();
        excelService.writeClientesToExcel(clientes, filePath);

        System.out.println("✅ Archivo generado exitosamente: " + filePath +
                           " (" + clientes.size() + " clientes)");
    }

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║ GENERADOR DE DATOS DE PRUEBA - CLIENTES ║");
        System.out.println("╚════════════════════════════════════╝");

        TestDataGeneratorCliente generator = new TestDataGeneratorCliente();

        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
                System.out.println("📁 Directorio 'data' creado");
            }

            System.out.println("\n--- Generando archivo pequeño ---");
            generator.generateTestFile("data/clientes_prueba.xlsx", 10);

            System.out.println("\n--- Generando archivo mediano ---");
            generator.generateTestFile("data/clientes_100.xlsx", 100);

            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║       GENERACIÓN COMPLETADA        ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("📁 Archivos generados:");
            System.out.println("  • data/clientes_prueba.xlsx (10 clientes)");
            System.out.println("  • data/clientes_100.xlsx (100 clientes)");

        } catch (IOException e) {
            System.err.println("❌ Error de E/S: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
