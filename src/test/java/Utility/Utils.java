package Utility;

public class Utils {

    public static boolean isStringArrayAsce(String[] arr){
        if(arr.length==0|| arr.length==1){
            return true;
        }

        for(int i=0;i<arr.length-1;i++){
            String str1=arr[i].toLowerCase();
            String str2=arr[i+1].toLowerCase();
            int b= str1.compareTo(str2);
            if(b>0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStringArrayDesc(String[] arr){
        if(arr.length==0|| arr.length==1){
            return true;
        }

        for(int i=0;i<arr.length-1;i++){
            String str1=arr[i].toLowerCase();
            String str2=arr[i+1].toLowerCase();
            int b= str2.compareTo(str1);
            if(b>0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDoubleArrayAsc(double[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            return true;
        }
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]>arr[i+1]){
                return false;
            }
        }
        return true;

    }

    public static boolean isDoubleArrayDesc(double[] arr) {
        if (arr.length == 0 || arr.length == 1) {
            return true;
        }
        for(int i=0;i<arr.length-1;i++){
            if(arr[i+1]>arr[i]){
                return false;
            }
        }
        return true;

    }

    public static String randomString(int length){
        String str="abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb= new StringBuffer();
        for(int i=0;i<length;i++){
            int j=((int)(Math.random()*100))/4;
            sb.append(str.charAt(j));
        }
        return sb.toString();
    }

    public static String randomNumber(int length){
        String str="0123456789";
        StringBuffer sb= new StringBuffer();
        for(int i=0;i<length;i++){
            int j=((int)(Math.random()*100))/10;
            sb.append(str.charAt(j));
        }
        return sb.toString();
    }
   
}
