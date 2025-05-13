import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Layout from './components/Layout';
import Dashboard from './components/Dashboard';
import NewWork from './components/CreateWork';
import Evaluations from './components/Evaluations';
import Profile from './components/Profile';
import Login from './components/Login';
import Register from './components/Register';
import Protected from './components/ProtectedRoute';

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout/>,
    children: [
      { index: true, element: <Protected><Dashboard/></Protected> },
      { path: 'new', element: <Protected><NewWork/></Protected> },
      { path: 'evaluations', element: <Protected><Evaluations/></Protected> },
      { path: 'profile', element: <Protected><Profile/></Protected> },
    ]
  },
  { path: '/login',    element: <Login/>    },
  { path: '/register', element: <Register/> }
]);

ReactDOM.createRoot(document.getElementById('root'))
        .render(<RouterProvider router={router}/>);
