public class Student {
    private String name;
    private int age;
    private double grade;

    public Student (String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age >= 1 && age <= 150) {
            this.age = age;
        }
    }
    
    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.00) {
            this.grade = grade;
        }
    }

    public static void main (String[] args) {
    Student a = new Student("andy", 20, 80);
    a.setName("Andy");
    a.setAge(30);
    a.setGrade(100);
    System.out.println(a.getName());
    System.out.println(a.getAge());
    System.out.println(a.getGrade());
}
}



