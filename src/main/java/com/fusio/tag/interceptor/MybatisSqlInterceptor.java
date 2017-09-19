package com.fusio.tag.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fusio.tag.commons.prop.ConfigProperties;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class }) })
public class MybatisSqlInterceptor implements Interceptor {
	private Properties properties;
	private static Logger logger = LoggerFactory.getLogger(MybatisSqlInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Object[] args = invocation.getArgs();

		long time = 0;
		long start = System.currentTimeMillis();
		Object returnValue = null;
		boolean result = false;
		try {
			// 获取原始的ms
			MappedStatement ms = (MappedStatement) args[0];
			Object parameterObject = args[1];
			BoundSql boundSql = ms.getBoundSql(parameterObject);
			Configuration configuration = ms.getConfiguration();
			this.printFormat(ms, boundSql, configuration);
		} catch(Exception e) {}
		finally {
			Exception ex = null;
			try {
				returnValue = invocation.proceed();
				result = true;
			} catch (Exception e) {
				result = false;
				ex = e;
			}
			this.printIsSuccess(result);
			long end = System.currentTimeMillis();
			time = (end - start);
			this.printTimeSpan(time);
			
			if (ex != null) {
				ex.printStackTrace();
				logger.error("MybatisSQL错误(请观察是否字段超长)", ex);
				throw ex;
			}
		}

		return returnValue;
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	private void printFormat(MappedStatement ms, BoundSql boundSql, Configuration configuration) {
		if (isOpen()) {
			print("");
			print("@@@@语句 " + boundSql.getSql().replaceAll("[\\s]+", " "));
			print("@@@@参数 " + JSON.toJSONString(boundSql.getParameterObject()));
			print("@@@@带参sql \n" + showSqlWithParameter(configuration, boundSql));
			// print("@@@@getParameterMappings:" +
			// boundSql.getParameterMappings());
			print("@@@@ID " + ms.getId());
		}
	}

	private void printTimeSpan(long time) {
		if (isOpen()) {
			print("@@@@时间 " + time + " ms");
			print("");
		}
	}

	private void printIsSuccess(boolean succ) {
		if (isOpen()) {
			print("@@@@结果 " + (succ ? "成功" : "失败"));
		}
	}

	private boolean isOpen() {
		String s = ConfigProperties.get("switch.mybatisDebugSql");
		return "1".equals(s);
	}

	private void print(String msg) {
		String type = ConfigProperties.get("mybatisDebugSqlType");
		int iType = Integer.parseInt(type);
		// 0-logger,1-System.out,2-System.err
		if (iType == 0) {
			logger.info(msg);
		} else if (iType == 1) {
			System.out.println(msg);
		} else if (iType == 2) {
			System.err.println(msg);
		} else {
			// 方便
			logger.info(msg);
			System.out.println(msg);
		}
	}

	public static String showSqlWithParameter(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		return sql;
	}

	private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			Date d = (Date) obj;
			value = "'" + formatter.format(d) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}
}