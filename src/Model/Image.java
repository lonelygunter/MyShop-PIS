package Model;

import java.awt.Toolkit;

public class Image {

    private int id;
    private byte[] image;
    private Item item;
    private int itemId;

    public Image() {
        this.image = null;
        this.item = new Item();
        this.itemId = 0;
    }

    public Image(byte[] image, Item item) {
        this.image = image;
        this.item = item;
        setItemId(item.getId());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public java.awt.Image getJavaAwtImage() {
        return Toolkit.getDefaultToolkit().createImage(getImage());
    }
}
