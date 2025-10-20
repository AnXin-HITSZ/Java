class PrimeNumberTest {
    public static void main(String[] args) {
        boolean isFlag = true;
        
        for (int i = 2; i <= 100; i++) {    // 遍历 100 以内的自然数
            // 判定 i 是否是质数
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isFlag = false;
                }
            }

            if (isFlag) {//if (isFlag == true) {
                System.out.println(i);
            }

            // 重置 isFlag
            isFlag = true;
        }
    }
}
