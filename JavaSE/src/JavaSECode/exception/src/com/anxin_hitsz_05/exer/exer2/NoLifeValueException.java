package com.anxin_hitsz_05.exer.exer2;

/**
 * ClassName: NoLifeValueException
 * Package: com.anxin_hitsz_05.exer.exer2
 * Description:
 *
 * @Author AnXin
 * @Create 2026/2/5 18:08
 * @Version 1.0
 */
public class NoLifeValueException extends RuntimeException {

    static final long serialVersionUID = -7034897190939L;

    public NoLifeValueException() {

    }

    public NoLifeValueException(String message) {
        super(message);
    }
}
