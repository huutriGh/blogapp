package com.aptech.blogapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.aptech.blogapi.model.Blog;

public interface BlogRepository extends PagingAndSortingRepository<Blog, Integer>, CrudRepository<Blog, Integer> {

}
