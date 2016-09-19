/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudbeauty.classes;

import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author Debug
 */
public class Relatorios {
    
    Cliente cliente;
    
    
    public void geraRelatorioClientes() throws JRException{
        
        try{
            
            String path = "relatorios//reportClientes.jrxml";
            
            List<Cliente> lista = new ArrayList<Cliente>();
            
            cliente = new Cliente();
            cliente.setNome("Fulano");
            cliente.setEmail("email@email.com");
            cliente.setTelefone("(21) 5555-5555");
            cliente.setCelular("(21) 55555-5555");
            
            lista.add(cliente);
            
            JasperReport report = JasperCompileManager.compileReport(path);
            JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
            
            JasperExportManager.exportReportToPdfFile(print, "relatorios//RelatorioClientes.pdf");
            
            System.out.println("Relatório gerado");

            File pdf = new File("relatorios//RelatorioClientes.pdf");
            
            try{
                Desktop.getDesktop().open(pdf);
            }catch(Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }catch(Exception ex){
            System.out.println("Houve um erro na emissão do relatório de clientes. Erro: " + ex.getMessage());
        }
        
        
    }
}
