package com.stony.sso.facade.service;


import com.stony.sso.facade.entity.Icon;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/3 </p>
 * <p>Time: 11:49 </p>
 * <p>Version: 1.0 </p>
 */
public interface IconService {

    List<Icon> findAll();

    /**
     * 分页获取
     * @param icon
     * @return
     */
    Page<Icon> findIconList(Icon icon);
}
