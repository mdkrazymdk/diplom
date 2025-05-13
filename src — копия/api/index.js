import axios from 'axios';

// ▸ всегда берём origin страницы, а не фиксированный порт
const api = axios.create({
  baseURL: '/api',          // 👉 будет http://localhost:5188/api  на dev‑сервере
  withCredentials: false    // JWT хранится не в cookie, а в localStorage
});

// ▸ вставляем токен, если он есть
api.interceptors.request.use(cfg => {
  const token = localStorage.getItem('token');
  if (token) cfg.headers.Authorization = `Bearer ${token}`;
  return cfg;
});

export default api;         // 👈 везде импортируем ОДИН и тот же экземпляр
