package com.test.spring.demo.entity;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private int category;
    private List<User> kusers;
    private List<Double> kmeans=new ArrayList<Double>(6);
    private int sum;

    public Cluster() {
    }

    public Cluster(int category, List<User> kusers, List<Double> kmeans, int sum) {
        this.category = category;
        this.kusers = kusers;
        this.kmeans = kmeans;
        this.sum = sum;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<User> getUsers() {
        return kusers;
    }

    public void setUsers(List<User> kusers) {
        this.kusers = kusers;
    }

    public List<Double> getKmeans() {
        return kmeans;
    }

    public void setKmeans(List<Double> kmeans) {
        this.kmeans = kmeans;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        String str="[\n"+kusers.get(0).toString1();
        int length=kusers.size();
        for (int i=1;i<length;i++){
            str+=",\n"+kusers.get(i).toString1();
        }
        str+="]\n";
        return "\t{\n" +
                "\t   \"category\":" + category + ",\n" +
                "\t   \"sum\":" + sum + ",\n" +
                "\t   \"kmeans\":" +kmeans+",\n" +
                "\t   \"kusers\":" +
                str
                +"\t}";
    }
}
