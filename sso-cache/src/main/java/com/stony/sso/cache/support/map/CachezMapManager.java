package com.stony.sso.cache.support.map;

import com.stony.sso.cache.Cachez;
import com.stony.sso.cache.CachezManager;

/**
 * Created by Stony on 2016/3/11.
 */
public class CachezMapManager implements CachezManager {

    private Cachez cachez;
    private Cachez[] cachezs;
    @Override
    public Cachez getCachez() {
        if(cachez == null){
            cachez = new CachezMap();
        }
        return cachez;
    }

    @Override
    public void setCachez(Cachez cachez) {
        this.cachez = cachez;
    }
}
