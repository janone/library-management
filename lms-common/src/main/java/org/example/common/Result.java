package org.example.common;

public class Result<T> {
    private Integer code;

    private String msg;

    private T data;

    public Result(T data){
        this.data = data;
    }
    public Result(){
    }

    public static <V> Result<V> success(){
        return Result.success(Constants.SUCCESS_CODE,Constants.DEFAULE_SUCCESS_MESSAGE,null);
    }

    public static <V> Result<V> successWithData(V v){
        return Result.success(Constants.SUCCESS_CODE,Constants.DEFAULE_SUCCESS_MESSAGE,v);
    }

    public static <V> Result<V> success(Integer errcode, String msg, V data){
        Result<V> result = new Result<>();
        result.setCode(errcode);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <V> Result<V> fail(Integer errcode, String msg, V data){
        Result<V> result = new Result<>();
        result.setCode(errcode);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <V> Result<V> fail(Integer errcode, String msg){
        return Result.fail(errcode,msg, null);
    }
    public static <V> Result<V> fail(){
        return Result.fail(Constants.FAIL_CODE,Constants.DEFAULE_FAIL_MESSAGE, null);
    }
    public static <V> Result<V> fail(String message){
        return Result.fail(Constants.FAIL_CODE,message, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess(){
        return this.code == Constants.SUCCESS_CODE;
    }
}
