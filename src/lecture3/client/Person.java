package lecture3.client;

import java.io.Serializable;

public abstract class Person implements Serializable {

    private int id;
    private String name;

    public Person(int id, String name) {

        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){

        return getName() +" " + getId()+"\n";
    }

}
