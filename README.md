#zonesion-cloud
中智讯教学云系统

请按照以下步骤配置你的开发环境:

1. [Node.js][]: 我们使用NodeJS运行开发服务，并构建前端代码。你可以通过NodeJS的官方网站进行安装。

2. 安装完NodeJS后，需要通过终端或者命令行进入到本项目的目录中，并运行以下命令来安装开发工具 ([BrowserSync][]). 当 package.json 变更时，也需要运行该命令。
```
npm --registry=https://registry.npm.taobao.org --disturl=https://npm.taobao.org/dist install
```
3. 我们使用 [Gulp][] 构建前端代码,[Bower][]管理依赖。需要通过此命令安装:
```
npm --registry=https://registry.npm.taobao.org --disturl=https://npm.taobao.org/dist install -g gulp-cli bower
```
4. 分别在两个终端或命令行中运行下方的两个命令，将可以运行起工程：
```
mvn
gulp
```
5. 配置你的Eclipse IDE:
工程名右键 -> Properties -> Resource -> Resource Filters -> Add Fiter...
![Exclude the 'node_modules' folder](https://git.oschina.net/uploads/images/2017/0728/141119_61abf3c3_620988.png)

工程名右键 -> Properties -> Javascript -> Include path -> source -> zonesion-cloud/src/main/webapp -> Excluded -> Edit... -> Add multiple
![Edit Excluded](https://git.oschina.net/uploads/images/2017/0728/141601_fab3ce74_620988.png)
![Add multiple](https://git.oschina.net/uploads/images/2017/0728/141712_58f33ed9_620988.png)
![Add app](https://git.oschina.net/uploads/images/2017/0728/141913_3a6761be_620988.png)

6. Eclipse中开发调试启动方法：
工程名上右键-> Run As -> Run Configurations
![main配置](https://git.oschina.net/uploads/images/2017/0728/100655_6b5846ea_620988.jpeg)
![Arguments配置](https://git.oschina.net/uploads/images/2017/0728/100901_55754b39_620988.jpeg)
Apply -> Run

此外，我们使用Bower管理CSS和JavaScript依赖。使用`bower install xxx:xx --save`来安装依赖，使用`bower update`来更新依赖。

# 构建生产环境

运行此命令打包生产环境:
```
mvn -Pprod clean package
```
该命令将自动连接并压缩所有的CSS和JavaScript。此外还将修改`index.html`来引用新的资源文件。

运行下面的命令来确认打包程序可以运行:
```
java -jar target/*.war --spring.profiles.active=prod
```
用浏览器打开 [http://localhost:8080](http://localhost:8080) 进行访问