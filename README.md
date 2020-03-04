# springcloud-quickstart

### springcloud-gateway 网关模块

基于SpringCloudGateway实现的网关模块，目前实现根据请求路径连接 springcloud-auth授权模块和 springcloud-app模块

### springcloud-auth 授权模块

基于JWT与Mysql实现的授权模块。
权限信息保存进**Redis**,避免客户端JWT挟带过重的权限信息。

### springcloud-eureka 注册中心eureka

实现单机板本，用作示例
### springcloud-app 应用模块

业务逻辑模块，根据微服务划分，可包含多个app模块。
使用 **Undertow** 代替内置的tomcat模块。

示例代码中配置采用了 com.netflix.archaius 用做**动态属性**注入。默认生效时间30秒。

示例代码中采用 **CompletableFuture** 加 **异步线程池** 进行service业务层的耗时处理，提高应用的异步并发。

### springcloud-common 公共模块
