import { Container, Grid, Paper, TextField, Typography } from "@mui/material";
import { useState } from "react";
import { useLocation } from "react-router-dom";
const Blog = () => {
  const location = useLocation();
  console.log(location);
  const initBlog = {
    blogId: "",
    url: "",
    title: "",
  };

  const { state } = location;

  const [blog, setBlog] = useState(state?.blog ? state.blog : initBlog);

  const handleChange = (event) => {
    setBlog((prev) => ({
      ...prev,
      [event.target.name]:
        event.target.type === "checkbox"
          ? event.target.checked
          : event.target.value,
    }));
  };
  return (
    <Container sx={{ padding: "20px" }}>
      <Paper sx={{ padding: "20px" }}>
        <Grid container spacing={3}>
          <Grid item md={12} xs={12}>
            <Typography align="center" variant="h4">
              {blog.id ? "Edit" : "Create"}
            </Typography>
          </Grid>
          <Grid item md={6} xs={12}>
            <TextField
              label="Id"
              disabled={blog.blogId ? true : false}
              value={blog.blogId}
              fullWidth
              size="small"
              name="id"
              onChange={handleChange}
            />
          </Grid>
          {blog.id ? (
            <Grid item md={6} xs={12}>
              <TextField
                label="Url"
                disabled
                value={blog.url}
                fullWidth
                size="small"
                name="url"
                onChange={handleChange}
              />
            </Grid>
          ) : null}

          <Grid item md={6} xs={12}>
            <TextField
              label="Title"
              value={blog.title}
              fullWidth
              size="small"
              name="title"
              onChange={handleChange}
            />
          </Grid>
        </Grid>
      </Paper>
    </Container>
  );
};

export default Blog;
