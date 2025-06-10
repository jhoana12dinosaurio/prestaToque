package com.mycompany.prestoque;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TestDataGeneratorPrestamo {

    private final Random random = new Random();

    private final String[] estados = {"Pendiente", "Aprobado", "Desembolsado", "Cancelado", "Rechazado"};

    private final String[] monedas = {"PEN", "USD", "EUR"}; // Soles, Dólares, Euros

    private final String[] destinos = {
        "Cuenta de ahorro", "Cuenta corriente", "Yape", "Plin", "Tunki",
        "Caja Municipal", "Caja Arequipa", "BCP", "Interbank"
    };

    private String generarCuentaAleatoria(String tipo) {
        String cuenta = String.format("%03d-%011d", random.nextInt(999), Math.abs(random.nextLong()) % 1_000_000_00000L);
        return tipo + " " + cuenta;
    }

    private String generarCelularAleatorio() {
        return "9" + (10000000 + random.nextInt(90000000));
    }

    private Prestamo generateRandomPrestamo(Long id, Cliente cliente) {
        Prestamo p = new Prestamo();

        p.setPrestamoId(id);
        p.setClienteId(cliente.getClienteId());

        p.setNombresCliente(cliente.getNombres());
        p.setApellidosCliente(cliente.getApellidos());
        p.setTipoDocumentoCliente(cliente.getTipoDocumento());
        p.setNumeroDocumentoCliente(cliente.getNumeroDocumento());

        p.setTipoPrestamoId(String.valueOf(1 + random.nextInt(3))); // 1-3

        int monedaIndex = random.nextInt(monedas.length);
        p.setMonedaId(String.valueOf(monedaIndex + 1)); // ID como texto ("1", "2", "3")
        p.setNombreMonedas(monedas[monedaIndex]);
        
        BigDecimal monto = BigDecimal.valueOf(500 + random.nextInt(9501)); // 500 - 10,000
        p.setMontoSolicitado(monto);
        BigDecimal montoAprobado = monto.multiply(BigDecimal.valueOf(0.8 + random.nextDouble() * 0.2)).setScale(2, BigDecimal.ROUND_HALF_UP);
        p.setMontoAprobado(montoAprobado);

        p.setInteresAnual(BigDecimal.valueOf(15 + random.nextInt(16)));  // 15% - 30%
        p.setPlazoDias((short) (30 + random.nextInt(336)));  // 30 - 365 días

        LocalDateTime fechaSolicitud = LocalDateTime.now().minusDays(random.nextInt(180));
        p.setFechaSolicitud(fechaSolicitud);
        p.setFechaAprobacion(fechaSolicitud.plusDays(random.nextInt(3))); // +0 a +2 días
        p.setFechaDesembolso(p.getFechaAprobacion().plusHours(random.nextInt(24)));

        p.setFechaVencimiento(p.getFechaDesembolso().toLocalDate().plusDays(p.getPlazoDias()));

        p.setEstado(estados[random.nextInt(estados.length)]);

        if ("Rechazado".equals(p.getEstado())) {
            p.setObservaciones("No cumple con los requisitos.");
        } else if ("Pendiente".equals(p.getEstado())) {
            p.setObservaciones("En evaluación.");
        } else {
            p.setObservaciones("Operación procesada correctamente.");
        }

        // Añadir datos del destino
        int destinoIndex = random.nextInt(destinos.length);
        String nombreDestino = destinos[destinoIndex];
        p.setDestinoId((byte) (destinoIndex + 1));
        p.setNombreDestino(nombreDestino);

        if (nombreDestino.equals("Yape") || nombreDestino.equals("Plin") || nombreDestino.equals("Tunki")) {
            p.setDescripcionDestino(generarCelularAleatorio() + " (" + nombreDestino + ")");
        } else {
            p.setDescripcionDestino(generarCuentaAleatoria(nombreDestino));
        }

        return p;
    }

    public List<Prestamo> generateTestPrestamos(List<Cliente> clientes) {
        List<Prestamo> prestamos = new ArrayList<>();
        long id = 1;
        for (Cliente cliente : clientes) {
            Prestamo p = generateRandomPrestamo(id++, cliente);
            if (p.isValid()) {
                prestamos.add(p);
            }
        }
        return prestamos;
    }

    public void generateTestFile(String filePath, int cantidadPrestamos) throws IOException {
        System.out.println("🔄 Generando archivo de préstamos: " + filePath);
        System.out.println("📄 Préstamos a generar: " + cantidadPrestamos);

        TestDataGeneratorCliente clienteGenerator = new TestDataGeneratorCliente();
        List<Cliente> clientes = clienteGenerator.generateTestClientes(cantidadPrestamos);

        List<Prestamo> prestamos = generateTestPrestamos(clientes);

        ExcelServicePrestamo ExcelServicePrestamo = new ExcelServicePrestamo();
        ExcelServicePrestamo.writePrestamosToExcel(prestamos, filePath);

        System.out.println("✅ Archivo generado exitosamente: " + filePath +
                " (" + prestamos.size() + " préstamos)");
    }

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║ GENERADOR DE DATOS DE PRÉSTAMOS   ║");
        System.out.println("╚════════════════════════════════════╝");

        TestDataGeneratorPrestamo generator = new TestDataGeneratorPrestamo();

        try {
            File dataDir = new File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
                System.out.println("📁 Directorio 'data' creado");
            }

            System.out.println("\n--- Generando archivo de prueba ---");
            generator.generateTestFile("data/prestamos_prueba.xlsx", 10);

            System.out.println("\n--- Generando archivo mediano ---");
            generator.generateTestFile("data/prestamos_100.xlsx", 100);

            System.out.println("\n╔════════════════════════════════════╗");
            System.out.println("║     GENERACIÓN DE PRÉSTAMOS OK     ║");
            System.out.println("╚════════════════════════════════════╝");

        } catch (IOException e) {
            System.err.println("❌ Error de E/S: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
