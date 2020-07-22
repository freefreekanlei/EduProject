package com.kanlei.base.exceptionHandler;

import com.kanlei.commonUtils.GenericResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kanlei
 * @email freefree@qq.com
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理空指针异常
     * @param e 异常对象
     * @return 异常提示
     */
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public GenericResult handleNull(Exception e){

        e.printStackTrace();
        return GenericResult.failure().message("出现空指针异常");
    }
    /**
     * 全局异常处理
     * @param e 异常对象
     * @return 异常提示
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public GenericResult handleException(Exception e){
        log.error(e.getMessage() + "/n" +e.getCause());
        e.printStackTrace();
        return GenericResult.failure().message("出现异常啦...");
    }
}
