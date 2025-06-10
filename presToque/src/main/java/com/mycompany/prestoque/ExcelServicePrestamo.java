package com.mycompany.prestoque;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ExcelServicePrestamo {

    private void createCell(Row row, int column, BigDecimal value, CellStyle style) {
    Cell cell = row.createCell(column);
    if (value != null) {
        cell.setCellValue(value.doubleValue());
    } else {
        cell.setCellValue("");
    }
    cell.setCellStyle(style);
}

private void createCell(Row row, int column, Short value, CellStyle style) {
    Cell cell = row.createCell(column);
    if (value != null) {
        cell.setCellValue(value);
    } else {
        cell.setCellValue("");
    }
    cell.setCellStyle(style);
}

    public void writePrestamosToExcel(List<Prestamo> prestamos, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Préstamos");

            // Estilos
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle dateStyle = createDateStyle(workbook);

            // Cabeceras
            String[] headers = {
                "Nombres", "Apellidos", "Tipo Documento", "Número Documento",
                "Moneda", "Monto Solicitado", "Monto Aprobado", "Interés Anual (%)",
                "Plazo (días)", "Fecha Solicitud", "Fecha Aprobación", "Fecha Desembolso", "Fecha Vencimiento",
                "Destino","Descripción Destino", "Estado", "Observaciones"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (Prestamo p : prestamos) {
                Row row = sheet.createRow(rowNum++);
                int col = 0;

                createCell(row, col++, Objects.requireNonNullElse(p.getNombresCliente(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(p.getApellidosCliente(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(p.getTipoDocumentoCliente(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(p.getNumeroDocumentoCliente(), ""), dataStyle);
                createCell(row, col++, p.getNombreMonedas(), dataStyle);
                createCell(row, col++, p.getMontoSolicitado(), dataStyle);
                createCell(row, col++, p.getMontoAprobado(), dataStyle);
                createCell(row, col++, p.getInteresAnual(), dataStyle);
                createCell(row, col++, p.getPlazoDias(), dataStyle);

                createDateCell(row, col++, toLocalDate(p.getFechaSolicitud()), dateStyle);
                createDateCell(row, col++, toLocalDate(p.getFechaAprobacion()), dateStyle);
                createDateCell(row, col++, toLocalDate(p.getFechaDesembolso()), dateStyle);
                createDateCell(row, col++, p.getFechaVencimiento(), dateStyle);

                createCell(row, col++, Objects.requireNonNullElse(p.getNombreDestino(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(p.getDescripcionDestino(), ""), dataStyle);
                createCell(row, col++, String.valueOf(Objects.requireNonNullElse(p.getEstado(), "")), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(p.getObservaciones(), ""), dataStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new IOException("Error al generar archivo Excel de préstamos: " + e.getMessage(), e);
        }
    }

    // Métodos utilitarios para crear celdas

    private void createCell(Row row, int column, String value, CellStyle style) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value != null ? value : "");
        cell.setCellStyle(style);
    }

    private void createCell(Row row, int column, Integer value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private void createCell(Row row, int column, Long value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private void createCell(Row row, int column, Double value, CellStyle style) {
        Cell cell = row.createCell(column);
        if (value != null) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private void createDateCell(Row row, int column, LocalDate date, CellStyle style) {
        Cell cell = row.createCell(column);
        if (date != null) {
            Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            cell.setCellValue(d);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

    private LocalDate toLocalDate(java.time.LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toLocalDate() : null;
    }

    // Estilos

    private CellStyle createHeaderStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());

        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        addBorders(style);
        return style;
    }

    private CellStyle createDataStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        addBorders(style);
        return style;
    }

    private CellStyle createDateStyle(Workbook workbook) {
        CellStyle style = createDataStyle(workbook);
        CreationHelper createHelper = workbook.getCreationHelper();
        style.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

    private void addBorders(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}
