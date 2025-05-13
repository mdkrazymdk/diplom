import { useState }   from 'react';
import { useNavigate} from 'react-router-dom';
import api            from '../api';

export default function CreateWork() {
  const [title, setTitle] = useState('');
  const [desc,  setDesc ] = useState('');
  const [err,   setErr  ] = useState('');
  const nav = useNavigate();

  const submit = async e => {
    e.preventDefault();
    try {
      await api.post('/research', { title, description: desc });
      nav('/');
    } catch (ex) {
      setErr(ex.response?.data?.error || ex.message);
    }
  };

  return (
    <form onSubmit={submit} className="space-y-4 max-w-lg">
      <h2 className="text-xl font-semibold">Новая работа</h2>
      {err && <p className="text-red-600">{err}</p>}

      <input
        value={title}
        onChange={e => setTitle(e.target.value)}
        placeholder="Заголовок"
        className="input"
        required
      />
      <textarea
        value={desc}
        onChange={e => setDesc(e.target.value)}
        placeholder="Описание"
        className="textarea"
      />
      <button className="btn-primary">Создать</button>
    </form>
  );
}
