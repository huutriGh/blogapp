import {
    Button,
    Card,
    CardActions,
    CardContent,
    Container,
    Grid,
    Pagination,
    Stack,
    Typography,
} from "@mui/material";
import { useEffect, useState } from "react";

import { useNavigate } from "react-router-dom";
import client from "../api/client";
const BlogList = () => {
  const navigate = useNavigate();
  const [blogs, setBlog] = useState({
    data: [],
    first: true,
    last: false,
    size: 0,
    number: 0,
    totalElements: 0,
    totalPages: 0,
    empty: true,
  });

  const fetchBlog = async () => {
    const res = await client.get("/blog?pageSize=9");
    const {
      content,
      totalElements,
      totalPages,
      first,
      last,
      empty,
      number,
      size,
    } = res.data;

    setBlog((prev) => ({
      ...prev,
      data: content,
      totalElements,
      totalPages,
      first,
      last,
      empty,
      number,
      size,
    }));
    console.log(res.data);
  };

  useEffect(() => {
    fetchBlog();
  }, []);

  const handleChange = async (event, value) => {
    console.log("page: ", value);
    const res = await client.get(`/blog?pageNo=${value - 1}&pageSize=${5}`);
    console.log(`/blog?pageNo=${value - 1}&pageSize=${9}`);
    const {
      content,
      totalElements,
      totalPages,
      first,
      last,
      empty,
      number,
      size,
    } = res.data;
    setBlog((prev) => ({
      ...prev,
      data: content,
      totalElements,
      totalPages,
      first,
      last,
      empty,
      number,
      size,
    }));
  };

  const handleEdit = (blog = {}) => {
    navigate("/blog", { state: { blog } });
  };
  const handleDelete = async (blog) => {
    const res = await client.post("/blog", blog);
    console.log(blogs.data);
    const theRestBlog = blogs.data.filter((item) => item.blogId !== blog.blogId);
    console.log(theRestBlog);
    setBlog((prev) => ({
      ...prev,
      data: theRestBlog,
    }));
    console.log(res);
  };

  return (
    <Container>
      <Grid container spacing={2} rowSpacing={3}>
        <Grid item xs={12}>
          <Typography variant="h4" align="center">
            My Blogs
          </Typography>
        </Grid>
        <Grid item xs={12}>
          <Button
            size="small"
            variant="contained"
            onClick={() => navigate("/blog")}
          >
            Create Blog
          </Button>
        </Grid>

        {blogs.data.map((blog) => (
          <Grid item md={4} xs={12} key={blog.blogId}>
            <Card>
              <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                  {blog.title}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  {blog.url}
                </Typography>
              </CardContent>
              <CardActions>
                <Button size="small">View</Button>
                <Button size="small" onClick={() => handleEdit(blog)}>
                  Edit
                </Button>
                <Button size="small" onClick={() => handleDelete(blog)}>
                  Delete
                </Button>
              </CardActions>
            </Card>
          </Grid>
        ))}
        {blogs.size > 0 && (
          <Grid item xs={12}>
            <Stack spacing={2}>
              <Pagination
                count={blogs.totalPages}
                page={blogs.number + 1}
                onChange={handleChange}
              />
            </Stack>
          </Grid>
        )}
      </Grid>
    </Container>
  );
};

export default BlogList;
