package com.test.spring.demo.entity;

public class User {
    //会员卡编号
    private String id;
    //性别
    private String gender;
    //年龄
    private int age;
    //飞行次数
    private int flyTimes;
    //最后一次乘机距离统计截止日期时间
    private int lastToEnd;
    //平均折扣
    private double averageDiscount;
    //飞行公里数
    private int distance;
    //精英积分数
    private int elitePoint;
    //入会时长
    private int days;

    public User() {
    }

    public User(String id, int flyTimes, int lastToEnd, double averageDiscount, int distance, int elitePoint, int days) {
        this.id = id;
        this.flyTimes = flyTimes;
        this.lastToEnd = lastToEnd;
        this.averageDiscount = averageDiscount;
        this.distance = distance;
        this.elitePoint = elitePoint;
        this.days = days;
    }

    public User(String id, String gender, int age, int flyTimes, int lastToEnd, double averageDiscount, int distance, int elitePoint, int days) {
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.flyTimes = flyTimes;
        this.lastToEnd = lastToEnd;
        this.averageDiscount = averageDiscount;
        this.distance = distance;
        this.elitePoint = elitePoint;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFlyTimes() {
        return flyTimes;
    }

    public void setFlyTimes(int flyTimes) {
        this.flyTimes = flyTimes;
    }

    public int getLastToEnd() {
        return lastToEnd;
    }

    public void setLastToEnd(int lastToEnd) {
        this.lastToEnd = lastToEnd;
    }

    public double getAverageDiscount() {
        return averageDiscount;
    }

    public void setAverageDiscount(double averageDiscount) {
        this.averageDiscount = averageDiscount;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getElitePoint() {
        return elitePoint;
    }

    public void setElitePoint(int elitePoint) {
        this.elitePoint = elitePoint;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "\t{\n" +
                "\t   \"id\":\"" + id + "\",\n" +
                "\t   \"gender\":\"" + gender + "\",\n" +
                "\t   \"age\":" + age + ",\n" +
                "\t   \"flyTimes\":" + flyTimes + ",\n" +
                "\t   \"lastToEnd\":" + lastToEnd + ",\n" +
                "\t   \"averageDiscount\":" + averageDiscount + ",\n" +
                "\t   \"distance\":" + distance + ",\n" +
                "\t   \"elitePoint\":" + elitePoint + ",\n" +
                "\t   \"days\":" + days+ "\n"
                +"\t}";
    }

    public String toString1() {
        return "\t   {\n" +
                "\t      \"id\":\"" + id + "\",\n" +
                "\t      \"gender\":\"" + gender + "\",\n" +
                "\t      \"age\":" + age + ",\n" +
                "\t      \"flyTimes\":" + flyTimes + ",\n" +
                "\t      \"lastToEnd\":" + lastToEnd + ",\n" +
                "\t      \"averageDiscount\":" + averageDiscount + ",\n" +
                "\t      \"distance\":" + distance + ",\n" +
                "\t      \"elitePoint\":" + elitePoint + ",\n" +
                "\t      \"days\":" + days+ "\n"
                +"\t   }";
    }
}
