import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestStudent {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList (
                new Student("S1", "Alice", 20, "CS", Arrays.asList(90.0, 85.0, 88.0)),
                new Student("S2", "Bob", 21, "CS", Arrays.asList(70.0, 75.0, 80.0)),
                new Student("S3", "Cathy", 22, "Math", Arrays.asList(95.0, 92.0, 90.0)),
                new Student("S4", "David", 20, "Math", Arrays.asList(60.0, 65.0, 70.0)),
                new Student("S5", "Emma", 23, "Physics", Arrays.asList(85.0, 87.0, 90.0)),
                new Student("S6", "Frank", 21, "Physics", Arrays.asList(50.0, 55.0, 58.0))
        );

        StudentAnalyzer analyzer = new StudentAnalyzer();

        System.out.println("Top 3 student names: ");
        List<String> topStudents = analyzer.getTopStudentNames(students, 3);
        topStudents.forEach(System.out::println);

        System.out.println("\nAverage score by major ");
        Map<String, Double> avgByMajor = analyzer.getAverageScoreByMajor(students);
        avgByMajor.forEach((major, avg) -> System.out.println(major + "->" + avg));

        System.out.println("\nStudent with highest single score ");
        Optional<Student> highestSingle = analyzer.findStudentWithHighestSingleScore(students);
        highestSingle.ifPresent(System.out::println);

        System.out.println("\nStudents above average in CS: ");
        List<Student> aboveAvgCS = analyzer.getStudentsAboveAverageInMajor(students, "CS");
        aboveAvgCS.forEach(System.out::println);

        System.out.println("\nPartition by pass/fail with passing score 75: ");
        Map<Boolean, List<Student>> partitioned = analyzer.partitionByPassFail(students, 75);
        System.out.println("Passed: ");
        partitioned.get(true).forEach(System.out::println);
        System.out.println("Failed: ");
        partitioned.get(false).forEach(System.out::println);

        System.out.println("\nOptional handling with orElse ");
        Student defaultStudent = highestSingle.orElse(
                new Student("N/A", "No student", 0, "None", Collections.emptyList())
        );
        System.out.println(defaultStudent);

        System.out.println("\nOptional handling with orElseThrow ");
        Student mustExist = highestSingle.orElseThrow(
                () -> new RuntimeException("No student found")
        );
        System.out.println(mustExist.getName());

    }
}
