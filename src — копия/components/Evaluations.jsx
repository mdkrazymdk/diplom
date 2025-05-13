import { useEffect, useState } from 'react';
import api from '../api';

export default function Evaluations() {
  const [rows, setRows] = useState([]);
  const [err , setErr ] = useState('');

  useEffect(() => {
    api.get('/evaluations/my')
       .then(r => setRows(r.data ?? []))
       .catch(e => setErr(e.message));
  }, []);

  if (err)          return <p className="text-red-600">{err}</p>;
  if (!rows.length) return <p>У вас пока нет оценок</p>;

  return (
    <ul className="space-y-2">
      {rows.map(ev => (
        <li key={ev.id} className="border p-3 rounded">
          <b>{ev.stageName}</b> — {ev.score}/5
          <div className="text-sm text-gray-500">{ev.comment}</div>
        </li>
      ))}
    </ul>
  );
}
