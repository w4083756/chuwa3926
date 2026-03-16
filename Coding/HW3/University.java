package HW3;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private List<Department> listOfDepartments;
    private List<Professor> listOfProfessors;

    public University (String name) {
        this.name = name;
        this.listOfProfessors = new ArrayList<>();
        this.listOfDepartments = new ArrayList<>();
        listOfDepartments.add(new Department("CS", "Cory"));
        listOfDepartments.add(new Department("DS", "Brown Hall"));
        listOfDepartments.add(new Department("Math", "Soda"));
    }

    public void addProfessor(Professor p) {
        if (!listOfProfessors.contains(p)) {
            listOfProfessors.add(p);
        }
    }
    public void listProfessors() {
        for (Professor p : listOfProfessors) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        University u = new University("UC Berkeley");
        Professor p1 = new Professor("Denero", "CS61A");
        Professor p2 = new Professor("Josh", "CS61B");

        u.addProfessor(p1);
        u.addProfessor(p2);

        System.out.println("Professors in university:");
        u.listProfessors();

        u = null;
        
        System.out.println("Professors still exist after university is null:");
        System.out.println(p1);
        System.out.println(p2);
    }
    
}

class Department {
    private String name;
    private String building;

    public Department(String name, String building) {
        this.name = name;
        this.building = building;
    }

    @Override
    public String toString() {
        return  "Department{name='" + name + "', building= '" + building + "'}";
    }
}

class Professor {
    private String name;
    private String specialization;

    public Professor (String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Professor{name='" + name + "', specialization='" + specialization +"'}";
    }
    
}


