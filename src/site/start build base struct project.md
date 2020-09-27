# 在我们本次学习,要实现构建一个微服务的基本结构,然后跟着实现这个项目的基本运行.

## 构建父工程

- 首先是创建一个maven项目
	* 选择简单的模式,不需要用骨架
	* 选择pom类型
	* 需要在父工程中添加全部的公共的配置和jar包依赖的管理
		* 父工程中定义过的依赖,子项目用到不用写版本配置
		* 父工程和模块就是一种依赖关系.

- pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  
  <groupId>com.atguigu.springcloud</groupId>
  <artifactId>microservicecloud</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>pom</packaging>
  
  <properties>
  	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  	<!--  保证每次编辑的版本的jdk版本稳定不变  -->
  	<maven.compiler.source>1.8</maven.compiler.source>
  	<maven.compiler.target>1.8</maven.compiler.target>
  	<!-- 常用的三个依赖包版本定义 -->
  	<junit.version>4.12</junit.version>
  	<log4j.version>1.2.17</log4j.version>
  	<lombok.version>1.16.18</lombok.version>
  </properties>
  
  <!-- 父类工程管理的机制 -->
  <dependencyManagement>
  	<dependencies>
  		<dependency>
  			<groupId>org.springframework.cloud</groupId>
		  	<artifactId>spring-cloud-dependencies</artifactId>
		  	<version>Dalston.SR1</version>
		  	<type>pom</type>
		  	<scope>import</scope>
  		</dependency>
  		
  		<dependency>
  			<groupId>org.springframework.boot</groupId>
		  	<artifactId>spring-boot-dependencies</artifactId>
		  	<version>1.5.9.RELEASE</version>
		  	<type>pom</type>
		  	<scope>import</scope>
  		</dependency>
  		
  		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.47</version>
		</dependency>
			
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.0.31</version>
		</dependency>
		
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.0</version>
		</dependency>
		
		<dependency>
			<groupId>ch.qos.logback</groupId>
			<artifactId>logback-core</artifactId>
			<version>1.2.3</version>
		</dependency>
		
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit.version}</version>
			<scope>test</scope>
		</dependency>
		
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>${log4j.version}</version>
		</dependency>
  		
  	</dependencies>
  
  </dependencyManagement>
  
</project>
```

## 构建第一个子模块 api

- 首先在父工程上右键创建一个maven模块
	* 简单模式不用骨架
	* 输入子模块的名称
	* 选择子模块的类型为jar
		* 成功后,在父工程里面会自主的生成一个module的子项目依赖配置项
	* 配置pom依赖
	* 创建一个实体类Dept
	* 全部完成,更新最新的包到maven的仓库中
		- 先在子项目上右键,选择run as, 选择maven clean,清理上次的包
		- 然后再run as,选择maven install, 将最新的包放入maven的本地仓库
		- 正常情况下,应该都是成功的就是对的.

- 实体类Dept的创建
	* 首先我们要了解lombook解放我们写getter,setter,tostring,等等
	* 另外一个小知识,就是一个微服务实例可以有自己的数据库,因此在dept的定义里面有一个db_source属性

- 这里就是子模块的应用的坐标

```xml
<dependency>
    <groupId>com.atguigu.springcloud</groupId>
    <artifactId>microservicecloud-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

- pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  
  <!-- 子类里面显示申明才能有明确的继承关系,无意外,就是父类的默认版本,否则自己定义 -->
  <parent>
    <groupId>com.atguigu.springcloud</groupId>
    <artifactId>microservicecloud</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </parent>
  
  <!-- 当前模块自己叫什么名字 -->
  <artifactId>microservicecloud-api</artifactId>
  
  <!--  public class microservicecloud-api extends microservicecloud 就是继承和复写 -->
  
  <!-- 当前模块用到的jar包,按自己的需求添加,如果父类包含了,可以不再写版本 -->
  <dependencies>
  	<dependency>
  		<groupId>org.projectlombok</groupId>
  		<artifactId>lombok</artifactId>
  	</dependency>
  </dependencies>
</project>
```

约定 > 配置 > 编码

创建项目后,先构建配置

最后编写代码


## 构建provider子项目

同理的创建不多说

- pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>com.atguigu.springcloud</groupId>
    <artifactId>microservicecloud</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </parent>
  
  <artifactId>microservicecloud-provider-dept-8001</artifactId>
  
  <dependencies>
  
  <!-- 引用自定义的api通用包,可以适用Dept部门Entity -->
  <dependency>
    <groupId>com.atguigu.springcloud</groupId>
    <artifactId>microservicecloud-api</artifactId>
    <version>${project.version}</version>
</dependency>

	<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
	</dependency>
	
   	<dependency>
   		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency>
	
   	<dependency>
	   	<groupId>com.alibaba</groupId>
		<artifactId>druid</artifactId>
	</dependency>
	
   	<dependency>
	   	<groupId>ch.qos.logback</groupId>
		<artifactId>logback-core</artifactId>
	</dependency>
	
   	<dependency>
   		<groupId>org.mybatis.spring.boot</groupId>
		<artifactId>mybatis-spring-boot-starter</artifactId>
	</dependency>

	<!-- 内嵌的 -->
  	<dependency>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-starter-jetty</artifactId>
  	</dependency>

  	<dependency>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-starter-web</artifactId>
  	</dependency>

  	<dependency>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-starter-test</artifactId>
  	</dependency>

  	<!-- 热部署 -->
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>springloaded</artifactId>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-devtools</artifactId>
  	</dependency>
  	
  </dependencies>
</project>

```

第三个子项目就没有什么好说的,就是直接来就是啦.


## 配置yaml配置文件

```text
server:
	port: 8001
	
mybatis:
	config-location: classpath:mybatis/mybatis.cfg.xml             # mybatis configure file.
	type-aliases-package: com.atguigu.springcloud.entities    # the package of all Entity alias class.
	mapper-locations: 
		- calsspath:mybatis/mapper/**/*.xml                                      # mapper mapping file location

spring:
	application:
		name: microservicecloud-dept
	datasource:
		type: com.alibaba.druid.pool.DruidDataSource                # current database datasource type
		#driver-class-name: org.gjt.mm.mysql.Driver
		driver-class-name: com.mysql.jdbc.Driver                          # mysql Driver class
		url: jdbc:mysql://localhost:3306/cloudDB01                     # db name
		username: root
		password: root
		dbcp2:
			min-idle: 5                                                                                    # min connect size
			initial-size: 5                                                                                 # init connect size
			max-total: 5                                                                                 # max connect size
			max-wait-millis: 200                                                                  # max time of wait connect 


```




## Mybatis 核心配置文件

- 完整版本,实际在和spring整合后就不是这样的啦

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 环境, 就是配置数据库访问环境的标签. default - 默认使用什么环境 -->
	<environments default="development">
		<!-- 配置具体的某一个环境 id - 当前环境的命名 -->
		<environment id="development">
			<!-- 事务管理方式, 当前框架管理数据库事务使用什么技术. type - 使用的具体技术. JDBC, 就是Connection.commit()/rollback() -->
			<transactionManager type="JDBC" />
			<!-- 数据源, 访问的数据库参数 type - 管理方式, 管理Connection的方式, POOLED , 代表池化管理. 就是连接池. -->
			<dataSource type="POOLED">
				<!-- 配置具体参数 -->
				<property name="driver" value="com.mysql.jdbc.Driver" />
				<property name="url"
					value="jdbc:mysql://localhost:3306/mybatis" />
				<property name="username" value="root" />
				<property name="password" value="root" />
			</dataSource>
		</environment>
	</environments>

	<!-- 引用映射文件 -->
	<mappers>
		<!-- resource : 相对路径查询资源的属性. 相对于当前核心配置文件的位置开始查找映射文件. -->
		<mapper resource="com/bjsxt/pojo/User-mapper.xml" />
	</mappers>
</configuration>

```

- 我们项目中的springboot下的配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

	<settings>
		<!-- 开启二级缓存 -->
		<setting name="cacheEnabled" value="true"/>
	</settings>

</configuration>

```


## 数据库脚本

```sql
DROP DATABASE if EXISTS cloudDB01;
CREATE DATABASE cloudDB02 CHARACTER SET UTF8;
USE cloudDB02;
CREATE TABLE dept(
	deptno BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	dname VARCHAR(60),
	db_source VARCHAR(60)
);

INSERT INTO dept(dname,db_source) VALUES('开发部', DATABASE());
INSERT INTO dept(dname,db_source) VALUES('人事部', DATABASE());
INSERT INTO dept(dname,db_source) VALUES('财务部', DATABASE());
INSERT INTO dept(dname,db_source) VALUES('市场部', DATABASE());
INSERT INTO dept(dname,db_source) VALUES('运维部', DATABASE());

select * from dept;

```

```text

<!-- namespace:填写映射当前的Mapper接口，所有的增删改查的参数和返回值类型， 就可以直接填写缩写，不区分大小写，直接通过方法名去找类型 -->
<mapper namespace="com.itheima.mapper.UserMapper">
	<!-- sql:里面可以写入一个共同的sql代码，用于提取重复的代码。 要使用该代码的时候就直接使用<include>标签 id:为提取的sql代码，取一个id，起标识作用 -->
	<sql id="select">
		select * from user
	</sql>
	<!-- public User findUserById(int id); id:填写在XxxMapper接口中的方法名 parameterType:填写参数的类型 
		resultType:填写方法中返回值的类型，不用写全路径，不区分大小写 -->
	<select id="findUserById" parameterType="int" resultType="user">
		<!-- include:用于加载提取公共的sql语句，与<sql>标签对应 refid:填写<sql>标签中的id属性 -->
		<include refid="select"></include>
		where id = #{id}
	</select>
	<!-- resultMap属性:与resultMap标签一起使用，填写resultMap标签中定义的id属性 -->
	<select id="findAllOrders" resultMap="orders">
		select * from orders
	</select>
	<!-- resultMap标签:用于自定义封装结果 type:最终结果还是封装到实体类中，type就是指定封装到哪一个类中 id:与<select>标签中的resultMap中的属性一直，一定要唯一 
		<id>:该标签是指定主键封装到实体类中的哪一个属性(可以省略) <result>:该标签是其他的列封装到实体类中，一般只需填写实体类中的属性与表中列不同的项即可 
		property:填写实体类中的属性，column:填写表中的列名 -->
	<resultMap type="Orders" id="orders">
		<id property="id" column="id" />
		<result property="userId" column="user_id" />
	</resultMap>
	<!-- public void addUser(User user); insert:用于执行添加语句；update:执行更新语句 同样 delete:执行删除语句 -->
	<insert id="addUser" parameterType="user">
		<!-- selectKey配置主键信息的标签 keyColumn:对应数据库表中的主键列 keyProperty:对应实体类中的属性 after:代表执行下面代码之前，先执行当前里面的代码 -->
		<selectKey keyColumn="id" keyProperty="id" order="AFTER"
			resultType="int">
			select LAST_INSERT_ID()
		</selectKey>
		insert into user
		(username,sex,address)
		values(#{username},#{sex},#{address})
	</insert>
	<!-- public List<User> findUserBySexAndUsername(User user); -->
	<select id="findUserBySexAndUsername" parameterType="User"
		resultType="user">
		<!--select * from user where 1=1 -->
		<include refid="select"></include>
		<!-- where标签:一个where条件语句，通常和<if>标签混合使用 -->
		<where>
			<!-- if标签:执行一个判断语句，成立才会执行标签体内的sql语句 test:写上条件判断语句 注意:这里每一个if前面都尽量加上and，如果你是第一个条件，框架会自动帮你把and截取，如果是第二个if就不能省略and -->
			<if test="sex != null and sex != ''">
				and sex = #{sex}
			</if>
			<if test="username != null and username != ''">
				and username like '%${username}%'
			</if>
		</where>
	</select>

	<!-- public List<User> findUserByIds(QueryVo vo); -->
	<!-- QueryVo:是一个实体包装类，通常用于封装实体类之外的一些属性 -->
	<select id="findUserByIds" parameterType="QueryVo"
		resultType="user">
		<include refid="select"></include>
		<where>
			<!-- foreach:循环语句，通常多用于参数是集合时，需要对参数进行遍历出来，再进行赋值查询 collection:参数类型中的集合、数组的名字，例：下面的ids就是QueryVo这个类中的list集合的名字 
				item:为遍历该集合起一个变量名，遍历出来的每一个字，都赋值到这个item中 open:在sql语句前面添加的sql片段 close:在sql语句后面添加的sql片段 
				separator:指定遍历元素之前用什么分隔符 -->
			<foreach collection="ids" item="id" open="id in(" close=")"
				separator=",">
				#{id}
			</foreach>
		</where>
	</select>
	</mapper>
```



注意事项

- mybatis
	- 对于整合过后的mybatis,我们在dao的接口上,要加上@Mapper这个注解
	- 对于启动类的名称,我们注意下,最好是带上端口,就好找啦.


注册服务的架构模式

- springcloud

````text
    【eureka|eureka|eureka|eureka|...】(多个enureka组成注册中心的集群)
        /+                                    +\
       /                                        \
   Consumer(消费者) ---> rest-remote-call --> Service Provider(多个提供服务的微服务)
````

- dubbo

````text
参考官方
````

引入一个cloud的一个新技术组件有两步
- 1 新增一个相关的maven坐标
- 2 ???



主机名的修改:

提供方修改:
- yml
    - instance
        - #### 通过修改定义一个实例的名称(小细节)
        - instance-id: microservicecloud-dept8001


报告信息(/info报错的问题)


多个注册中心进行集群的时候,如果是在一台机器上
则必须定义不同的域名,用于进行多个服务的绑定和区别

定义一下hosts
- 127.0.0.1 eureka7001.com
- 127.0.0.1 eureka7002.com
- 127.0.0.1 eureka7003.com


Ribbon 通过主动的注册均衡规则对象即可实现均衡规则的切换

Ribbon 自定义规则,则有许多的注意,我们仔细看一下提交的记录,找到自定义rule的提交历史查看一下就知道啦.



# 关于注册中心的说明

- 集群
    - 集群的情况下,的确是好,但是单机开发特别占用资源
        - 我们挺两个,留一个即可.
            - 会报错,不要紧,服务正常访问.
            - 这个体现高可用.
            - 但是正式生产环境则需要排查问题,测试这个就是正常的哈.
                - 我们是为了避免占用太多资源而已,哈哈

- 当然,简单的来说,我们都配置成单机的模式也是可以的.


# Feign: 面向接口调用微服务

是一个声明式的Web服务客户端,使得编写Web服务客户端变得非常容易.
> 只需要创建一个接口,然后上面添加注解即可.

[Feign Website Loading](https://github.com/OpenFeign/feign)

> 旨在在使编写Java Http客户端变得容易.

前面,Ribbon + RestTemplate 利用RestTemplate对http请求的封装处理,形成一套模板化的调用方法.
但是实际开发中,由于对服务依赖的调用不止一处, 

> 往往一个接口会被多处调用,所以通常会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用.

所以,feign在此基础上做了进一步的封装,由他来帮我们定义和实现依赖服务接口的定义.

             
大家目前,都是习惯面向接口编程,比如WebService, 比如DAO接口,这个已经是大家的规范.

2.1 微服务的名称的方式获得调用地址
2.2 通过接口+注解, 获得我们的调用服务
    - 可以做的很强大.
    
2.3 为了适应社区其他程序员提出的, 还有统一的面向接口编程, 这个就是我们的Feign.
    - 就是这么来的.
    
至于写法,就是和mybatis的Dao上加上一个@Mapper的注解,就可以映射到xml解析类似.

> Feign 加上注解,就是访问的微服务.

我们单独的搞一个服务,里面提供一组服务接口打包封装为一个客服务的统一接口Rest服务

## 定义一个新的子模块, microservicecloud-consumer-dept-feign










