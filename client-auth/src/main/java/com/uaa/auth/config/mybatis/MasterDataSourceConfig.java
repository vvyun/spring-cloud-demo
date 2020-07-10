package com.uaa.auth.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.uaa.auth.dao.typehandler.UuidTypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

//import org.apache.log4j.Logger;

/**
 * @author wyf
 * @Date 2018-12-12.16:42
 */
@Configuration
@PropertySource("classpath:properties/dbconfig1.properties")
public class MasterDataSourceConfig {

//    private Logger logger = Logger.getLogger(MasterDataSourceConfig.class);

    private static final Logger logger = LoggerFactory.getLogger(UuidTypeHandler.class);


    @Value("${master.datasource.url}")
    private String dbUrl;

    @Value("${master.datasource.username}")
    private String username;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${master.datasource.initialSize}")
    private int initialSize;

    @Value("${master.datasource.minIdle}")
    private int minIdle;

    @Value("${master.datasource.maxActive}")
    private int maxActive;

    @Value("${master.datasource.maxWait}")
    private int maxWait;

    @Value("${master.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${master.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${master.datasource.validationQuery}")
    private String validationQuery;

    @Value("${master.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${master.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${master.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${master.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${master.datasource.filters}")
    private String filters;

    @Value("${master.datasource.maxOpenPreparedStatements}")
    private int maxOpenPreparedStatements;

    @Value("${master.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${master.datasource.type}")
    private String dbType;

    @Value("${master.datasource.publicKey}")
    private String publicKey;

    @Value("${master.datasource.passwordCallback}")
    private String passwordCallbackClassName;

    @Value("${master.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean
    public ServletRegistrationBean masterDruidServlet() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean masterFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }

    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setUrl(this.dbUrl);
        masterDataSource.setUsername(username);
        masterDataSource.setPassword(password);
        masterDataSource.setDriverClassName(driverClassName);
        masterDataSource.setInitialSize(initialSize);
        masterDataSource.setMinIdle(minIdle);
        masterDataSource.setMaxActive(maxActive);
        masterDataSource.setMaxWait(maxWait);
        masterDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        masterDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        masterDataSource.setValidationQuery(validationQuery);
        masterDataSource.setTestWhileIdle(testWhileIdle);
        masterDataSource.setTestOnBorrow(testOnBorrow);
        masterDataSource.setTestOnReturn(testOnReturn);
        masterDataSource.setPoolPreparedStatements(poolPreparedStatements);
        masterDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        masterDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        masterDataSource.setConnectionProperties(connectionProperties);
        /*try
        {
            datasource.setPasswordCallbackClassName(passwordCallbackClassName);
        }
        catch (Exception e)
        {
            logger.error("druid configuration initialization passwordCallbackClassName", e);
        }*/
        try {
            masterDataSource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return masterDataSource;
    }
}
