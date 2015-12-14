package com.shellming.preprocessors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruluo1992 on 11/26/2015.
 */
public class SimpleMsgFilter {

    public static void filter(String source, String target, String keyword){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target));
            StringBuffer sb = new StringBuffer();
            int count = 0;
            while(true){
                String line = reader.readLine();
                if(line == null)
                    break;
                if(line.contains(keyword)) {
                    sb.append(line).append("\r\n");
                    count++;
                }
            }
            System.out.println(String.format("Keyword:%s\r\nCount:%d", keyword, count));
            writer.write(String.format("Keyword:%s\r\nCount:%d", keyword, count));
            writer.write(sb.toString());
            reader.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            String base = "F:\\Courseware\\面向对象\\project\\";
            String source = base + "sendrep_20150701-0.txt";
            String[] keywords = new String[]{"京东", "淘宝", "美丽说", "容通", "易宝", "微信", "支付", "购买"};
            for(String keyword : keywords){
                String target = base + keyword + ".txt";
                filter(source, target, keyword);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
