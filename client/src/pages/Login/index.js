import React, {useState} from 'react';
import { useHistory, Link} from 'react-router-dom';
import './styles.css';

import api from '../../services/api'

import logoImage from '../../assets/logo.svg'

export default function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const apiUrl = process.env.REACT_APP_API_URL;

    console.log('--->', apiUrl);
    

    const history = useHistory();

    async function login(e){
        e.preventDefault();

        const data = {
            username,
            password,
        };

        try {
            const response = await api.post('auth/signin', data);

            localStorage.setItem('username', username);
            localStorage.setItem('accessToken', response.data.accessToken
);
            console.log(response); 

            history.push('/books')
        } catch (err) {
            alert('Login failed! Try again!');
        }
    };

    return (
        <div className="login-container">
            <section className="form-card">
                <div className="form-branding">
                    <img src={logoImage} alt="Erudio Logo" />
                    <div>
                        <h1>Welcome Back</h1>
                        <p>Sign in to continue and manage your books with ease.</p>
                    </div>
                </div>

                <form onSubmit={login}>
                    <div className="form-group">
                        <label>Username</label>
                        <input
                            placeholder="Enter your username"
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            placeholder="Enter your password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </div>

                    <button className="button" type="submit">Login</button>

                    <p className="create-account">
                        Don&apos;t have an account? <Link to="/createAccount">Create one</Link>
                    </p>
                </form>
            </section>
        </div>
    )

}