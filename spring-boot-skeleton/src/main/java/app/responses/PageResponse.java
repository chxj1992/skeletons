package app.responses;

import org.springframework.data.domain.Page;

public class PageResponse<T> extends Response<ListData<T>> {

    public PageResponse(Page<T> page) {
        ListData<T> data = new ListData<>(page.getContent(), page.getTotalElements());
        this.setData(data);
    }

}
