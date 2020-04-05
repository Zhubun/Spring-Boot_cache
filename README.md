# Spring-Boot_cache
Spring Boot_cache与Redis整合

基于SpringBoot框架制作的后端,采用MVC设计模式,在普通的增删改查操作上增加了Redis作为缓存,访问指定url进行增删改查,返回json字符串

运用了切面实现简单的Redis缓存操作,
缓存只对查询缓存,更新会删除对应缓存

加入swagger2测试
