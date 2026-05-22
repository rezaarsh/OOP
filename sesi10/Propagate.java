public class Propagate {
    public static void main(String[] args) {
        try{
            System.out.println(reverse("This is a string"));
        }
        catch(Exception e){
            System.out.println("This string was blank");
        }
        finally{
            System.out.println("All done");
        }
    }

    public static String reverse(String s) throws Exception{
        if (s.length() == 0){
            throw new Exception();
        }
        String reverseStr = "";
        for (int i=s.length()-1; i>=0; --i){
            reverseStr += s.charAt(i);
        }
        return reverseStr;
    }
}

// ketika method reverse() diberi string kosong, program akan melempar exception
// yang kemudian ditangani oleh catch, sehingga muncul pesan error