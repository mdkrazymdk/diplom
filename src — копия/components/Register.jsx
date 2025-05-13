import { useState }          from 'react';
import { Link, useNavigate } from 'react-router-dom';
import api                   from '../api';

export default function Register() {
  const [form, setForm] = useState({
    username:'', password:'', role:'STUDENT'
  });
  const [err, setErr] = useState('');
  const nav = useNavigate();

  const change = e => setForm(f => ({ ...f, [e.target.name]: e.target.value }));

  const submit = async e => {
    e.preventDefault();
    try {
      await api.post('/users/register', form);
      nav('/login');
    } catch (ex) {
      setErr(ex.response?.data?.error || ex.message);
    }
  };

  return (
    <form onSubmit={submit} className="space-y-4 max-w-xs mx-auto">
      <h2 className="text-xl font-semibold">Register</h2>
      {err && <p className="text-red-600">{err}</p>}

      <input name="username" value={form.username} onChange={change}
             className="input" placeholder="Username" required />
      <input name="password" type="password" value={form.password} onChange={change}
             className="input" placeholder="Password" required />

      <select name="role" value={form.role} onChange={change} className="input">
        <option value="STUDENT">STUDENT</option>
        <option value="SUPERVISOR">SUPERVISOR</option>
        <option value="ADMIN">ADMIN</option>
      </select>

      <button className="btn-primary w-full">Register</button>
      <p className="text-sm text-center">
        Have an account? <Link to="/login" className="underline">Login</Link>
      </p>
    </form>
  );
}
