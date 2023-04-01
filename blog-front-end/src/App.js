import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Blog from "./page/Blog.page";
import BlogList from "./page/BlogList.page";

const router = createBrowserRouter([
  {
    path: "/",
    element: <BlogList />,
  },
  {
    path: "/blog",
    element: <Blog />,
  },
]);
const App = () => {
  return <RouterProvider router={router} />;
};

export default App;
