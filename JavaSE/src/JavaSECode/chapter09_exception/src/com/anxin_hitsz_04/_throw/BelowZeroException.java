package com.anxin_hitsz_04._throw;

/**
 * ClassName: BelowZeroException
 * Package: com.anxin_hitsz_04._throw
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 17:51
 * @Version 1.0
 */
public class BelowZeroException extends Exception {
    static final long serialVersionUID = -3387516999948L;

    public BelowZeroException() {

    }

    public BelowZeroException(String name) {
        super(name);
    }

    public BelowZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
