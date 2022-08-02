# resubmit

[resubmit](https://github.com/houbb/resubmit) 是一款为 java 设计的防止重复提交框架。

[![Build Status](https://travis-ci.com/houbb/resubmit.svg?branch=master)](https://travis-ci.com/houbb/resubmit)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/resubmit/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/resubmit)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/resubmit/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/resubmit)

## 创作目的

有时候手动加防止重复提交很麻烦，每次手动编写不利于复用。

所以希望从从简到繁实现一个工具，便于平时使用。

## 特性

- 渐进式实现，可独立 spring 使用

- 基于注解+字节码，配置灵活

- 支持编程式的调用

- 支持注解式，完美整合 spring

- 支持整合 spring-boot

> [变更日志](https://github.com/houbb/resubmit/blob/master/CHANGELOG.md)

# 快速开始

## maven 引入

```xml
<dependency>
    <group>com.github.houbb</group>
    <artifact>resubmit-core</artifact>
    <version>1.0.0</version>
</dependency>
```

## 编码

- UserService.java

`@Resubmit` 对应的属性如下：

| 属性 | 说明 | 默认值 |
|:---|:---|:---|
| value() | 多久内禁止重复提交，单位为毫秒。| 60000 |

```java
@Resubmit(5000)
public void queryInfo(final String id) {
    System.out.println("query info: " + id);
}
```

- 测试代码

如果在指定时间差内，重复请求，则会抛出异常 ResubmitException

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

## 自定义

`ResubmitProxy.getProxy(new UserService());` 可以获取 UserService 对应的代理。

等价于：

```java
ResubmitBs resubmitBs = ResubmitBs.newInstance()
                .cache(new CommonCacheServiceMap())
                .keyGenerator(new KeyGenerator())
                .tokenGenerator(new HttpServletRequestTokenGenerator());

UserService service = ResubmitProxy.getProxy(new UserService(), resubmitBs);
```

其中 ResubmitBs 作为引导类，对应的策略都支持自定义。

| 属性 | 说明  | 默认值 |
|:---|:---|:---|
| cache() | 缓存实现策略 | 默认为基于 ConcurrentHashMap 实现的基于内存的缓存实现 |
| keyGenerator() | key 实现策略，用于唯一标识一个方法+参数，判断是否为相同的提交 | md5 策略 |
| tokenGenerator() | token 实现策略，用于唯一标识一个用户。 | 从 HttpServletRequest 中的 header 属性 `resubmit_token` 中获取 |


# spring 整合使用

## maven 引入

```xml
<dependency>
    <group>com.github.houbb</group>
    <artifact>resubmit-spring</artifact>
    <version>1.0.0</version>
</dependency>
```

## 代码编写

- UserService.java

```java
@Service
public class UserService {

    @Resubmit(5000)
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

### @EnableResubmit 注解说明

`@EnableResubmit` 中用户可以指定对应的实现策略，便于更加灵活的适应业务场景。

和 `ResubmitBs` 中支持自定义的属性一一对应。

| 属性 | 说明  | 默认值 |
|:---|:---|:---|
| cache() | 缓存实现策略 | 默认为基于 ConcurrentHashMap 实现的基于内存的缓存实现 |
| keyGenerator() | key 实现策略，用于唯一标识一个方法+参数，判断是否为相同的提交 | md5 策略 |
| tokenGenerator() | token 实现策略，用于唯一标识一个用户。 | 从 HttpServletRequest 中的 header 属性 `resubmit_token` 中获取 |

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

# 整合 spring-boot

## maven 引入

```xml
<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>resubmit-springboot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 代码实现

- UserService.java

这个方法实现和前面的一样。

```java
@Service
public class UserService {

    @Resubmit(5000)
    public void queryInfo(final String id) {
        System.out.println("query info: " + id);
    }

}
```

- Application.java

启动入口

```java
@SpringBootApplication
public class ResubmitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResubmitApplication.class, args);
    }

}
```

## 测试代码

```java
@ContextConfiguration(classes = ResubmitApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ResubmitSpringBootStarterTest {

    @Autowired
    private UserService service;

    @Test(expected = ResubmitException.class)
    public void queryTest() {
        service.queryInfo("1");
        service.queryInfo("1");
    }

}
```

# 自定义策略

上面提到 `@EnableResubmit` 中的策略支持自定义。

此处仅以 cache 为例，为了简单，默认是基于本地内存的缓存实现。

**如果你不是单点应用，那么基于 redis 的缓存更加合适**

## 自定义缓存 cache

### 实现缓存

只需要实现 `ICommonCacheService` 接口即可。

```java
public class MyDefineCache extends CommonCacheServiceMap {

    // 这里只是作为演示，实际生产建议使用 redis 作为统一缓存
    @Override
    public synchronized void set(String key, String value, long expireMills) {
        System.out.println("------------- 自定义的设置实现");

        super.set(key, value, expireMills);
    }

}
```

### core 中指定使用

在非 spring 项目中，可以在引导类中指定我们定义的缓存。

```java
ResubmitBs resubmitBs = ResubmitBs.newInstance()
                .cache(new MyDefineCache());

UserService service = ResubmitProxy.getProxy(new UserService(), resubmitBs);
```

其他使用方式保持不变。

### spring 中指定使用

在 spring 项目中，我们需要调整一下配置，其他不变。

```java
@ComponentScan("com.github.houbb.resubmit.test.service")
@Configuration
@EnableResubmit(cache = "myDefineCache")
public class SpringDefineConfig {

    @Bean("myDefineCache")
    public ICommonCacheService myDefineCache() {
        return new MyDefineCache();
    }

}
```

`@EnableResubmit(cache = "myDefineCache")` 指定我们自定义的缓存策略名称。

# Redis 的内置缓存策略

为了便于复用，基于 redis 的缓存策略已实现。

> [Redis-Config](https://github.com/houbb/redis-config)

# Road-Map

- [ ] 优化 spring 对应的版本依赖

- [ ] 添加基于 redis 的 cache 实现
