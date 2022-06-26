package com.example.savitrijewellersadmin.order;

public class Jewellery_item {
    private String name;
    private String img0;
    private String img1;
    private String img2;
    private Integer goldprice;
    private Integer makingcharge;
    private Integer discount;
    private String type;
    private String purity;
    private Double weight;
    private String category;
    private String itemid;

    public String getImg0() {
        return img0;
    }

    public void setImg0(String img0) {
        this.img0 = img0;
    }

    public Jewellery_item() {
        // empty constructor required for firebase.
    }

    public Jewellery_item(String name, String img0, String img2, String img3, Integer goldprice, Integer makingcharge, Integer discount, String type, String purity, Double weight, String category, String itemid) {
        this.name = name;
        this.img0 = img0;
        this.img1 = img1;
        this.img2 = img2;
        this.goldprice = goldprice;
        this.makingcharge = makingcharge;
        this.discount = discount;
        this.type = type;
        this.purity = purity;
        this.weight = weight;
        this.category = category;
        this.itemid =itemid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }



    public Integer getGoldprice() {
        return goldprice;
    }

    public void setGoldprice(Integer goldprice) {
        this.goldprice = goldprice;
    }

    public Integer getMakingcharge() {
        return makingcharge;
    }

    public void setMakingcharge(Integer makingcharge) {
        this.makingcharge = makingcharge;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
