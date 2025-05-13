import { useState }          from 'react';
import { Link, useNavigate } from 'react-router-dom';
import api                   from '../api';

export default function Login() {
  const [form, setForm] = useState({ username:'', password:'' });
  const [err,  setErr ] = useState('');
  const nav = useNavigate();

  const change = e => setForm(f => ({ ...f, [e.target.name]: e.target.value }));

  const submit = async e => {
    e.preventDefault();
    try {
      const { data: token } = await api.post('/users/login', form);
      localStorage.setItem('token',    token);
      localStorage.setItem('username', form.username);
      nav('/');
    } catch (ex) {
      setErr(ex.response?.data?.error || ex.message);
    }
  };

  return (
    <form onSubmit={submit} className="space-y-4 max-w-xs mx-auto">
      <h2 className="text-xl font-semibold">Login</h2>
      {err && <p className="text-red-600">{err}</p>}

      <input name="username" value={form.username} onChange={change}
             className="input" placeholder="Username" required />
      <input name="password" type="password" value={form.password} onChange={change}
             className="input" placeholder="Password" required />

      <button className="btn-primary w-full">Login</button>
      <p className="text-sm text-center">
        No account? <Link to="/register" className="underline">Register</Link>
      </p>
    </form>
  );
}
