import { Outlet, Link, useNavigate } from 'react-router-dom';

export default function App() {
  const nav   = useNavigate();
  const user  = localStorage.getItem('username');

  const logout = () => {
    localStorage.clear();
    nav('/login', { replace: true });
  };

  return (
    <div className="min-h-screen flex flex-col">
      {/* ––– header ––– */}
      <header className="flex items-center justify-between px-6 py-3 bg-violet-700 text-white">
        <h1 className="font-semibold text-lg">🎓 Research Monitor</h1>

        {user ? (
          <div className="space-x-3">
            <span>{user}</span>
            <button onClick={logout} className="btn-secondary">Logout</button>
          </div>
        ) : (
          <div className="space-x-3">
            <Link to="/login"    className="btn-secondary">Login</Link>
            <Link to="/register" className="btn-outline">Register</Link>
          </div>
        )}
      </header>

      {/* ––– body ––– */}
      <div className="flex flex-1">
        <aside className="w-48 border-r p-4 space-y-2 text-sm">
          <Link to="/"        className="block">Dashboard</Link>
          <Link to="/new"     className="block">New Work</Link>
          <Link to="/evals"   className="block">Evaluations</Link>
          <Link to="/profile" className="block">Profile</Link>
        </aside>

        <main className="flex-1 p-6">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
