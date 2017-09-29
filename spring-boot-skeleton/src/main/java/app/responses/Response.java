package app.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Response<T> {

    @ApiModelProperty("错误信息")
    private String err;

    @ApiModelProperty("数据")
    private T data;


    public Response() {
    }

    public Response(String err) {
        this.err = err;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(String err, T data) {
        this.err = err;
        this.data = data;
    }

    public String getErr() {
        return err;
    }

    public T getData() {
        return data;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public void setData(T data) {
        this.data = data;
    }

}
