package com.uaa.auth.config.mybatis;

import com.uaa.auth.utils.StringUtil;

/**
 * @author wyf
 */
public class CustomerContextHolder {

    /**
     * 默认数据源
     */
    public static String defaultDataSource = "masterDataSource";
    /**
     * 目标数据源
     */
    public static String targetDataSource = "slaveDataSource";

    private static final ThreadLocal<String> contentHolder = new ThreadLocal<String>();

    public static void setCustomerType(String customerType) {
        contentHolder.set(customerType);
    }

    public static String getCustomerType() {
        String source = contentHolder.get();
        if (StringUtil.isBlank(source)) {
            return defaultDataSource;
        } else {
            return targetDataSource;
        }
    }

    public static void clearCustomerType() {
        contentHolder.remove();
    }

    public static String getDefaultDataSource() {
        return defaultDataSource;
    }

    public static void setDefaultDataSource(String defaultDataSource) {
        CustomerContextHolder.defaultDataSource = defaultDataSource;
    }

    public static String getTargetDataSource() {
        return targetDataSource;
    }

    public static void setTargetDataSource(String targetDataSource) {
        CustomerContextHolder.targetDataSource = targetDataSource;
    }

}
