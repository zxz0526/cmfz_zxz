package com.baizhi.entity;

import java.util.Date;

public class User {

    private String id;
    private String img;
    private String phoneNum;
    private String username;
    private String password;
    private String nickName;
    private String name;
    private String sex;
    private String addr;
    private String sign;
    private String stauts;
    private Date createDate;
    private String salt;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", addr='" + addr + '\'' +
                ", sign='" + sign + '\'' +
                ", stauts='" + stauts + '\'' +
                ", createDate=" + createDate +
                ", salt='" + salt + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User(String id, String img, String phoneNum, String username, String password, String nickName, String name, String sex, String addr, String sign, String stauts, Date createDate, String salt) {
        this.id = id;
        this.img = img;
        this.phoneNum = phoneNum;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.name = name;
        this.sex = sex;
        this.addr = addr;
        this.sign = sign;
        this.stauts = stauts;
        this.createDate = createDate;
        this.salt = salt;
    }

    public User() {
    }
}
