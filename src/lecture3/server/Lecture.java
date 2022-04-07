package lecture3.server;

import lecture3.client.Person;

import java.io.Serializable;

public class Lecture extends Person implements Serializable{

    private int salary;

    public Lecture(int salary, int id, String name) {
        super(id, name);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    @Override
    public String toString()
    {
        return super.toString() +" "+ getSalary();
    }
    @Override
    public boolean equals(Object obj)
    {
        if ( obj instanceof Lecture)
        {
            Lecture l = (Lecture) obj;
            return l.getId() == getId();
        }
        return false;
    }
}
