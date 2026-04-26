public class MahasiswaKu {

    static class Mahasiswa<T1, T2, T3> {
        private T1 Nim;
        private T2 Name;
        private T3 Class;

        public void setNim(T1 InputNim) {
            this.Nim = InputNim;
        }

        public void setName(T2 InputName) {
            this.Name = InputName;
        }

        public void setClass(T3 InputClass) {
            this.Class = InputClass;
        }

        public T1 getNim() {
            return Nim;
        }

        public T2 getName() {
            return Name;
        }

        public T3 getClas() {
            return Class;
        }
    }

    public static void main(String[] args) {
        Mahasiswa<String, String, Integer> m = new Mahasiswa<>();
        m.setNim("1102020");
        m.setName("Ferdi");
        m.setClass(21);

        System.out.println(m.getNim());
        System.out.println(m.getName());
        System.out.println(m.getClas());
    }
}