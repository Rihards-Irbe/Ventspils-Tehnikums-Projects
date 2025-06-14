package model;

public class Item {

    private String name;
    private String size;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Item(String name, String size, int value) {
        this.name = name;
        this.size = size;
        this.value = value;
    }

}
