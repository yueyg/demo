package com.yyg.cn.base;

public class Result {
    // 结果标记(true:执行成功 false:执行失败)
    private Boolean flag;
    // 消息状态码
    private Integer code;
    // 消息
    private String msg;
    // 返回数据
    private Object data;

    public Result(Boolean flag, Integer code, String msg, Object data) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应成功
     * @param data 返回数据
     * @return Result
     */
    public static Result success(Object data){
        return new Result(true,2000,"响应成功",data);
    }
    /**
     * 响应成功
     * @return Result
     */
    public static Result success(String msg){
        return new Result(true,2000,msg,null);
    }
    /**
     * 响应成功
     * @return Result
     */
    public static Result success(){
        return new Result(true,2000,"响应成功",null);
    }

    /**
     * 响应失败
     * @return Result
     */
    public static Result error(String msg){
        return new Result(false,4000,msg,null);
    }
    /**
     * 响应失败
     * @return Result
     */
    public static Result error(Integer code,String msg){
        return new Result(false,code,msg,null);
    }
    /**
     * 响应失败
     * @return Result
     */
    public static Result error(){
        return new Result(false,4000,"响应成功",null);
    }

}
