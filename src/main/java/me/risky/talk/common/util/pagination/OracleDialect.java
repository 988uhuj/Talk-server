/**
 * 
 */
package me.risky.talk.common.util.pagination;


import me.risky.talk.common.util.Dialect;

/**
 * 数据库查询物理分页ORACLE方言
 * @author lyl
 *
 */
public class OracleDialect extends Dialect {

	/**
	 * 生成物理分页查询语句
	 * @param sql 原始SQL
	 * @param offset 跳过的行数
	 * @param limit 限制的行数
	 * @return String
	 */
	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset);
		pagingSelect.append(" and rownum_ <= ").append(offset + limit);

        System.out.println("getLimitString:" + pagingSelect.toString());
		return pagingSelect.toString();
	}

}
