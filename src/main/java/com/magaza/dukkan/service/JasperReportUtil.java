package com.magaza.dukkan.service;


import com.lowagie.text.pdf.BaseFont;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class JasperReportUtil {

    public static byte[] generatePdfFromJasper(long izinId) throws JRException, SQLException {
        InputStream jasperStream = JasperReportUtil.class.getResourceAsStream("/reports/report1.jasper");

        Map<String, Object> parameters = new     HashMap<>();
        parameters.put("id", izinId);  // The key must match the parameter name in the report query

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, getConnection());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream.toByteArray();
    }


    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dukkandb", "postgres", "1");
    }


}

