此工程为创建模块时的一个模版工程，该工程采用 Maven Modules 的管理方式进行创建

Maven 各 Moduel 说明如下：
1、<module>kk-demo-api</module>
	模块接口工程，只存放模块提供给外部的接口，各package 说明：
	    顶层包 com.melot.kk.demo.api
	    	常量和枚举类存放在 com.melot.kk.demo.api.constant 包内
	 	服务接口类存放在 com.melot.kk.demo.api.service 包内
		实体类存放在 com.melot.kk.demo.api.domain 包内
		   返回对象存放在 com.melot.kk.demo.api.domain.DO 包内
		   新增和编辑对象存放在 com.melot.kk.demo.api.domain.Param 包内

2、<module>kk-demo-server</module>
	模块服务工程，包含模块的所有服务实现，各package 说明：
	    启动脚本存放在 bin 目录下
	    顶层包 com.melot.kk.demo.server
		服务实现类存放在 com.melot.kk.demo.server.impl 包内
		实体类存放在 com.melot.kk.demo.server.model 包内
		Dao类存放在 com.melot.kk.demo.server.dao 包内
		工具类存放在 com.melot.kk.demo.server.util 包内
		配置类存放在 com.melot.kk.demo.server.config 包内
		常量类存放在 com.melot.kk.demo.server.constant 包内
		缓存类存放在 com.melot.kk.demo.server.redis 包内
		服务启动类为：com.melot.kk.demo.server.Application

3、<module>kk-demo-project</module>
	模块小程序工程，集中模块的小程序功能，各package 说明：
	    启动脚本存放在 bin 目录下
	    顶层包 com.melot.kk.demo.project
		job 存放在 com.melot.kk.demo.project.job 包内
		配置类存放在 com.melot.kk.demo.project.config 包内
		服务启动类为：com.melot.kk.demo.project.Application
	
	小程序都是基于公司框架 elastic-job-melot-core 进行的

4、SQL 目录存放模块相关的数据库操作sql