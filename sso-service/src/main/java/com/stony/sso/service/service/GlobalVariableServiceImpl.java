package com.stony.sso.service.service;

import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.*;
import com.stony.sso.service.mapper.GlobalVariableMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/22 </p>
 * <p>Time: 17:52 </p>
 * <p>Version: 1.0 </p>
 */
@Service("globalVariableService")
public class GlobalVariableServiceImpl implements GlobalVariableService {

    @javax.annotation.Resource
    GlobalVariableMapper globalVariableMapper;


    public static final String REMOVE_WATCH_KEYS = "VariableKeys";


    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_VARIABLE, type = Cachezable.CachezType.SET)
    public List<GlobalVariable> findAll() {
        return globalVariableMapper.findAll();
    }

    @Override
    @Cachezable(key = "ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public GlobalVariable createVariable(GlobalVariable globalVariable) {
        globalVariableMapper.insert(globalVariable);
        return globalVariable;
    }

    @Override
    @Cachezable(key = "#globalVariable.id,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public GlobalVariable updateVariable(GlobalVariable globalVariable) {
        globalVariableMapper.updateByPrimaryKey(globalVariable);
        return globalVariable;
    }

    @Override
    @Cachezable(key = "#id,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int delVariable(Long id) {
        return globalVariableMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Cachezable(key = "MAPALL", prefix = SecurityKeys.KEY_FIND_VARIABLE, type = Cachezable.CachezType.SET)
    public Map<String, String> getFieldVariables() {
        List<GlobalVariable> all = findAll();
        Map<String, String> result = new HashMap<>();
        for (GlobalVariable variable : all) {
            result.put(variable.getField(), variable.getValue());
        }
        return result;
    }
}
