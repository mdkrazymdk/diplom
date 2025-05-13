// pages/NewWork.jsx
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import api from '../api'

export default function NewWork() {
  const [title, setTitle]         = useState('')
  const [description, setDesc]    = useState('')
  const [err, setErr]             = useState('')
  const nav = useNavigate()

  const submit = async e => {
    e.preventDefault()
    try {
      await api.post('/research', { title, description })
      nav('/')              // после успешного создания
    } catch (e) {
      setErr(e.response?.data?.error || e.message)
    }
  }

  return (
    <form onSubmit={submit} className="max-w-sm space-y-4">
      <h1 className="text-2xl font-bold">Новая работа</h1>
      {err && <p className="text-red-600">{err}</p>}
      <input className="input" placeholder="Заголовок" value={title}
             onChange={e => setTitle(e.target.value)}/>
      <textarea className="input h-24" placeholder="Описание" value={description}
                onChange={e => setDesc(e.target.value)}/>
      <button className="btn">Создать</button>
    </form>
  )
}
