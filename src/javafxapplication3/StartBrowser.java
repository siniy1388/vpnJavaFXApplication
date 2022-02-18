/*
//start chromium-browser --proxy-server=10.10.2.1:3128 2ip.ru yandex.ru/internet
//start google-chrome --proxy-server=10.10.2.1:3128 2ip.ru yandex.ru/internet
//start opera --proxy-server=http://10.10.2.1:3128 2ip.ru yandex.ru/internet
//start YandexPortable --proxy-server=http://10.10.2.1:3128 2ip.ru yandex.ru/internet               
//start theworld --proxy-server=http://10.10.2.1:3128 2ip.ru yandex.ru/internet
//start dragon --proxy-server=http://10.10.2.1:3128 2ip.ru yandex.ru/internet //comodo
//start tor --proxy-server=http://10.10.2.1:3128 2ip.ru yandex.ru/internet
 */
package javafxapplication3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author oleg
 */
public class StartBrowser {
 
    public static void main(String args[]) {
        String[] browsers = null;
        String browsers_win="";
        String ip = null;
        String port = null;
        // получаем наименование браузера
        if (args.length > 0){
          browsers_win =  args[0];
          ip =  args[1];
          port =  args[2];
        }else{  
          browsers = new String[] {"epiphany", "firefox", "mozilla", "konqueror", "netscape", "opera", "links", "lynx"};
          
        }
        String url = "https://2ip.ru";
        String os = System.getProperty("os.name").toLowerCase(); // получаем имя операционной системы
        Runtime rt = Runtime.getRuntime();
        try {
            if (os.contains("win")) {
               // File dir = null;
                String commandLine = browsers_win;
                        //getBrowserPath(browsers_win);
                String proxy = " --proxy-server="+ip+":"+port;
                ProcessBuilder builder = new ProcessBuilder(commandLine, "/C", 
                        proxy,url);
                builder.redirectErrorStream(true);
                final Process process = builder.start();
                // Watch the process
                watch(process);
   
            } else if (os.contains("mac")) {
                rt.exec("open " + url); // аналогично в MAC
            } else if (os.contains("nix") || os.contains("nux")) {


                StringBuilder cmd = new StringBuilder();
                for (int i = 0; i < browsers.length; i++)
                    cmd.append(i == 0 ? "" : " || ").append(browsers[i]).append(" \"").append(url).append("\" ");
                    rt.exec(new String[]{"sh", "-c", cmd.toString()});
            } else {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    private static String getBrowserPath(String brName){
//        String pathB = null;
//        switch (brName){
//            case ("chromium"):
//                pathB = "";
//                break;
//            case ("chrome"):
//                pathB = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
//                break;
//            case ("dragon"):
//               pathB = "";
//                break;
//            case ("yandex"):
//                pathB = "AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
//                break;
//            case ("opera"):
//                pathB = "C:\\Program Files (x86)\\Opera\\launcher";
//                break;   
//            case ("theworld"):
//                pathB = "";
//                break;
//            case ("tor"):
//                pathB = "C:\\distr\\portableBrowser\\Tor Browser\\Browser\\firefox.exe";
//                break;
//             default:
//
//               break;    
//        }
//        
//        return pathB;
//    }
//   
    //Обработка сообщений ProcessBuilder
    private static void watch(final Process process) {
    new Thread() {
        public void run() {
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null; 
            try {
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
               // e.printStackTrace();
            }
        }
    }.start();
}
}
