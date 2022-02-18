/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import messageclient.Client;

/**
 *
 * @author oleg
 */
public class FXMLDocumentController implements Initializable {
       
    private List<String> listIpServ;
    HashMap<String,String> brPath = new HashMap<>();
    @FXML
    private Label label;
    private AnchorPane apane2;
    private MenuItem close;
    private AnchorPane AnchorPane;
    private Button R1;
    private String ip;
    final private String port = "3128";
    @FXML
    private Hyperlink chromium;
    String appIp = "10.10.2.34"; //IP app server
    int appPort = 11235;

    
    
    
    /*
    Обработка нажатия на кнопку 
    выбора прокси
    */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String r = getProxySrv(((Button) event.getSource()).getId());
        System.out.println(r);
    }
    
    /*
    Обработка Выбора бракзера
    */
    @FXML
    private void handleToggleButtonAction(ActionEvent event) {
        String r = getBrowserPath(((ToggleButton) event.getSource()).getId());
        //System.out.println(r);
        if (r==null){
            JOptionPane.showMessageDialog(null, "Браузер не установлен или не найден путь");
        }else{
        String[] selBrowser = new String[] {r,ip,port};
        StartBrowser.main(selBrowser);
      //  StartBrowser sb = new StartBrowser();
        }
    }
    /*
    Закрываем
    */    
    @FXML
    private void handleMenuCloseAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
    }
    
    @Override
    //URL url, ResourceBundle rb
    public void initialize(URL url, ResourceBundle rb) {
         // порт прокси
        try {
            init(appIp,appPort); //"10.10.2.6",  11235
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void init(String url, int port) throws IOException, InterruptedException {
       Client cl = new Client(url,port) ;
       // Загружаем привязку пути установки к браузеру
        brPath = new fileWorks().loadParams();
        setHiperlinks();
       //Устанавливаем дефолтный машрут
       // для proxy
        try {
            cl.start();
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listIpServ = cl.getListIp();
        if (ip==null){
           ip =  getProxySrv("R1"); 
        }
  
    }
 
/*
    Получаем параметры прокси по 
    имени маршрута. Каждой кнопке - свой маршрут
    */    
    private String getProxySrv(Object rout) throws IOException{
        
        ip = "no";
        switch (rout.toString()){
            case ("R1"):
                ip = listIpServ.get(0);
                break;
            case ("R2"):
                ip = listIpServ.get(1);
                break;
            case ("R3"):
                ip = listIpServ.get(2);
                break;
            case ("R4"):
                ip = listIpServ.get(3);
                break;
            case ("R5"):
                ip = "10.10.2.5";
            //    stopVPN();
                break;   
           case ("VPN"):
           //     ip = startVPN();
                break;       
            default:

               break;    
        }
        System.out.println(ip);
        return ip;
     }
    
    /*
    Получаем параметры Браузера. Каждой кнопке - свой Браузер
    */    
    private String getBrowserPath(Object rout){
        String nm = "no";
        try{
        switch (rout.toString()){
            case ("Button"):
                nm = brPath.get("chromium");
                break;
            case ("Button2"):
                nm = brPath.get("chrome");
                break;
            case ("Button3"):
                nm = brPath.get("comodo");
                break;
            case ("Button4"):
                nm = brPath.get("yandex");
                break;
            case ("Button5"):
                nm = brPath.get("opera");
                break;   
            case ("Button6"):
                nm = brPath.get("theworld");
                break;
            case ("Button7"):
                nm = brPath.get("tor");
                break;
            case ("Button8"):
                nm = brPath.get("firefox");
                break;  
//            case ("Button9"):
//                nm = "tor";
//                break;    
//            case ("Button10"):
//                nm = "tor";
//                break;     
            default:

               break;    
        }
        }catch(NullPointerException  ex){
            throw new RuntimeException("Браузер не найден!", ex);
        }
        return nm;
    }
    
    private void setHiperlinks(){
        
        Parent parent = chromium.getParent();
        brPath.keySet().forEach((key) -> {
            //System.out.println("Key: " + key);
            Hyperlink Hyplink = (Hyperlink) parent.lookup("#"+key);
            if (Hyplink != null)
                 Hyplink.setVisible(false);
            
        });
 
        }
        
    

}
 
