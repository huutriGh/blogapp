package com.aptech.blogapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aptech.blogapi.model.Blog;
import com.aptech.blogapi.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> getPage(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Blog> page = blogRepository.findAll(pageable);
        return page;

    }

    @Override
    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }

}
