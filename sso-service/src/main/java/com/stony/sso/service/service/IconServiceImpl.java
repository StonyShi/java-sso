package com.stony.sso.service.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.*;
import com.stony.sso.service.mapper.IconMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/3 </p>
 * <p>Time: 11:50 </p>
 * <p>Version: 1.0 </p>
 */
@Service("iconService")
public class IconServiceImpl implements IconService {

    @javax.annotation.Resource
    IconMapper iconMapper;

    @Override
//    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_ICON, type = Cachezable.CachezType.SET)
    public List<Icon> findAll() {
        return iconMapper.findAll();
    }

    @Override
    public Page<Icon> findIconList(Icon icon){
        PageHelper.startPage(icon.getPageNum(), icon.getPageSize());
        return iconMapper.findAll();
    }


}
