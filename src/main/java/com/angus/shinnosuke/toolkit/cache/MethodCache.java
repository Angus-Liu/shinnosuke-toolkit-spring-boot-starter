package com.angus.shinnosuke.toolkit.cache;

/**
 * // TODO: angus on 2021/2/26 SpringBoot Cache 很强大，要不要在它的基础上二次开发？
 */
public @interface MethodCache {
    String prefix() default "";

    Class<? extends PrefixProvider> prefixProducer();

    KeyType key() default KeyType.METHOD_PARAM_NAME;

    Class<? extends KeyProvider> keyProducer();

    CacheScope score() default CacheScope.THREAD;

    long expire() default 0;
}
