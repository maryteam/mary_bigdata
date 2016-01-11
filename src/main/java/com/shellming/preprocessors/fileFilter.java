package com.shellming.preprocessors;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Kirk Gong on 2016/1/7 0007.
 */
public class fileFilter {

    public  static void main(String[] args) throws IOException {
        String baseDir = "E:\\学习\\courseproject\\output\\";
        String baseDir1 = "E:\\学习\\courseproject\\outputC\\";
        File fileDir = new File(baseDir);
        File[] files = fileDir.listFiles();
        ArrayList<BufferedWriter> fileArrayList = new ArrayList<BufferedWriter>();

        File fileDir1 = new File(baseDir1);
        int addd = 0;
        for (int i=0; i<5; i++){
            String a = String.format("%d.txt", i);
            File tempfile = new File(baseDir1, a);
            if(!tempfile.exists())
                tempfile.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempfile, true), "UTF-8"));
            fileArrayList.add(bw);
        }


        for(File a: files){
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(a), "UTF-8"));
            String tempString;
            while ((tempString = br.readLine()) != null){
                if(tempString.contains("财付通") || tempString.contains("QQ钱包") || tempString.contains("微信支付")){
                    fileArrayList.get(0).write(tempString + "\r\n");
                }else if (tempString.contains("京东白条") || tempString.contains("白条")){
                    fileArrayList.get(1).write(tempString + "\r\n");
                }else if(tempString.contains("支付宝")){
                    fileArrayList.get(2).write(tempString + "\r\n");
                }else if(tempString.contains("易宝支付") || tempString.contains("易宝")){
                    fileArrayList.get(3).write(tempString + "\r\n");
                }else if(tempString.contains("联动优势")){
                    fileArrayList.get(4).write(tempString + "\r\n");
                }
            }
        }

        for(int i=0; i<fileArrayList.size(); i++){
            fileArrayList.get(i).close();
        }
    }
}


