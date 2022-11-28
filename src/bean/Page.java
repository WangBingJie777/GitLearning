package bean;



import java.util.List;

/**分页模型对象。
 * 泛型是具体的模块的javaBean对象
 * @author wbj
 * @date 2022/7/24 - 9:03
 */

public class Page<T> {
    public static final Integer PAGE_SIZE=4;
    private Integer pageNo;//当前页面
    private Integer pageTotal;//总页码
    private Integer pageSize=PAGE_SIZE;//当前页大小（显示的记录数量）设一个默认值，当用户不传的时候用。
    private Integer pageTotalCount;//总记录数
    private List<T> items;//当前页数据。（可以是Book对象的数据、User对象的等等等。用了泛型就是妙。）
    private String url;//表示分页条的请求地址



    //指定泛型参数的值，就指定了具体对哪个对象进行分页
    public Page() {

    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        ///**
        // * 数据边界的有效检查。前面那个是在前端做检查，这个是在服务器检查，做2手准备，防止懂行的人越过
        // * 前端，直接访问这里，造成错误，故为了安全性，必须要在服务器里也设置检查。
        // * if检查写到这里是非常妙的。每次设置pageNo的值前，都要检查一遍。
        // 但是，放到这pageTotal总为null，直接就报错，导致页面数据也显示不了。
        // */
        //if(pageNo < 1){
        //    pageNo = 1;
        //}
        //if(pageNo > pageTotal){
        //    pageNo = pageTotal;
        //}
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
