# 微博（Weibo）

基于 Servlet + JSP 实现的简易微博系统，支持用户注册登录、发布微博、查看动态、删除微博等功能。

## 技术栈

- **后端**：Java Servlet 4.0
- **前端**：JSP + JSTL + CSS
- **数据库**：MySQL 8.0
- **部署**：WAR 包 → Tomcat

## 功能模块

| 模块 | 功能说明 |
|------|----------|
| 用户注册 | 用户名 + 密码注册，校验用户名是否已存在 |
| 用户登录 | 用户名密码登录，Session 校验登录状态 |
| 用户登出 | 销毁 Session，返回登录页 |
| 发布微博 | 登录后可发布文字微博 |
| 动态列表 | 查看所有用户发布的微博，按时间倒序排列 |
| 删除微博 | 删除自己发布的微博 |

## 项目结构

```
Weibo/
├── src/main/java/com/cquewjh1/weibo/
│   ├── controller/      # Servlet 控制器
│   │   ├── Login.java      # 登录
│   │   ├── Register.java   # 注册
│   │   ├── Logout.java     # 登出
│   │   ├── Message.java    # 发布微博
│   │   └── Delete.java     # 删除微博
│   ├── model/           # 数据模型与 DAO
│   │   ├── Account.java         # 用户实体
│   │   ├── AccountDAO.java      # 用户 DAO 接口
│   │   ├── AccountDAOFileImpl.java   # 文件存储实现
│   │   ├── AccountDAOJDBCImpl.java   # JDBC 实现
│   │   ├── Blah.java            # 微博实体
│   │   ├── BlahDAO.java         # 微博 DAO 接口
│   │   ├── BlahDAOFileImpl.java # 文件存储实现
│   │   ├── BlahDAOJDBCImpl.java # JDBC 实现
│   │   └── UserService.java     # 用户业务逻辑
│   ├── view/            # 视图输出
│   │   ├── Error.java       # 错误页
│   │   ├── Member.java      # 主页
│   │   └── Success.java     # 成功页
│   └── web/             # 过滤器与监听器
│       ├── MemberFilter.java   # 登录校验过滤器
│       └── WeiboListener.java # 应用监听器
├── src/main/webapp/
│   ├── WEB-INF/web.xml     # Web 配置
│   ├── index.jsp           # 登录页
│   ├── register.jsp        # 注册页
│   ├── member.jsp          # 微博主页
│   ├── success.jsp         # 成功页
│   ├── error.jsp          # 错误页
│   ├── css/               # 样式
│   └── images/            # 图片
├── weibo.sql              # 数据库建表 SQL
└── pom.xml
```

## 核心设计

### 1. DAO 接口与实现分离
定义 `AccountDAO` 和 `BlahDAO` 接口，分别提供文件存储和 JDBC 两种实现，方便切换数据存储方式。

### 2. 登录校验过滤器
通过 Servlet Filter 统一拦截请求，未登录用户不能访问主页，自动跳转登录页。

### 3. Session 管理
登录成功后将用户信息存入 Session，过滤器从 Session 中读取登录状态。

## 部署步骤

### 环境要求
- JDK 8+
- Maven 3.6+
- MySQL 8.0+
- Tomcat 9+

### 1. 克隆项目

```bash
git clone https://github.com/Wang-Studying/Weibo.git
cd Weibo
```

### 2. 数据库准备

```sql
CREATE DATABASE weibo DEFAULT CHARACTER SET utf8mb4;
USE weibo;
SOURCE weibo.sql;
```

### 3. 修改数据库配置

在 `AccountDAOJDBCImpl.java` 和 `BlahDAOJDBCImpl.java` 中修改数据库连接地址、用户名和密码。

### 4. 打包部署

```bash
mvn clean package
```

将生成的 `target/Weibo-1.0-SNAPSHOT.war` 放到 Tomcat 的 `webapps` 目录下，启动 Tomcat。

### 5. 访问

```
http://localhost:8080/Weibo-1.0-SNAPSHOT/
```

## 项目收获

- 理解 Servlet 工作原理与请求生命周期
- 掌握 JSP + JSTL 视图渲染方式
- 学会 DAO 模式设计，接口与实现分离
- 理解 Filter 过滤器和 Session 会话管理
- 熟悉 Maven WAR 包构建与 Tomcat 部署流程
