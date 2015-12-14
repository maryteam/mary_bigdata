package com.shellming.preprocessors;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ruluo1992 on 12/13/2015.
 */
public class TemplateGetter {
    private List<BufferedWriter> templates;
    private List<String> templateSamples;

    private String baseDir;
    private String inputName;

    private final int threshold = 20;

    private Map<String, String[]> toCombine;  // 等待合并的短信，key为userId-msgId-total
    private Pattern pattern = Pattern.compile("@([0-9]+)\\[([0-9]+)/([0-9]+)\\](.*)");

    public TemplateGetter(String baseDir, String inputName) {
        this.baseDir = baseDir;
        this.inputName = inputName;

        templates = new ArrayList<>();
        templateSamples = new ArrayList<>();

        toCombine = new HashMap<>();
    }

    private boolean isCombineDone(String[] parts){
        for(int i = 0; i < parts.length; i++){
            if(StringUtils.isEmpty(parts[i]))
                return false;
        }
        return true;
    }

    private String combineContent(String[] parts) {
        StringBuilder sb = new StringBuilder();
        for(String str : parts){
            sb.append(str);
        }
        return sb.toString();
    }

    // 合并长短信，形如@171[2/2]ttp://url.cn/adPjmc。(回复T12164不再收到此类信息)
    private String getCombinedContent(String content, String userId){
        Matcher matcher = pattern.matcher(content);
        if(matcher.matches()){
            String msgId = matcher.group(1);
            Integer current = Integer.valueOf(matcher.group(2));
            Integer total = Integer.valueOf(matcher.group(3));
            String part = matcher.group(4);
            String key = String.format("%s-%s-%d", userId, msgId, total);
            String[] parts = toCombine.get(key);
            if(parts == null){
                parts = new String[total];
                toCombine.put(key, parts);
            }
            if(current > parts.length){
                System.out.println("drop one record:" + key);
                return null;
            }
            parts[current - 1] = part;
            if(isCombineDone(parts)){
                toCombine.remove(msgId);
                return combineContent(parts);
            }
        }
        return null;
    }

    /*
    * 返回null的情况：
    * \t分割后结果数据过少
    * 一条长短信未合并完成
    * 其他未知格式异常
    * */
    private String getContent(String line){
        if(line == null)
            return null;
        String[] parts = line.split("\t");
        if(parts.length < 2)
            return null;
        String content = parts[1];
        String userId = parts[0];
        if(content.startsWith("@")){
            return getCombinedContent(content, userId);
        }
        else
            return parts[1];
    }

    // 不考虑长短信合并的情况
    private String getPlainContent(String line){
        if(line == null)
            return null;
        String[] parts = line.split("\t");
        if(parts.length < 2)
            return null;
        return parts[1];
    }
    /*
    * input @101[2/2]http://url.cn/U1NIXb。(回复T12140不再收到此类信息)
    * output 101
    * */
    private String getMsgId(String content){
        int start = 1;
        int end = content.indexOf('[');
        return content.substring(start, end);
    }

    private BufferedWriter getTemplate(String content) throws IOException {
        BufferedWriter result = null;
        for(int i = 0; i < templateSamples.size(); i++){
            String sample = templateSamples.get(i);
            if(StringUtils.getLevenshteinDistance(sample, content) < threshold){
                result = templates.get(i);
                break;
            }
        }

        if(result == null) {
            String templateName = String.format("template-%d.txt", templates.size());
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(baseDir, templateName)));
            templates.add(writer);
            templateSamples.add(content);

            result = writer;
        }

        return result;
    }

    public void close() throws IOException {
        for(int i = 0; i < templates.size(); i++){
            templates.get(i).close();
        }
    }

    public String reform(String line, String content){
        String[] parts = line.split("\t");
        if(parts[1].equals(content))
            return line;
        else{
            parts[1] = content;
            StringBuilder sb = new StringBuilder();
            for(String str : parts) {
                sb.append(str).append("\t");
            }
            return sb.toString();
        }
    }

    public void combine(String outputName){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(baseDir, inputName)));
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(baseDir, outputName)));
            int count = 0;
            while(true){
                count++;
                if(count % 1000 == 0)
                    System.out.println("combine done:" + count);
                String line = reader.readLine();
                if(line == null)
                    break;
                String content = getContent(line);
                if(content == null){
                    continue;
                }
                line = reform(line, content);
                writer.write(line + "\r\n");
            }
            reader.close();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getTemplates() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(baseDir, inputName)));
            int count = 0;
            while(true){
                count++;
                if(count % 1000 == 0){
                    System.out.println("process done:" + count);
                }
                String line = reader.readLine();
                if(line == null)
                    break;
                String content = getPlainContent(line);
                if(content == null){
                    continue;
                }
                BufferedWriter writer = getTemplate(content);
                line = reform(line, content);
                writer.write(line + "\r\n");
            }
            reader.close();
            close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(baseDir, "sample.txt")));
            for(int i = 0; i < templateSamples.size(); i++){
                String line = String.format("template-%d\t%s\r\n", i, templateSamples.get(i));
                writer.write(line);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String baseDir = "F:\\Courseware\\面向对象\\project\\";
        String inputName = "sendrep_20150701-0.txt";

        TemplateGetter getter = new TemplateGetter(baseDir, inputName);
//        getter.getTemplates();
        getter.combine("output.txt");
    }
}
