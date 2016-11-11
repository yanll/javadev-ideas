package com.yanll.framework.util;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.stereotype.Component;

@Component
public class UPage {

    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_LIMIT = 10;

    public static PageBounds toPageBounds(Integer page) {
        return toPageBounds(getPage(page), null);
    }

    public static PageBounds toPageBounds(Integer page, Integer limit) {
        return new PageBounds(page == null ? DEFAULT_PAGE : getPage(page), limit == null ? DEFAULT_LIMIT : getLimit(limit));
    }


    public static Integer getPage(Integer page) {
        return Math.max(page.intValue(), 1);
    }

    public static Integer getLimit(Integer limit) {
        return Math.max(limit.intValue(), 1);
    }
}
