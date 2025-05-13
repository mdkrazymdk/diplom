import { Navigate, useLocation } from 'react-router-dom';

export default function Protected({ children }) {
  const loc   = useLocation();
  const token = localStorage.getItem('token');
  return token ? children : <Navigate to="/login" state={{ from: loc }} replace/>
}
