package com.shusi.modules.menu_management.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * The type Page result.
 *
 * @param <T> the type parameter
 * @author linqb
 * @date 2020 /11/24 9:15
 */
@Data
public class PageResult<T> extends BaseResult {

    /**
     * 分页数据
     */
    private Page<T> data;

    public PageResult() {
        super();
    }

    public PageResult(Page<T> data) {
        super();
        this.data = data;
    }

    /**
     * 失败
     *
     * @param msg
     * @return
     */
    public static PageResult fail(String msg) {
        PageResult pageResult = new PageResult(null);
        pageResult.setCode(BaseResult.FAIL);
        pageResult.setMessage(msg);
        return pageResult;
    }

}
