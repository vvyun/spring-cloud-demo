package com.uaa.auth.config.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.interceptor.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * MyBatis基础配置
 *
 * @author wyf
 * @since 2015-12-19 10:11
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.uaa.auth.dao.mapper")
public class MyBatisConfig implements TransactionManagementConfigurer
{
    /**
     * mybatis 配置路径
     */
    private static String MYBATIS_CONFIG = "mybatis-config.xml";
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasPackage;

    @Value("${spring.datasource.defaultSource}")
    private int defaultSource;

    @Value("${mybatis.mapper-locations}")
    private String scanXmlPath;

    @Resource(name = "masterDataSource")
    DataSource masterDataSource;

    @Resource(name = "slaveDataSource")
    DataSource slaveDataSource;

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dynamicDataSource")
    @DependsOn("slaveDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        if( defaultSource==1)
        {
            // 默认数据源
            dynamicDataSource.setDefaultTargetDataSource(slaveDataSource);
            // 配置多数据源
            Map<Object, Object> dsMap = new HashMap();
            dsMap.put("masterDataSource", slaveDataSource);
            dsMap.put("slaveDataSource", masterDataSource);
            dynamicDataSource.setTargetDataSources(dsMap);
        }else
        {
            // 默认数据源
            dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
            // 配置多数据源
            Map<Object, Object> dsMap = new HashMap();
            dsMap.put("masterDataSource", masterDataSource);
            dsMap.put("slaveDataSource", slaveDataSource);
            dynamicDataSource.setTargetDataSources(dsMap);
        }

        return dynamicDataSource;
    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean()
    {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        /** 设置mybatis configuration 扫描路径 */
        bean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
        /** 设置datasource */
        bean.setDataSource(dynamicDataSource());
        /** 设置typeAlias 包扫描路径 */
        bean.setTypeAliasesPackage(typeAliasPackage);

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{(Interceptor) pageHelper});
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try
        {
            // System.out.println("mybatis xml location:" + scanXmlPath);
            bean.setMapperLocations(resolver.getResources(scanXmlPath));
            return bean.getObject();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "sqlSession")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory)
    {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Bean
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager()
//    {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }
    @Bean("txManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager()
    {
        return new DataSourceTransactionManager(dynamicDataSource());
    }


    private static final int TX_METHOD_TIMEOUT = 50000;
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.blueskykong.auth.service..*(..))";

    // 事务的实现Advice
    @Bean
    public TransactionInterceptor txAdvice(@Qualifier("txManager") PlatformTransactionManager m)
    {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
        RuleBasedTransactionAttribute requiresNewTx = new RuleBasedTransactionAttribute();
        requiresNewTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiresNewTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        requiresNewTx.setTimeout(TX_METHOD_TIMEOUT);
        Map<String, TransactionAttribute> txMap = new HashMap<>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("submit*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("modify*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("cancel*", requiredTx);
        txMap.put("newTran*", requiresNewTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("search*", readOnlyTx);
        source.setNameMap(txMap);
        TransactionInterceptor txAdvice = new TransactionInterceptor(m, source);
        return txAdvice;
    }

    // 切面的定义,pointcut及advice
    @Bean
    public Advisor txAdviceAdvisor(@Qualifier("txAdvice") TransactionInterceptor txAdvice)
    {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }

}
