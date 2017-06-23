package com.stony.sso.commons;

import org.springframework.util.Assert;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/26 </p>
 * <p>Time: 14:43 </p>
 * <p>Version: 1.0 </p>
 */
public class MapPlaceholderHelper extends PropertyPlaceholderHelper {
    /**
     * 强制替换为空值
     */
    private boolean forcePlace = false;
    private String prefix;
    public MapPlaceholderHelper(String placeholderPrefix, String placeholderSuffix) {
        super(placeholderPrefix, placeholderSuffix);
        this.prefix = placeholderPrefix;
    }
    public MapPlaceholderHelper(String placeholderPrefix, String placeholderSuffix, boolean forcePlace) {
        super(placeholderPrefix, placeholderSuffix);
        this.forcePlace = forcePlace;
        this.prefix = placeholderPrefix;
    }

    public MapPlaceholderHelper(String placeholderPrefix, String placeholderSuffix, String valueSeparator, boolean ignoreUnresolvablePlaceholders, boolean forcePlace) {
        super(placeholderPrefix, placeholderSuffix, valueSeparator, ignoreUnresolvablePlaceholders);
        this.forcePlace = forcePlace;
        this.prefix = placeholderPrefix;
    }

    /**
     * 如果不包prefix，直接返回 value
     * @param value
     * @param maps
     * @return
     */
    public String replacePlaceholders(String value, final Map<String,String> maps) {
        if(StringUtils.isEmpty(value)){
            return value;
        }
        if(!value.contains(this.prefix)){
            return value;
        }
        Assert.notNull(maps, "Argument 'maps' must not be null.");
        return replacePlaceholders(value, new PlaceholderResolver() {
            public String resolvePlaceholder(String placeholderName) {
                if(forcePlace && (null == maps.get(placeholderName))) return "";
                return maps.get(placeholderName);
            }
        });
    }
}
