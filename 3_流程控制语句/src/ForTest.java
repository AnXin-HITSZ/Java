class ForTest {
    public static void main(String[] args) {
        int count = 0;  // 记录偶数的个数
        int sum = 0;    // 记录所有偶数的和
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                count++;
                sum += i;
            }
        }

        System.out.println("偶数的个数为：" + count);
        System.out.println("偶数的总和为：" + sum);
    }
}