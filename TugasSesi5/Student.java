public class Student extends Person {
    private int numCourses = 0;
    // Menggunakan array dengan batas maksimal (misal: 30 mata kuliah)
    private String[] courses = new String[30]; 
    private int[] grades = new int[30];

    public Student(String name, String address) {
        // super() digunakan untuk memanggil constructor dari class induk (Person)
        super(name, address); 
    }

    @Override
    public String toString() {
        return "Student: " + super.toString();
    }

    public void addCourseGrade(String course, int grade) {
        if (numCourses < 30) {
            courses[numCourses] = course;
            grades[numCourses] = grade;
            numCourses++;
        } else {
            System.out.println("Kapasitas mata kuliah penuh!");
        }
    }

    public void printGrades() {
        System.out.print("Nilai: ");
        for (int i = 0; i < numCourses; i++) {
            System.out.print(courses[i] + ":" + grades[i] + " ");
        }
        System.out.println();
    }

    public double getAverageGrade() {
        if (numCourses == 0) return 0.0;
        int sum = 0;
        for (int i = 0; i < numCourses; i++) {
            sum += grades[i];
        }
        return (double) sum / numCourses;
    }
}