#服务提供者
nacos-discovery-server
#服务消费者
nacos-discovery-client


#配置中心
nacos-config
注:  (1)要使用 bootstrap.properties/yml 文件
     (2)Nacos配置中的  
                     Data ID -> ${spring.cloud.nacos.config.prefix}-${spring.profile.active}.${spring.cloud.nacos.config.file-extension}
                                 默认值properties -> spring.application.name 加上 properties -> spring.cloud.nacos.config.file-extension
                                 指定properties -> spring.cloud.nacos.config.prefix=sunshine-nacos-config001
                     Group ->    默认值为: DEFAULT_GROUP 
                                 指定properties -> spring.cloud.nacos.config.group=SUNSHINE_GROUP001  
                     配置内容:   例 sunshine.name=zhangna  (@Value("${sunshine.name:}"))
     (3)spring.cloud.nacos.config.namespace 区分不同的环境
     (4)spring.cloud.nacos.config.group 专注于业务层面的分组管理
   
   在发布时用命令指定不同的环境：-Dspring.profiles.active=DEV



> 这有个坑啊~~
> 如果一个pom中同时引用了这两个依赖,那么项目启动会报错。所以分别引入各自的依赖包
  #服务的注册和发现
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
  </dependency>
  
 #服务的注册和发现
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
  </dependency>