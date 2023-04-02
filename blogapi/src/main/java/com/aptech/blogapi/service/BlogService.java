package com.aptech.blogapi.service;

import org.springframework.data.domain.Page;

import com.aptech.blogapi.model.Blog;

public interface BlogService {

    public Page<Blog> getPage(int pageNo, int pageSize);
    public void delete(Blog blog);
    public void updateOrAdd(Blog blog);
   

}
