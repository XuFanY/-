package com.test.spring.demo.Json;

import com.test.spring.demo.entity.Cluster;
import com.test.spring.demo.entity.User;

import java.io.*;
import java.util.*;

public class JsonData {

    private static String filePath;
    private static String jsonFilePath;

    private static String id;
    private static String gender;
    private static int age;
    private static int flyTimes;
    private static int lastToEnd;
    private static double averageDiscount;
    private static int distance;
    private static int elitePoint;
    private static int days;
    private static List<User> users;

    private static Reader fr=null;
    private static Writer fw=null;
    private static BufferedReader br=null;
    private static PrintWriter pw=null;
    private static String line;

    static{
        filePath="C:/Users/fanyu/Desktop/";
        jsonFilePath="C:/Users/fanyu/Desktop/";
        id="";
        gender="";
        age=0;
        flyTimes=0;
        lastToEnd=0;
        averageDiscount=0.0;
        distance=0;
        elitePoint=0;
        days=0;
        users=new ArrayList<User>();
    }

    public static class JavaData{
        public void collect() throws IOException {

            filePath+=File.separator+"cleanedData.csv";
            fr=new FileReader(filePath);
            br=new BufferedReader(fr);
            br.readLine();

            while((line=br.readLine())!=null){
                String[] fields=line.split(",");
                id=fields[0];
                gender=fields[2];
                if (!fields[3].equals("")){
                    age=Integer.parseInt(fields[3]);
                }
                flyTimes=Integer.parseInt(fields[4]);
                lastToEnd=Integer.parseInt(fields[5]);
                averageDiscount=Double.parseDouble(fields[6]);
                distance=Integer.parseInt(fields[7]);
                elitePoint=Integer.parseInt(fields[8]);
                days=Integer.parseInt(fields[9]);
                User user=new User(id,gender,age,flyTimes,lastToEnd,averageDiscount,distance,elitePoint,days);
                users.add(user);
            }
        }

        public void initial(int type) throws IOException {
            if(type==0){
                jsonFilePath+=File.separator+"JsonDataAll(java).json";
            }
            else {
                filePath="C:/Users/fanyu/Desktop/"+File.separator+"cluster.csv";
                jsonFilePath+=File.separator+"JsonClusterData.json";
                fr=new FileReader(filePath);
                br=new BufferedReader(fr);
            }
            fw=new FileWriter(jsonFilePath);
            pw=new PrintWriter(fw);
            pw.println("[");
            pw.flush();

            filePath="C:/Users/fanyu/Desktop/";
            jsonFilePath="C:/Users/fanyu/Desktop/";

        }

        public void end() throws IOException {
            pw.println("]");
            pw.flush();
            fr.close();
            br.close();
            fw.close();
            pw.close();
        }

        //将所有信息写成json文件
        public void JsonDataAll() throws IOException {
            collect();

            initial(0);

            int length=users.size()-1;
            for(int i=0;i<length;i++){
                pw.println(users.get(0).toString()+',');
                pw.flush();
                users.remove(0);
            }
            pw.println(users.get(0).toString());

            end();
        }

        //将聚类信息写成json文件
        public void JsonClusterData() throws IOException {
            collect();

            initial(1);

            int category;
            List<User> kusers=new ArrayList<User>();
            List<Double> kmeans=new ArrayList<Double>(6);
            List<Cluster> clusters=new ArrayList<Cluster>();
            int sum;
            while((line=br.readLine())!=null){
                String[] fields=line.split(",");
                category=Integer.parseInt(fields[0]);
                int i;
                for(i=2;i<8;i++){
                    kmeans.add(Double.valueOf(fields[i]));
                }
                sum=Integer.parseInt(fields[i]);
                i++;
                while(i<fields.length){
                    id=fields[i];
                    i+=7;
                    Iterator<User> iterator=users.iterator();
                    while (iterator.hasNext()){
                        User user=iterator.next();
                        if(id.equals(user.getId())){
                            kusers.add(user);
                            iterator.remove();
                            break;
                        }
                    }
                }
                Cluster cluster=new Cluster(category,kusers,kmeans,sum);
                clusters.add(cluster);
                kusers=new ArrayList<User>();
                kmeans=new ArrayList<Double>(6);
            }

            int length=clusters.size()-1;
            for(int i=0;i<length;i++){
                pw.println(clusters.get(0).toString()+",");
                pw.flush();
                clusters.remove(0);
            }
            pw.println(clusters.get(0).toString());

            end();
        }

    }

    public static class PythonData{

    }

    public static void main(String[] args) throws IOException {
        JavaData javaData=new JavaData();
        javaData.JsonDataAll();
        System.out.println("end1!");
        javaData.JsonClusterData();
        System.out.println("end2!");
    }
}