import React, {useState} from 'react';
import { useHistory, Link} from 'react-router-dom';
import './styles.css';

import api from '../../services/api'

import logoImage from '../../assets/logo.svg'
// import padlock from '../../assets/padlock.png'

export default function CreateAccount() {

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    // const [confiPmpassword, setconfirmPassword] = useState('');

    const history = useHistory();

    async function cadastrar(e){
        e.preventDefault();

        const data = {
            username,
            email,
            password,
        };

        try {
            const response = await api.post('auth/createUser', data);

            // localStorage.setItem('username', username);
            // localStorage.setItem('accessToken', response.data.accessToken);
            console.log(response); 

            history.push('/')
        } catch (err) {
            alert('Login failed! Try again!');
        }
    };

    return (
        <div className="login-container">
            <section className="form">
                <img src={logoImage} alt="Erudio Logo"/>
                <form onSubmit={cadastrar}>
                    <h2>Create Account or: <Link to='/'>Sign in</Link></h2>
                    
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

                    <input
                         placeholder="Email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                    />

                    <button className="button" type="submit">Create</button>
                </form>

            </section>

            {/* <img src={padlock} alt="Login"/> */}

        </div>
    )

}