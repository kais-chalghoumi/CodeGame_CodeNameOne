package tn.esprit.codegame.entities;

public class Game {

    public String getUrl() {
        return url;
    }

    private String name, img,Description,url,price;

    public Game(String name, String Description, String url,String img, String price) {
        this.name = name;
        this.Description = Description;
        this.url = url;
        this.price = price;
        this.img = img;
    }

    public Game() {
    }

    public Game(String name, String price, String img) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public Game(String name, String price,String img, String Description ) {
        this.name = name;
        this.img = img;
        this.Description = Description;
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game{" + "name=" + name + ", img=" + img + ", price=" + price + '}';
    }

}
