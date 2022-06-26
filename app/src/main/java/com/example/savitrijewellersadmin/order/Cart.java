package com.example.savitrijewellersadmin.order;

public class Cart {
    private String name;
    private String image;
    private String purity;
    private String type;
    private Double weight;
    private Integer makingcharge;
    private Integer discount;
    private String uid;
    private String itemid;
    private Integer totalprice;
    private String cartitemid;
    private Integer itemprice;

    public Integer getItemprice() {
        return itemprice;
    }

    public void setItemprice(Integer itemprice) {
        this.itemprice = itemprice;
    }

    public Integer getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }

    public String getCartitemid() {
        return cartitemid;
    }

    public void setCartitemid(String cartitemid) {
        this.cartitemid = cartitemid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public Cart() {
    }

    public Cart(String name, String image, String purity, String type, Double weight, Integer makingcharge, Integer discount, String uid, String itemid, Integer totalprice, String cartitemid, Integer itemprice) {
        this.name = name;
        this.image = image;
        this.purity = purity;
        this.type = type;
        this.weight = weight;
        this.makingcharge = makingcharge;
        this.discount = discount;
        this.uid = uid;
        this.itemid=itemid;
        this.totalprice=totalprice;
        this.cartitemid=cartitemid;
        this.itemprice=itemprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
