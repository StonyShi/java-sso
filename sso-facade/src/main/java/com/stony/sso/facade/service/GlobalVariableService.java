package com.stony.sso.facade.service;

import com.stony.sso.facade.entity.GlobalVariable;

import java.util.List;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/22 </p>
 * <p>Time: 17:51 </p>
 * <p>Version: 1.0 </p>
 */
public interface GlobalVariableService {

    List<GlobalVariable> findAll();

    GlobalVariable createVariable(GlobalVariable globalVariable);

    GlobalVariable updateVariable(GlobalVariable globalVariable);

    int delVariable(Long id);

    /**
     * key = field,
     * value = value
     * @return Map
     */
    Map<String,String> getFieldVariables();
}
