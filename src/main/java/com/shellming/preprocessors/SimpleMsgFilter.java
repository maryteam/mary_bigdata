package com.shellming.preprocessors;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ruluo1992 on 11/26/2015.
 */
public class SimpleMsgFilter {

    public static void filterExclude(String source, String target, String keyword){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));
            StringBuffer sb = new StringBuffer();
            int count = 0;
            while(true){
                String line = reader.readLine();
                if(line == null)
                    break;
                if(!line.contains(keyword)) {
                    sb.append(line).append("\r\n");
                    count++;
                }
            }
            System.out.println(String.format("Keyword Exclude:%s\r\nCount:%d", keyword, count));
            writer.write(String.format("Keyword Exclude:%s\r\nCount:%d\r\n", keyword, count));
            writer.write(sb.toString());
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void filterWithRegex(String source, String target, String regex, boolean include){
        Pattern pattern = Pattern.compile(regex);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));
            StringBuffer sb = new StringBuffer();
            int count = 0;
            while(true){
                String line = reader.readLine();
                if(line == null)
                    break;
                Matcher matcher = pattern.matcher(line);
                boolean find = matcher.find();
                if(find && include) {
                    sb.append(line).append("\r\n");
                    count++;
                }
                else if(!find && !include){
                    sb.append(line).append("\r\n");
                    count++;
                }
            }
            System.out.println(String.format("Keyword:%s\r\nCount:%d\r\n", regex, count));
            writer.write(String.format("Keyword:%s\r\nCount:%d\r\n", regex, count));
            writer.write(sb.toString());
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void filter(String baseDir, String source, String keyword){
        try {
            File dir = new File(baseDir);
            File sourceFile = new File(dir, source);
            File targetFile = new File(dir, keyword + ".txt");
            File tempFile = new File(dir, "temp");
            BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile));
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
            int count = 0;
            while(true){
                String line = reader.readLine();
                if(line == null)
                    break;
                if(line.contains(keyword)) {
                    writer.write(line + "\r\n");
                    count++;
                }
                else{
                    tempWriter.write(line + "\r\n");
                }
            }
            reader.close();
            writer.close();
            tempWriter.close();

            sourceFile.delete();
            tempFile.renameTo(sourceFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            String base = "F:\\Courseware\\面向对象\\project\\12-23\\三九\\";
            String source = "current.txt";
            String keyword = "尊敬的三九会员";
            filter(base, source, keyword);
//            String[] keywords = new String[]{"京东", "淘宝", "美丽说", "容通", "易宝", "微信", "支付", "购买"};
//            for(String keyword : keywords){
//                String target = base + keyword + ".txt";
//                filter(source, target, keyword);
//            }
//            String exclude = "您本次用车费用";
//            filterExclude(source, source + "1", exclude);
//            String include = "您本次用车费用";
//            String target = base + include + ".txt";
//            filter(source, target, include);
//            String regex = "亲爱的张黎，您心仪的";
//            String target = base + "current2.txt";
//            filterWithRegex(source, target, regex, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
