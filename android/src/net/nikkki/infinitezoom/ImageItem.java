package net.nikkki.infinitezoom;

public class ImageItem {
    private int image;
    private String title;
    private int index;
 
    public ImageItem(int image, String title, int index) {
        super();
        this.image = image;
        this.title = title;
        this.index = index;
    }
 
    public int getImage() {
        return image;
    }
 
    public void setImage(int image) {
        this.image = image;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
}