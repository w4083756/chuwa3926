import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentAnalyzer {
    public List<String> getTopStudentNames(List<Student> students, int n) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverageScore).reversed())
                .limit(n)
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    public Map<String, Double> getAverageScoreByMajor(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(
                        Student::getMajor,
                        Collectors.averagingDouble(Student::getAverageScore)
                ));
    }

    public Optional<Student> findStudentWithHighestSingleScore(List<Student> students) {
        return students.stream()
                .max(Comparator.comparingDouble(Student::getHighestScore));
    }

    public List<Student> getStudentsAboveAverageInMajor(List<Student> students, String major) {
        double majorAverage =  students.stream()
                .filter(student -> student.getMajor().equals(major))
                .mapToDouble(Student::getAverageScore)
                .average()
                .orElse(0.0);
        
        return students.stream()
                .filter(student -> student.getMajor().equals(major))
                .filter(student -> student.getAverageScore() > majorAverage)
                .collect(Collectors.toList());
    }

    public Map<Boolean, List<Student>> partitionByPassFail(List<Student> students, double passingScore) {
        return students.stream()
                .collect(Collectors.partitioningBy(
                        student -> student.getAverageScore() >= passingScore
                ));
    }
}
