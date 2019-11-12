### spring boot demo

该demo较（ji）为简单，适合初学者参考学习（可用于了解spring boot项目搭建方式）

参考链接： [spring boot guide](https://spring.io/guides/gs/serving-web-content/)

************

#### 项目包含

- spring boot
- mybatis
- thymeleaf

************

#### 文件结构

```text
springboot
    src.main
         java
            com.wyf.*
         resources
            mapper    -- mybatis mapper
            static    -- 静态资源 html css js等
            templates -- thymleaf模板
            application.yml
    pom.xml

```

#### 配置

pom.xml
```XML
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.wyf</groupId>
	<artifactId>bootdemo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>bootdemo</name>
	<description>Demo project for Spring Boot</description>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.10.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!--thymeleaf模板-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<!-- lommbok   -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<!-- MyBatis -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.0</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.47</version>
		</dependency>
	</dependencies>

	<build>
		<finalName>spring4mybatis3</finalName>
		<resources>
			<resource>
				<directory>src/main/java</directory>
				<includes>
					<include>**/*.xml</include>
				</includes>
			</resource>
			<resource>
				<directory>src/main/resources</directory>
			</resource>
		</resources>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```
application.yml
```yaml
server:
  port: 8080

spring:
    mvc:
      static-path-pattern: /**
    datasource:
        name: dbtest
        url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false
        username: root
        password: 123456
        driver-class-name: com.mysql.jdbc.Driver
    thymeleaf:
      cache: false #暂时关闭

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.wyf.entity

```
ps 
sql文件见 [doc/180213170926.sql](doc/180213170926.sql)，数据库名、数据库用户名、密码自行在application.yml文件内修改