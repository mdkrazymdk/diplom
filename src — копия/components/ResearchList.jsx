import { useEffect, useState } from 'react';
import api from '../api';

export default function ResearchList() {
  const [works, setWorks] = useState([]);
  const [err,   setErr]   = useState('');

  useEffect(() => {
    api.get('/research')
       .then(r => setWorks(r.data))
       .catch(e => setErr(e.message));
  }, []);

  if (err) return <p className="text-red-600">{err}</p>;
  if (!works.length) return <p className="text-gray-500">No works yet</p>;

  return (
    <ul className="space-y-1">
      {works.map(w => (
        <li key={w.id} className="border p-3 rounded flex justify-between">
          <div>
            <strong>{w.title}</strong><br/>
            <span className="text-sm text-gray-500">{w.status}</span>
          </div>
          <span className="text-sm italic">{w.description}</span>
        </li>
      ))}
    </ul>
  );
}
