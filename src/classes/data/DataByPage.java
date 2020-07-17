package classes.data;

import com.sun.rowset.CachedRowSetImpl;

/**
 * @version 1.0
 * @author: lyq
 * @date: 2020/5/14 22:10
 * @modified by
 */
public class DataByPage {
    CachedRowSetImpl rowSet = null;
    int pageSize = 2;
    int totalPages = 1;
    int currentPage = 1;

    public CachedRowSetImpl getRowSet() {
        return rowSet;
    }

    public void setRowSet(CachedRowSetImpl rowSet) {
        this.rowSet = rowSet;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
