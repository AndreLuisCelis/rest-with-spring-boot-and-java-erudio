import axios from 'axios';

const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080';

const api = axios.create({
    baseURL: apiUrl ,
});

export default api;