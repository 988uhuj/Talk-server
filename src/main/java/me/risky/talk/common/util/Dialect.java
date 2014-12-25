/**
 * 
 */
package me.risky.talk.common.util;

/**
 * 数据库方言
 * @author lyl
 *
 */
public abstract class Dialect {
	/**
	 * 数据库类型枚举
	 * @author lyl
	 *
	 */
	public static enum Type{  
        MYSQL,  
        ORACLE  
    }  
    
	/**
	 * 生成物理分页查询语句
	 * @param sql 原始SQL
	 * @param offset 跳过的行数
	 * @param limit 限制的行数
	 * @return String
	 */
    public abstract String getLimitString(String sql, int offset, int limit);
}
