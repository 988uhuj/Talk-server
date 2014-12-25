/**
 *
 */
package me.risky.talk.common.util.pagination;

import me.risky.talk.common.util.Dialect;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


@Intercepts({@Signature(type=Executor.class,method="query",args={ MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })})
public class PaginationInterceptor implements Interceptor {

	private final static Log log = LogFactory.getLog(PaginationInterceptor.class);

	//方言
	Dialect dialect = new OracleDialect();

	public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept----------------");
		MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];

		//分页参数--上下文传参
		Page page = Page.getPage();

		if(null == page){
            System.out.println("page null");
			return invocation.proceed();
		}

		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		String originalSql = boundSql.getSql().trim();
		RowBounds rowBounds = (RowBounds)invocation.getArgs()[2];
		Object parameterObject = boundSql.getParameterObject();

		if(boundSql==null || boundSql.getSql()==null || "".equals(boundSql.getSql()))
			return null;


		if(page != null)
		{
			//-所查询出来的结果的总条数, 也是带查询条件的总条数 ,必须每次统计总页数
			int totpage = 0;
		  //得到总记录数
		  if (totpage==0)
			{
				try {
					StringBuffer countSql  = new StringBuffer(originalSql.length()+100 );
					countSql.append("select count(*) from (").append(originalSql).append(") as total");
					Connection connection=mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection()  ;
					PreparedStatement countStmt = connection.prepareStatement(countSql.toString());
					BoundSql countBS = new BoundSql(
                            mappedStatement.getConfiguration(),
                            countSql.toString(),
                            boundSql.getParameterMappings(),parameterObject);
					setParameters(countStmt,mappedStatement,countBS,parameterObject);
					ResultSet rs = countStmt.executeQuery();
					if (rs.next()) {
						page.setTotalNum(rs.getInt(1));
					}
                    System.out.println("getTotalNum:" + page.getTotalNum());
					rs.close();
					countStmt.close();
					connection.close();
				} catch (Exception e) {
                    e.printStackTrace();
					log.error("获取总数出错!");
				}

			}

			if(rowBounds == null || rowBounds == RowBounds.DEFAULT){
				rowBounds= new RowBounds(page.getPageSize()*(page.getPageNo()-1),page.getPageSize());
			}
			//分页查询
            Dialect dialect = new MySQLDialect();
            String pageSql=dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql,boundSql.getParameterMappings(),boundSql.getParameterObject());

            MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));

            //重新赋值addtionalParameter。DefaultParameterHandler.setParameters解析sql参数时，会从addtionalParameter
            //获取其他参数值，比如mybatis的foreach 参数。这里采用new BoundSql()没有拷贝原boundSql的addtionalParameter值
            List<ParameterMapping> pmLs = boundSql.getParameterMappings();
            for(ParameterMapping pm:pmLs){
                String propertyName = pm.getProperty();
                //这里只是增加以"_frec_"开始的参数，表示是for each参数，其他参数可以从ParameterObject获取。
                if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)){
                    newBoundSql.setAdditionalParameter(propertyName, boundSql.getAdditionalParameter(propertyName));
                }
            }

            invocation.getArgs()[0]= newMs;
		}
		   return invocation.proceed();
	}
	public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
	public Object plugin(Object arg0) {
		// TODO Auto-generated method stub
		 return Plugin.wrap(arg0, this);
	}
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub


	}

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws java.sql.SQLException
     */
    private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms,
    		 SqlSource newSqlSource) {
    		Builder builder = new Builder(ms.getConfiguration(),
    		ms.getId(), newSqlSource, ms.getSqlCommandType());
    		builder.resource(ms.getResource());
    		builder.fetchSize(ms.getFetchSize());
    		builder.statementType(ms.getStatementType());
    		builder.keyGenerator(ms.getKeyGenerator());
//    		builder.keyProperty(ms.getKeyProperty());
    		builder.timeout(ms.getTimeout());
    		 builder.parameterMap(ms.getParameterMap());
    		builder.resultMaps(ms.getResultMaps());
    		builder.cache(ms.getCache());
    		MappedStatement newMs = builder.build();
    		return newMs;
    		}


}
