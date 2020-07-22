package com.kanlei.commonUtils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果
 * @author kanlei
 * @creat 2020-07-21_9:13
 */
@Data
public class GenericResult {
    /**
     * 响应是否成功
     */
    private boolean success;
    /**
     * 自定义响应码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Map<String,Object> data = new HashMap<>();

    private GenericResult(){}

    /**
     * 成功
     * @return
     */
    public static GenericResult success(){

        GenericResult result = new GenericResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static GenericResult failure(){

        GenericResult result = new GenericResult();
        result.setSuccess(false);
        result.setCode(ResultCode.FAILURE);
        result.setMessage("失败");
        return result;
    }

    /**
     * 链式编程
     * @param success
     * @return
     */
    public GenericResult success(boolean success){
        this.setSuccess(success);
        return this;
    }
    public GenericResult message(String message){
        this.setMessage(message);
        return this;
    }
    public GenericResult code(Integer code){
        this.setCode(code);
        return this;
    }
    public GenericResult data(String key, Object value){
        this.data.put(key,value);
        return this;
    }
    public GenericResult data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
