package com.stony.sso.web.freemarker.tags;

import org.apache.shiro.subject.Subject;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.HasPermissionTag}</p>
 *
 * @since 0.1
 */
public class HasAnyPermissionTag extends PermissionTag {

    private static final String PERMISSION_NAMES_DELIMETER = ",";

    protected boolean showTagBody(String permissionNames) {
        boolean hasAnyPermission = false;
        Subject subject = getSubject();
        if(subject != null){
            for (String permission : permissionNames.split(PERMISSION_NAMES_DELIMETER)) {
                if (subject.isPermitted(permission.trim())) {
                    hasAnyPermission = true;
                    break;
                }
            }
        }
        return hasAnyPermission;
    }
}
