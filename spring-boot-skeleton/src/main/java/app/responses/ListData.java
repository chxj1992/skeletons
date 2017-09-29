package app.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class ListData<T> {

    @ApiModelProperty("数据列表")
    private List<T> list;

    @ApiModelProperty("数据总量")
    private Long total;

    ListData(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public Long getTotal() {
        return total;
    }
}