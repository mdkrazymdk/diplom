import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api';

export default function Profile() {
  const [me,setMe] = useState(null);
  const nav = useNavigate();

  useEffect(() => {
    api.get('/users/me')
       .then(r => setMe(r.data))
       .catch(console.error);
  }, []);

  const logout = () => {
    localStorage.removeItem('token');
    nav('/login', { replace:true });
  };

  if (!me) return null;

  return (
    <div className="space-y-2">
      <h2 className="text-xl font-semibold">Профиль</h2>
      <p><b>ID:</b> {me.id}</p>
      <p><b>Username:</b> {me.username}</p>
      <p><b>Role:</b> {me.role}</p>

      <button onClick={logout} className="btn-primary mt-4">Выйти</button>
    </div>
  );
}
