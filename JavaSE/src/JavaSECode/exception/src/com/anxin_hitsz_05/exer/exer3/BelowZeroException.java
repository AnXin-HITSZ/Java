package com.anxin_hitsz_05.exer.exer3;

/**
 * ClassName: BelowZeroException
 * Package: com.anxin_hitsz_05.exer.exer3
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:21
 * @Version 1.0
 */
public class BelowZeroException extends Exception {
    static final long serialVersionUID = -33875169939948L;

    public BelowZeroException() {
    }

    public BelowZeroException(String message) {
        super(message);
    }
}
