package com.anxin_hitsz_05.exer.exer3;

/**
 * ClassName: DivisionDemo
 * Package: com.anxin_hitsz_05.exer.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:18
 * @Version 1.0
 */
public class DivisionDemo {
    public static void main(String[] args) {

        try {
            int m = Integer.parseInt(args[0]);
            int n = Integer.parseInt(args[1]);

            int result = divide(m, n);
            System.out.println("结果为：" + result);
        } catch (BelowZeroException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("数据类型不一致");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("缺少命令行参数");
        } catch (ArithmeticException e) {
            System.out.println("除 0");
        }

    }

    public static int divide(int m, int n) throws BelowZeroException {
        if (m < 0 || n < 0) {
            // 手动抛出异常类的对象
            throw new BelowZeroException("输入负数了");
        }

        return m / n;
    }
}
