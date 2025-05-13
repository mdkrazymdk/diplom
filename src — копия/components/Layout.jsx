import { Link, Outlet, useNavigate } from 'react-router-dom';

export default function Layout() {
  const nav   = useNavigate();
  const token = localStorage.getItem('token');
  const logout = () => { localStorage.removeItem('token'); nav('/'); };

  return (
    <>
      <header className="flex justify-between items-center bg-purple-700 text-white px-6 py-3">
        <h1 className="font-semibold">Research Monitor</h1>

        {token
          ? <button onClick={logout}>Logout</button>
          : <div className="space-x-2">
              <Link to="/login">Login</Link>
              <Link to="/register">Register</Link>
            </div>}
      </header>

      <main className="p-6 max-w-5xl mx-auto">
        <Outlet/>
      </main>
    </>
  );
}
