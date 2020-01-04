package com.jyyq.platformcommon.auto.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AutoCreateFileUtils {
    /*
     * 生成文件
     */
    public static void createFile(String filePath, String content){
        File serviceFile = new File(filePath);
        try {
            if(!serviceFile.exists()){
                serviceFile.createNewFile();
            }
            FileWriter fw = new FileWriter(serviceFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            String fileName = filePath.substring(filePath.lastIndexOf('/')+1);
            System.out.println("生成"+fileName+"成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
