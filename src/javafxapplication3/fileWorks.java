/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public class fileWorks {
    String os;
    
    public HashMap loadParams() throws IOException, InterruptedException{
        os = System.getProperty("os.name").toLowerCase();
        HashMap<String,String> brPath = new HashMap<>();
        final String dir = System.getProperty("user.dir");
         System.out.println("current dir = " + dir);
         String path = System.getProperty("user.dir")+
                File.separator+"fconfig"+File.separator+"programs-list-main.txt";
         String pathR = System.getProperty("user.dir")+
                File.separator+"fconfig"+File.separator+"ready.txt";
        // Проверяем закончена ли запись в файл
        // Должен появиться файл ready.txt с текстом 77
        boolean isOpen = true;
            while (isOpen){
                try {
                    File tFile = new File(pathR);
                    FileInputStream fos = new FileInputStream(tFile);
                    InputStreamReader ipf1 = new InputStreamReader(fos,StandardCharsets.UTF_8);
                    BufferedReader reader1 = new BufferedReader(ipf1);
                    String line1 = reader1.readLine();
                    int t = 0;
                    try{
                        t = Integer.parseInt(line1.substring(1,3));
                    }catch(Exception ex){
                        t = Integer.parseInt(line1);
                    }finally{
                        t = Integer.parseInt(line1);
                    }
                    if (77 !=t){
                    } else {
                        isOpen = false;
                    }
                   // runServices.addStrToFom("Открыт");
                } catch(IOException e) {
                    isOpen = true;
                    wait(1000);
                  //  runServices.jTextArea1.append("Открыт"+"\n");
                }}
            // Читаем из programs-list-main.txt
            // пути до найденных браузеров
            //  FileReader ipf = new FileReader(inFile);
            File inFile = new File(path);
            FileInputStream fis = new FileInputStream(inFile);
            InputStreamReader ipf = new InputStreamReader(fis,StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(ipf);
            
        try{
            if (os.contains("win")) {
                String line = reader.readLine();
                while (line != null) { 
                line = reader.readLine();
              //  System.out.println(line);
                // считываем остальные строки в цикле
                // и передаем на обработку 
               // if (line != null){
                    if (line.indexOf("Каталог")>0){
                        String tstr = workString(line.trim());
                        if (tstr != "-1"){
                            String[] keyValue = tstr.split(";");
                            brPath.put(keyValue[0],keyValue[1]);  
                            }
                        }
                   // }
                }
            } else if (os.contains("mac")) {
                String line = reader.readLine();
                while (line != null) { 
                // считываем строки в цикле
                // и передаем на обработку 
                //if (line != null){
                    if (line.indexOf("Каталог")>0){
                        String tstr = workStringLin(line.trim());
                        if (tstr != "-1"){
                            String[] keyValue = tstr.split(";");
                            brPath.put(keyValue[0],keyValue[1]);  
                            }
                        }
                  //  }
                line = reader.readLine();
                }    
            }else if (os.contains("nix") || os.contains("nux")) {       
                String line = reader.readLine();
                while (line != null) { 
                // считываем строки в цикле
                // и передаем на обработку 
              //  if (line != null){
                    if (line.indexOf("/")>0){
                        String tstr = workStringLin(line.trim());
                        if (tstr != "-1"){
                            String[] keyValue = tstr.split(";");
                            brPath.put(keyValue[0],keyValue[1]);  
                            }
                        }
                 //   }
                line = reader.readLine();
                }
            }    
            } catch (Exception e) {
                e.printStackTrace();
            }

        return brPath;
    }
    
    /*
    Проверяем переданную строку на наличие путей к 
    браузерам в системе
    */
    public String workString(String tstr){
        String browsers[] = {"chromium","chrome","comodo","yandex",
        "opera","theworld","tor","firefox"};
        String res = "";
        if (!(tstr.indexOf("Каталог:")<0)){
            for (String browser : browsers) {
                if (tstr.toUpperCase().indexOf(browser.toUpperCase())>0){
                    switch (browser){
                        case ("opera"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","" )+"\\"+"launcher.exe";
                           break;
                        case ("tor"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","")+"\\"+"firefox.exe";
                           break; 
                        case ("comodo"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","")+"\\"+"dragon.exe";
                           break; 
                           case ("yandex"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","")+"\\"+"browser.exe";
                           break; 
                        default:
                            
                                res = browser+";"+
                                tstr.replace("Каталог: ", "")+"\\"+browser+".exe";
                            
                        break;   
                    }
                    
                }
            }
        }
        
        if ("".equals(res)){
            res = "-1";
        }
 //           JOptionPane.showMessageDialog(null, "Путь к браузеру" + res);
        return res;
    }
    
     /*
    Проверяем переданную строку на наличие путей к 
    браузерам в системе
    */
    public String workStringLin(String tstr){
        String browsers[] = {"chromium-browser","google-chrome","comodo","yandex",
        "opera","theworld","tor","firefox"};
        String res = "";
        if (!(tstr.indexOf("/")<0)){
            for (String browser : browsers) {
                String tstr1 = tstr.substring(0, tstr.indexOf(":"));
                if (tstr1.toUpperCase().equals(browser.toUpperCase())){
//                    switch (browser){
//                        case ("opera"):
                           res = browser+";"+browser;
//                           break;
//                        case ("tor"):
//                           res = browser+";"+
//                                   tstr.replace("Каталог: ","")+"\\"+"firefox.exe";
//                           break; 
//                        case ("comodo"):
//                           res = browser+";"+
//                                   tstr.replace("Каталог: ","")+"\\"+"dragon.exe";
//                           break; 
//                           case ("yandex"):
//                           res = browser+";"+
//                                   tstr.replace("Каталог: ","")+"\\"+"browser.exe";
//                           break; 
//                        default:
//                            
//                                res = browser+";"+
//                                tstr.replace("Каталог: ", "")+"\\"+browser+".exe";
//                            
//                        break;   
                    //}
                    
                }
            }
        }
        
        if ("".equals(res)){
            res = "-1";
        }
 //           JOptionPane.showMessageDialog(null, "Путь к браузеру" + res);
        return res;
    }
    
     /*
    Проверяем переданную строку на наличие путей к 
    браузерам в системе
    */
    public String workStringMac(String tstr){
        String browsers[] = {"chromium","chrome","comodo","yandex",
        "opera","theworld","tor","firefox"};
        String res = "";
        if (!(tstr.indexOf("Каталог:")<0)){
            for (String browser : browsers) {
                if (tstr.toUpperCase().indexOf(browser.toUpperCase())>0){
                    switch (browser){
                        case ("opera"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","" )+"\\"+"launcher.exe";
                           break;
                        case ("tor"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","")+"\\"+"firefox.exe";
                           break; 
                        case ("comodo"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","")+"\\"+"dragon.exe";
                           break; 
                           case ("yandex"):
                           res = browser+";"+
                                   tstr.replace("Каталог: ","")+"\\"+"browser.exe";
                           break; 
                        default:
                            
                                res = browser+";"+
                                tstr.replace("Каталог: ", "")+"\\"+browser+".exe";
                            
                        break;   
                    }
                    
                }
            }
        }
        
        if ("".equals(res)){
            res = "-1";
        }
 //           JOptionPane.showMessageDialog(null, "Путь к браузеру" + res);
        return res;
    }
    
    
}
