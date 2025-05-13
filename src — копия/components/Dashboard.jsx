import { useEffect, useState } from 'react';
import api from '../api';
import { Link } from 'react-router-dom';

export default function Dashboard() {
  const [works, setWorks] = useState([]);
  const [err,   setErr  ] = useState('');

  useEffect(() => {
    api.get('/research')
       .then(r => setWorks(r.data))
       .catch(e => setErr(e.message));
  }, []);

  return (
    <>
      <h1 className="text-xl font-semibold mb-4">My Research Works</h1>
      {err && <p className="text-red-600">{err}</p>}
      <ul className="space-y-2">
        {works.map(w => (
          <li key={w.id} className="border p-2 rounded">
            {w.title} — <em>{w.status}</em>
          </li>
        ))}
      </ul>
      <Link to="/new" className="btn-primary mt-4 inline-block">+ New Work</Link>
    </>
  );
}
