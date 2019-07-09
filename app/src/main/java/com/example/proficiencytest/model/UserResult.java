
package com.example.proficiencytest.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResult {

    @SerializedName("page")
    @Expose
    private Long page;
    @SerializedName("per_page")
    @Expose
    private Long perPage;
    @SerializedName("total")
    @Expose
    private Long total;
    @SerializedName("total_pages")
    @Expose
    private Long totalPages;
    @SerializedName("data")
    @Expose
    private List<User> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserResult() {
    }

    /**
     * 
     * @param total
     * @param page
     * @param data
     * @param perPage
     * @param totalPages
     */
    public UserResult(Long page, Long perPage, Long total, Long totalPages, List<User> data) {
        super();
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
        this.data = data;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPerPage() {
        return perPage;
    }

    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }


}
