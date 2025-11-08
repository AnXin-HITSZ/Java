class PrimeNumberTest2 {
    public static void main(String[] args) {
        // 获取系统当前的时间
        long start = System.currentTimeMillis();
        boolean isFlag = true;

        int count = 0;  // 记录质数的个数
        
        for (int i = 2; i <= 100000; i++) {    // 遍历 100000 以内的自然数
            // 判定 i 是否是质数
            // for (int j = 2; j < i; j++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isFlag = false;
                    break;  // 针对于非质数有效果
                }
            }

            if (isFlag) {//if (isFlag == true) {
                count++;
            }

            // 重置 isFlag
            isFlag = true;
        }

        // 获取系统当前的时间
        long end = System.currentTimeMillis();

        System.out.println("质数的总个数为：" + count); // 9592
        System.out.println("花费的时间为：" + (end - start));   // 6004 -> 加上 break：548 -> 加上 Math.sqrt()：5
    }
}
