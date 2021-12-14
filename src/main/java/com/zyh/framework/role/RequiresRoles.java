package com.zyh.framework.role;

import java.lang.annotation.*;

/**
 * @author
 * @date 2021/3/26 15:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresRoles {

    Role type();
}
