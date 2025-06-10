package prestoque;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class MostrarExcelEnJFrame extends JFrame {

    private JTable table;
    private JButton btnCargarArchivo;
    private JLabel lblArchivo;

    public MostrarExcelEnJFrame() {
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Vista de Préstamos Internacionales");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new FlowLayout());
        btnCargarArchivo = new JButton("Cargar Archivo Excel");
        lblArchivo = new JLabel("Ningún archivo cargado");
        
        btnCargarArchivo.addActionListener(e -> seleccionarArchivo());
        
        panelSuperior.add(btnCargarArchivo);
        panelSuperior.add(lblArchivo);
        add(panelSuperior, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Archivos Excel", "xlsx", "xls"));
        
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            cargarExcel(archivo);
        }
    }

    public void cargarExcel(File archivo) {
        try {
            mostrarExcelEnTabla(archivo);
            lblArchivo.setText("Archivo: " + archivo.getName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al leer el archivo: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void mostrarExcelEnTabla(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        List<String> columnas = new ArrayList<>();
        List<Object[]> datos = new ArrayList<>();

        boolean esCabecera = true;
        for (Row fila : sheet) {
            if (fila == null) continue;
            
            List<Object> filaDatos = new ArrayList<>();
            for (int i = 0; i < fila.getLastCellNum(); i++) {
                Cell celda = fila.getCell(i);
                if (celda == null) {
                    filaDatos.add("");
                    continue;
                }
                
                switch (celda.getCellType()) {
                    case STRING:
                        filaDatos.add(celda.getStringCellValue());
                        break;
                    case NUMERIC:
                        filaDatos.add(celda.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        filaDatos.add(celda.getBooleanCellValue());
                        break;
                    default:
                        filaDatos.add("");
                }
            }
            
            if (esCabecera) {
                for (Object o : filaDatos) {
                    columnas.add(o != null ? o.toString() : "");
                }
                esCabecera = false;
            } else {
                datos.add(filaDatos.toArray());
            }
        }

        workbook.close();
        fis.close();

        String[] columnasArray = columnas.toArray(new String[0]);
        Object[][] datosArray = datos.toArray(new Object[0][]);
        
        DefaultTableModel modelo = new DefaultTableModel(datosArray, columnasArray);
        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MostrarExcelEnJFrame vista = new MostrarExcelEnJFrame();
                vista.setVisible(true);
                
                // Cargar archivo automáticamente si existe
                File archivo = new File("prestamo_100.xlsx");
                if (archivo.exists()) {
                    vista.cargarExcel(archivo);
                    System.out.println("Archivo cargado: " + archivo.getName());
                } else {
                    System.out.println("Archivo no encontrado: " + archivo.getAbsolutePath());
                }
            }
        });
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
