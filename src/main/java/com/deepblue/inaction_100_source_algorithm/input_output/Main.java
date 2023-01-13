package com.deepblue.inaction_100_source_algorithm.input_output;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 */
public class Main {



    public static void main(String[] args) throws IOException {

//        readLineFile();

//        readBuffFile();

//        readZipFile();

    }


    public static void readLineFile() throws IOException {

        String filename = "/Users/yangjianwei/IdeaProjects/java_springboot/src/main/java/com/deepblue/inaction_100_source_algorithm/read_write/inputfile.txt";
        File inputFile = new File(filename);

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        while (true) {
            String line = reader.readLine();

            if(line == null) {
                break;
            }

            System.out.println("line = " + line);
        }

    }

    public static void readBuffFile() throws IOException {

        String filename = "/Users/yangjianwei/IdeaProjects/java_springboot/src/main/java/com/deepblue/inaction_100_source_algorithm/read_write/inputfile.txt";
        char[] buffer = new char[1024];

        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename),"UTF-8");

        int count;

        while ((count = reader.read(buffer)) != -1) {
            String chars = new String(buffer, 0, count);
            System.out.println("count = " + count);
            System.out.println("chars = " + chars);
            System.out.println("----------------------------");
        }

    }


    public static void readZipFile() throws IOException {
        String filename = "/Users/yangjianwei/IdeaProjects/java_springboot/src/main/java/com/deepblue/inaction_100_source_algorithm/input_output/input.zip";
        ZipFile zipFile = new ZipFile(filename);

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if(entry.getName().startsWith("__MACOSX")) {
                continue;
            } else {
                System.out.println("read zip file method invoke, entry file name = " + entry.getName());
            }

            InputStream is = zipFile.getInputStream(entry);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader reader = new BufferedReader(isr);

            while (true) {
                String line = reader.readLine();

                if(line == null) {
                    break;
                }

                System.out.println("line = " + line);
            }
        }

    }






}


