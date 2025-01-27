package com.magaza.dukkan.service;


import com.lowagie.text.pdf.BaseFont;
import com.magaza.dukkan.model.Personel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperReportUtil {

    public static byte[] generatePdfFromJasper(long izinId) throws JRException, SQLException {
        InputStream jasperStream = JasperReportUtil.class.getResourceAsStream("/reports/report1.jasper");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", izinId);  // The key must match the parameter name in the report query

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, getConnection());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream.toByteArray();
    }


    public static byte[] generatePdfFromPersonelList(String birimAd, Map<String, Boolean> visibleColumns)
            throws JRException, SQLException {

        // Load the Jasper report design file
        InputStream jasperStream = JasperReportUtil.class.getResourceAsStream("/reports/personeller.jasper");


        // Prepare parameters for the report, including visibility of columns
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("birim_ad", birimAd);
        for (Map.Entry<String, Boolean> entry : visibleColumns.entrySet()) {
            parameters.put(entry.getKey(), entry.getValue());
        };
        /*parameters.put("personelDataSource", dataSource);
        parameters.put("showIdentificationNumber", visibleColumns.get("showIdentificationNumber"));
        parameters.put("showFirstName", visibleColumns.get("showFirstName"));
        parameters.put("showLastName", visibleColumns.get("showLastName"));
        parameters.put("showWorkStartDate", visibleColumns.get("showWorkStartDate"));
        parameters.put("showUnit", visibleColumns.get("showUnit"));
        parameters.put("showTask", visibleColumns.get("showTask"));
        parameters.put("showRemainingLeave", visibleColumns.get("showRemainingLeave"));
        parameters.put("showUsedLeave", visibleColumns.get("showUsedLeave"));
        parameters.put("showEmail", visibleColumns.get("showEmail"));
        parameters.put("showPhoneNumber", visibleColumns.get("showPhoneNumber"));
        parameters.put("showAddress", visibleColumns.get("showAddress"));*/

        // Fill the report with data and parameters
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, getConnection());

        // Export report to byte array (PDF format)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        // Return the PDF bytes
        return outputStream.toByteArray();
    }


    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ikdb", "postgres", "1");
    }


}

