// pages/WorkList.jsx
import { useEffect, useState } from 'react'
import api from '../api'

export default function WorkList() {
  const [works, setWorks] = useState([])

  useEffect(() => {
    api.get('/research').then(res => setWorks(res.data))
  }, [])

  return (
    <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
      {works.map(w => (
        <article key={w.id} className="rounded-xl border p-4 shadow">
          <h3 className="font-semibold text-lg">{w.title}</h3>
          {w.description && <p className="text-sm mt-1">{w.description}</p>}
          <span className="mt-2 inline-block text-xs italic text-gray-500">{w.status}</span>
        </article>
      ))}
    </div>
  )
}
