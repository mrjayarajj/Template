package dto;

import java.util.Set;

import org.codehaus.jackson.annotate.JsonBackReference;

public class Department {

	@JsonBackReference
    private Set employee;

    private int id;

    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set getEmployee() {        
        return employee;
    }

    public void setEmployee(Set employee) {
        this.employee = employee;
    }

    public String toString() {
        if (this.employee == null) {
            return this.id + "-" + this.name + "" + null + "" + null;
        } else {
            return this.id + "-" + this.name + "" + this.employee;
        }
    }
}
