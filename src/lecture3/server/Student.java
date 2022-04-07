package lecture3.server;

import lecture3.client.Person;

import java.io.Serializable;

public class Student extends Person implements Serializable{


    private int age;

    public Student(int id, String name, int age) {

        super(id, name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return super.toString() +" "+ getAge();
    }
    @Override
    public boolean equals(Object obj)
    {
        if ( obj instanceof Student)
        {
            Student l = (Student) obj;
            return l.getId() == getId();
        }
        return false;
    }


}
