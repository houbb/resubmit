package com.github.houbb.resubmit.api.exception;

/**
 * 运行时异常
 * @author binbin.hou
 * @since 0.0.1
 */
public class ResubmitException extends RuntimeException {

    public ResubmitException() {
    }

    public ResubmitException(String message) {
        super(message);
    }

    public ResubmitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResubmitException(Throwable cause) {
        super(cause);
    }

    public ResubmitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
