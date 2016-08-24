package com.zheng.demotest.quickquery;

/**
 * Created by Administrator on 2016/8/23.
 */
public class Person {

    private String name;
    private String telephone;
    private String pinyin;

    public Person(String name,String telephone) {
        this.name = name;
        this.telephone = telephone;
        this.pinyin = PinYinUtils.getPinYin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}
