public class Teacher extends Person {
    private int numCourses = 0;
    private String[] courses = new String[10]; // Misal maksimal mengajar 10 mata kuliah

    public Teacher(String name, String address) {
        super(name, address);
    }

    @Override
    public String toString() {
        return "Teacher: " + super.toString();
    }

    public boolean addCourse(String course) {
        // Cek apakah mata kuliah sudah ada
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                return false; // Return false jika sudah ada
            }
        }
        // Tambahkan jika belum ada
        if (numCourses < 10) {
            courses[numCourses] = course;
            numCourses++;
            return true;
        }
        return false;
    }

    public boolean removeCourse(String course) {
        int index = -1;
        // Cari index mata kuliah yang ingin dihapus
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                index = i;
                break;
            }
        }
        
        if (index == -1) {
            return false; // Return false jika tidak ditemukan
        }
        
        // Geser sisa array untuk menutupi yang dihapus
        for (int i = index; i < numCourses - 1; i++) {
            courses[i] = courses[i + 1];
        }
        numCourses--;
        return true;
    }
}