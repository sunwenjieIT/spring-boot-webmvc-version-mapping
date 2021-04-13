# spring-boot-webmvc-version-mapping

## 概述

该插件用于实现spring webmvc层级的接口版本号路由, 版本号通过请求头带入, 当method注解上的条件完全一致时, 通过版本号找到最合适的接口


### 快速接入

接入项目需要是spring boot项目, 组件基于spring boot 2.0.5开发, 暂未测试高版本下的可用性.
请自行测试后决定是否使用本插件. 

**1.引入依赖**

    <dependency>
        <groupId>io.github.sunwenjieIT</groupId>
        <artifactId>spring-boot-webmvc-version-mapping</artifactId>
        <version>1.0.2.1-SNAPSHOT</version>
    </dependency>

**2.启用功能**  
在spring boot启动类上添加注解`@EnableWebVersionControl`

**3.在controller的方法上添加注解**  
目前支持两种注解类型. 版本号校验时是闭区间, 支持min, max值一致(特定某个版本访问), 如果版本号有重叠, 支持order字段排序, 数值越小优先级越高
minVersion, maxVersion的默认值为`"-1"`, order的默认值为`-1`

3.1 @ClientVersion

```java
    @PostMapping("/yourMethodPath")
    @ClientVersion(minVersion = "1", maxVersion = "20", order = -1)
    public String yourMethod1() {
        ...
    }
```

3.2 @PostMappingWithVersion

```java
    @PostMappingWithVersion(value = "/yourMethodPath", minVersion = "21", maxVersion = "25")
    @ClientVersion(minVersion = "21", maxVersion = "25")
    public String yourMethod2() {
        ...
    }
```

**4.验证效果**

请求时带上请求头 `version`  
请求头版本号值`1 <= version <= 20`时, 访问到`yourMethod1`  
请求头版本号值`21 <= version <= 25`时, 访问到`yourMethod2`

### 配置项

#### 校验规则
本插件提供了 `Integer`, `String` 两种校验规则. 

Integer规则的版本号值要求为纯数字, 支持正负符号  
String规则的版本号要求为形如`1.0.5`的形式, 不限制层级(`1`, `1.2`, `1.2.3`, `1.2.3.4`...都支持)

可以通过在`application.yml/application.properties`配置文件中配置生效的规则, 校验规则全局唯一

Integer规则(默认规则)  
`webmvc.version.mapping.clazz=io.github.sunwenjieIT.webmvc.version.mapping.condition.IntegerVersionRequestCondition`

String规则  
`webmvc.version.mapping.clazz=io.github.sunwenjieIT.webmvc.version.mapping.condition.StringVersionRequestCondition`

#### 版本号请求头

默认请求头为`version`  
`webmvc.version.mapping.header-key=version`

### 其他说明

1. 启用了本功能的接口, 若请求头中的版本号无法匹配, 则会得到404的响应.  
2. 需要版本匹配的接口可以和普通接口同时使用, 所以要解决1问题, 可以和原有的无版本号控制的接口混用, 为版本号匹配不到时兜底

### 添加自定义的校验规则
本插件支持接入自定义的校验规则  
//TODO 
