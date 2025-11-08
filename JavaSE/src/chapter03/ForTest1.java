class ForTest1 {
    public static void main(String[] args) {
        int m = 12;
        int n = 20;

        // 需求 1：最大公约数
        // 获取 m 和 n 中的较小值
        int min = (m < n) ? m : n;
        // 方式 1：
        int result = 1;
        for (int i = 1; i <= min; i++) {
            if (m % i == 0 && n % i == 0) {
                // System.out.println(i);
                result = i;
            }
        }

        System.out.println(result);

        // 方式 2（推荐）：
        for (int i = min; i >= 1; i--) {
            if (m % i == 0 && n % i == 0) {
                System.out.println(i);
                break;  // 一旦执行，就跳出当前循环结构
            }
        }

        // 需求 2：最小公倍数
        // 获取 m 和 n 中的较大值
        int max = (m > n) ? m : n;
        for (int i = max; i <= m * n; i++) {
            if (i % m == 0 && i % n == 0) {
                System.out.println(i);
                break;
            }
        }
    }
}