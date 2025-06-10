package com.mycompany.prestoque;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ExcelServiceCliente {

    public void writeClientesToExcel(List<Cliente> clientes, String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Clientes");

            // Crear estilos
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle dateStyle = createDateStyle(workbook);

            // Cabeceras
            String[] headers = {
                "ID", "Nombres", "Apellidos", "Tipo Documento", "Número", "Teléfono",
                "Email", "Fecha Nacimiento", "Fecha Afiliación" , "Genero" , "Dirección" , "Ocupación" , "Tipo de Negocio", "Puntaje Crediticio"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Datos
            int rowNum = 1;
            for (Cliente c : clientes) {
                Row row = sheet.createRow(rowNum++);
                int col = 0;

                createCell(row, col++, c.getClienteId(), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getNombres(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getApellidos(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getTipoDocumento(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getNumeroDocumento(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getTelefono(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getEmail(), ""), dataStyle);

                createDateCell(row, col++, c.getFechaNacimiento(), dateStyle);
                createDateCell(row, col++, c.getFechaAfiliacion(), dateStyle);
                
                createCell(row, col++, Objects.requireNonNullElse(c.getGenero(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getDireccion(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getOcupacion(), ""), dataStyle);
                createCell(row, col++, Objects.requireNonNullElse(c.getTipoNegocio(), ""), dataStyle);
                
                createCell(row, col++, c.getPuntajeCrediticio(), dataStyle);
            }

            // Autoajustar columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Guardar archivo
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new IOException("Error al generar archivo Excel: " + e.getMessage(), e);
        }
    }

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

    private void createDateCell(Row row, int column, java.time.LocalDate date, CellStyle style) {
        Cell cell = row.createCell(column);
        if (date != null) {
            Date d = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            cell.setCellValue(d);
        } else {
            cell.setCellValue("");
        }
        cell.setCellStyle(style);
    }

   private CellStyle createHeaderStyle(Workbook workbook) {
    Font font = workbook.createFont();
    font.setBold(true);
    font.setColor(IndexedColors.WHITE.getIndex());

    CellStyle style = workbook.createCellStyle();
    style.setFont(font);
    style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);   // Sin getIndex()
    style.setAlignment(HorizontalAlignment.CENTER);           // Sin getCode()
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
