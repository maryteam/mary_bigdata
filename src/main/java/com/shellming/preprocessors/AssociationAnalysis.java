package com.shellming.preprocessors;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruluo1992 on 1/14/2016.
 */
public class AssociationAnalysis {
    private Map<String,char[]> data; // userid  jd yb ld cf
    private String[] companies = new String[]{"jd","tx","szzc","mpw","yl", "tb", "edj", "dd", "cft", "yb", "zfb", "ldys"};
    private final String outFile = "F:\\Courseware\\面向对象\\project\\out";

    public int getTotalCount(){
        return data.size();
    }

    private int getTCount(char[] value){
        int c = 0;
        for(int i = 0; i < value.length; i++){
            if(value[i] == 'T')
                c++;
        }
        return c;
    }

    public int getMultiCount(){
        int count = 0;
        for(Map.Entry<String, char[]> entry : data.entrySet()) {
            String key = entry.getKey();
            char[] value = entry.getValue();
            int c = getTCount(value);
            if(c > 1)
                count++;
        }
        return count;
    }

    public AssociationAnalysis() {
        load();
    }

    public void load(){
        File file = new File(outFile);
        data = new HashMap<>();
        if(file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while(true){
                    String line = reader.readLine();
                    if(line == null)
                        break;
                    String[] parts1 = line.split("\t");
                    if(parts1.length != 2)
                        continue;
                    String key = parts1[0];
                    char[] value = new char[companies.length];
                    for(int i = 0; i < companies.length; i++){
                        value[i] = parts1[1].charAt(i);
                    }
                    data.put(key, value);
                }
                reader.close();
                System.out.println("load done");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void writeHeader(BufferedWriter writer) throws IOException {
        writer.write("@relation 'message'\r\n");
        for(String c : companies){
            writer.write(String.format("@attribute %s {T}\r\n", c));
        }
        writer.write("@data\r\n");
    }

    public void saveAsArff(String output){
        File file = new File(output);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writeHeader(writer);
            for(Map.Entry<String, char[]> entry : data.entrySet()){
                String key = entry.getKey();
                char[] value = entry.getValue();
                if(getTCount(value) < 2)
                    continue;
                StringBuilder sb = new StringBuilder();
//                sb.append(key).append("\t");
                for(int i = 0; i < companies.length; i++){
                    sb.append(value[i]).append("\t");
                }
                sb.append("\r\n");
                String line = sb.toString();
                writer.write(line);
            }
            writer.close();
            System.out.println("save done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(){
        File file = new File(outFile);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Map.Entry<String, char[]> entry : data.entrySet()){
                String key = entry.getKey();
                char[] value = entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(key).append("\t");
                for(int i = 0; i < companies.length; i++){
                    sb.append(value[i]);
                }
                sb.append("\r\n");
                String line = sb.toString();
                writer.write(line);
            }
            writer.close();
            System.out.println("save done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(String key, int index){
        if(data.containsKey(key)){
            char[] value = data.get(key);
            value[index] = 'T';
        }
        else{
            char[] value = new char[companies.length];
            for(int i = 0; i < companies.length; i++)
                value[i] = '?';
            value[index] = 'T';
            data.put(key, value);
        }
    }

    public void read(String source, int index) {
        File file = new File(source);
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(true){
                String line = reader.readLine();
                if(line == null)
                    break;
                count++;
                if(count % 10000 == 0)
                    System.out.println("processed:" + count);
                String[] parts = line.split("\t");
                String key = parts[0];
                add(key, index);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AssociationAnalysis analysis = new AssociationAnalysis();
        analysis.saveAsArff("F:\\Courseware\\面向对象\\project\\1-14\\test.arff");
        System.out.println(analysis.getMultiCount());
        System.out.println(analysis.getTotalCount());
//        String baseDir = "F:\\Courseware\\面向对象\\project\\1-14\\";
//        String fileName = "联动优势.txt";
//        Integer index = 11;
//        analysis.read(baseDir + fileName, index);
//        analysis.save();
    }
}
