import java.util.List;

public class Student {
    private String id;
    private String name;
    private int age;
    private String major;
    private List<Double> scores;

    public Student(String id, String name, int age, String major, List<Double> scores) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.major = major;
        this.scores = scores;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public List<Double> getScores() {
        return scores;
    }

    public double getAverageScore() {
        return scores.stream()
                .mapToDouble(Double::doubleValue)   
                .average()
                .orElse(0.0);
    }

    public double getHighestScore() {
        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setScores(List<Double> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" + "id='" + id + "'" + ", name='" + name + "'" + ", age='" + age + "'" + 
        ", major='" + major + "'" + ", scores'" + scores + "'" + '}';
    }

}
