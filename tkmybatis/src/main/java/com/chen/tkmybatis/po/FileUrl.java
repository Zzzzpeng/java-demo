package com.chen.tkmybatis.po;

public class FileUrl {
    Integer id;
    String name;
    String url;
    String catalog;
    Integer unit;
    String unitName;
    String fileSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "FileUrl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", catalog='" + catalog + '\'' +
                ", unit=" + unit +
                ", unitName='" + unitName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String abc = "acb";
        System.out.println(abc.toString()+"ccc");
    }
}
