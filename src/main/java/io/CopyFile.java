package io;

import java.io.*;

/**
 * 创建文件和读取文件的方法
 */
public class CopyFile {

    public static void main(String[] args) throws IOException {
        //创建文件
        //mkTest();
        //复制文件
        copy();

    }

    public static void mkTest() throws IOException {
        String a = "我是一个由程序创建的字符串";
        File file = new File("/Users/furao/Documents/furao/test.txt");
        if(!file.exists()){
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(a.getBytes());
            stream.close();
        }
    }

    public static void copy() throws IOException {
        File file = new File("/Users/furao/Documents/furao/百年孤独.txt");
        if(file.exists()){
            FileInputStream stream = new FileInputStream(file);
            int available = stream.available();
            byte[] a = new byte[available];
            stream.read(a);
            System.out.println(new String(a));
            stream.close();
        }
    }
}
