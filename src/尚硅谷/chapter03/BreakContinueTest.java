class BreakContinueTest {
    public static void main(String[] args) {
        // 嵌套循环中的使用
        label1: for (int j = 1; j <= 4; j++) {
            for (int i = 1; i <= 10; i++) {
                if (i % 4 == 0) {
                    // break;
                    // continue;

                    // break label1;
                    continue label1;
                }

                System.out.print(i);
            }
            System.out.println();
        }
    }
}
