class DoWhileTest {
    public static void main(String[] args) {
        int num1 = 10;
        while (num1 > 10) {
            System.out.println("while: hello");
            num1--;
        }

        int num2 = 10;
        do {
            System.out.println("do - while: hello");
            num2--;
        } while (num2 > 10);
    }
}