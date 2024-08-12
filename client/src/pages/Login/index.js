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
            <section className="form">
                <img src={logoImage} alt="Erudio Logo"/>
                <form onSubmit={login}>
                    {apiUrl}
                    <h2>Access your Account or: 
                        <Link to='/createAccount'>Create Account</Link></h2>
                    <input
                        placeholder="Username"
                        value={username}
                        onChange={e => setUsername(e.target.value)}
                    />
                    <input
                        type="password" placeholder="Password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                    />

                    <button className="button" type="submit">Login</button>
                </form>

            </section>

            {/* <img src={padlock} alt="Login"/> */}

        </div>
    )

}