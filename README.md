# resubmit

[resubmit](https://github.com/houbb/resubmit) 是一款为 java 设计的防止重复提交框架。

[![Build Status](https://travis-ci.com/houbb/resubmit.svg?branch=master)](https://travis-ci.com/houbb/resubmit)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/resubmit/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/resubmit)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/resubmit/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/resubmit)

## 创作目的

经常会写一些工具，有时候手动加防止重复提交很麻烦，引入 spring 又过于大材小用。

所以希望从从简到繁实现一个工具，便于平时使用。

## 特性

- 基于注解+字节码，配置灵活

- 自动适配常见的日志框架

- 支持编程式的调用

- 支持注解式，完美整合 spring

> [变更日志](https://github.com/houbb/resubmit/blob/master/CHANGELOG.md)

# 快速开始

## maven 引入

```xml
<dependency>
    <group>com.github.houbb</group>
    <artifact>resubmit-core</artifact>
    <version>0.0.2</version>
</dependency>
```

## 编码

- UserService.java

```java
@Resubmit(ttl = 5)
public void queryInfo(final String id) {
    System.out.println("query info: " + id);
}
```

- 测试代码

```java
@Test(expected = ResubmitException.class)
public void errorTest() {
    UserService service = ResubmitProxy.getProxy(new UserService());
    service.queryInfo("1");
    service.queryInfo("1");
}
```

相同的参数直接提交2次，就会报错。

- 测试场景2

如果等待超过指定的 5s，就不会报错。

```java
@Test
public void untilTtlTest() {
    UserService service = ResubmitProxy.getProxy(new UserService());
    service.queryInfo("1");
    DateUtil.sleep(TimeUnit.SECONDS, 6);
    service.queryInfo("1");
}
```

# spring 整合使用

## maven 引入

```xml
<dependency>
    <group>com.github.houbb</group>
    <artifact>resubmit-spring</artifact>
    <version>0.0.2</version>
</dependency>
```

## 代码编写

- UserService.java

```java
@Service
public class UserService {

    @Resubmit(ttl = 5)
    public void queryInfo(final String id) {
        System.out.println("query info: " + id);
    }

}
```

- SpringConfig.java

```java
@ComponentScan("com.github.houbb.resubmit.test.service")
@EnableResubmit
@Configuration
public class SpringConfig {
}
```

## 测试代码

```java
@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ResubmitSpringTest {

    @Autowired
    private UserService service;

    @Test(expected = ResubmitException.class)
    public void queryTest() {
        service.queryInfo("1");
        service.queryInfo("1");
    }

}
```

# Road-Map

- [ ] spring-boot 整合