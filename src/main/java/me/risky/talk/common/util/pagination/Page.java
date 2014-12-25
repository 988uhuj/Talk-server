package me.risky.talk.common.util.pagination;

public class Page implements java.io.Serializable {
	
	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前页码
	 */
	private int pageNo = 0;
	
	/**
	 * 每页显示记录数
	 */
	private int pageSize = 10;
	
	/**
	 * 总记录数
	 */
	private int totalNum = 0;

    private int pageTotalNum = 0;
	
	/**
	 * 排序字段
	 */
	private String order = null;
	
	/**
	 * 排序规则ACS/DESC
	 */
	private String sort = null;
	
	/**
	 * 线程局部变量,分页查询时校验执行线程中是否存在分页参数对象来决定是否执行分页查询
	 */
	private static ThreadLocal<Page> context = new ThreadLocal<Page>();
	
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 使用BOOTPAG分页插件进行分页查询这里总数做特殊处理
	 * @return the totalNum
	 */
	public int getTotalNum() {
		if(totalNum == 0){
			return totalNum;
		}
        pageTotalNum = totalNum/pageSize;
		if(totalNum%pageSize != 0){
            pageTotalNum = pageTotalNum + 1;
		}
		return pageTotalNum;
	}

	/**
	 * @param totalNum the totalNum to set
	 */
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	/**
	 * 从线程变量中读取PAGE对象
	 * @return Page
	 */
	public static Page getPage(){
		return context.get();
	}
	
	/**
	 * 创建一个新的PAGE对象并放入线程变量中并返回
	 * @return Page
	 */
	public static Page createPage(){
		Page page = new Page();
		context.set(page);
		return page;
	}

	/**
	 * 从线程局部变量中移除分页查询参数
	 */
	public static void removeContext(){
		if(null != context){
			context.remove();
		}
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
