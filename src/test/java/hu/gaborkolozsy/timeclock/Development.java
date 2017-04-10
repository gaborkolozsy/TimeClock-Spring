/*
 * Copyright (c) 2017, Gabor Kolozsy. All rights reserved.
 */

package hu.gaborkolozsy.timeclock;

import hu.gaborkolozsy.timeclock.config.ApplicationConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * The common development annotation for test classes.
 *
 * @author Gabor Kolozsy (gabor.kolozsy.development@gmail.com)
 * @since 0.0.1-SNAPSHOT
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles
@ContextConfiguration
public @interface Development {
    
    @AliasFor(annotation = ActiveProfiles.class, attribute = "profiles")
    String[] changeProfil() default {"development"};

    @AliasFor(annotation = ContextConfiguration.class, attribute = "classes")
    Class<?>[] changeDefaultClass() default {ApplicationConfig.class};
    
    @AliasFor(annotation = ContextConfiguration.class, attribute = "loader")
    Class<? extends ContextLoader> changeDefaultLoader() default AnnotationConfigContextLoader.class;
    
}