package com.aptech.blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aptech.blogapi.model.Blog;
import com.aptech.blogapi.service.BlogService;

@RestController
// @CrossOrigin(origins = "${security.cors.origin}", methods = {
// RequestMethod.DELETE, RequestMethod.GET,
// RequestMethod.POST, RequestMethod.PUT })
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping(path = "/blog", produces = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Page<Blog>> getBlog(@RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "${jpa.pageSize}") int pageSize) {

        return new ResponseEntity<Page<Blog>>(blogService.getPage(pageNo, pageSize), HttpStatus.OK);

    }

    @DeleteMapping(path = "/blog", consumes = {
            org.springframework.http.MediaType.APPLICATION_JSON_VALUE }, produces = {
                    org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> deleteBlog(@RequestBody Blog blog) {

        blogService.delete(blog);
        return new ResponseEntity<String>("Delete blog success", HttpStatus.OK);

    }

    @PostMapping(path = "/blog", consumes = { org.springframework.http.MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> updateOrAddBlog(@RequestBody Blog blog) {

        if (blog.getUrl().isEmpty()) {
            blog.setUrl("http:localhost:8080/blog/" + blog.getBlogId());
        }
        blogService.updateOrAdd(blog);
        return new ResponseEntity<String>("Update blog success", HttpStatus.OK);

    }

}
