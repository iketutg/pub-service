package id.digitallending.publishers.core.application.util;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    /*
    Parsing response SMS Gateway
    msisdn=087781721777&rcDesc=Message has been sent&rc=0
     */
    public static boolean getRC(String message){
        String[] res = message.split("&", 0);
        boolean response = false;
        for(String myStr: res) {
            String[] res2 = myStr.split("=",0);
            if (res2[0].equalsIgnoreCase("rc") && res2.length == 2){
                if (res2[1].equals("0")) {
                    response = true ;
                    break;
                }
            }
        }
        return response;
    }

    public static String getToken(){
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999);
        return String.valueOf(randomNum);
    }

}
