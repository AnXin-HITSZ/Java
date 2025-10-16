class WhileTest {
    public static void main(String[] args) {
        int j = 1;
        
        int count = 0;  // 记录偶数的个数
        int sum = 0;    // 记录偶数的总和
        while (j <= 100) {
            if (j % 2 == 0) {
                System.out.println(j);
                count++;
                sum += j;
            }
            j++;
        }

        System.out.println("偶数的个数为：" + count);
        System.out.println("偶数的总和为：" + sum);
    }
}