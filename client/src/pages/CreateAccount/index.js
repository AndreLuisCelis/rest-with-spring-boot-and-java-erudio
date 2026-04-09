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
            <section className="form-card">
                <div className="form-branding">
                    <img src={logoImage} alt="Library Logo" />
                </div>

                <form onSubmit={cadastrar}>
                    <div className="form-header">
                        <h1>Create Account</h1>
                        <p>Join our community and start managing your books</p>
                    </div>

                    <div className="form-group">
                        <label>Username</label>
                        <input
                            placeholder="Choose your username"
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input
                            type="email"
                            placeholder="Enter your email"
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input
                            type="password"
                            placeholder="Create a password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                        />
                    </div>

                    <button className="button" type="submit">Create Account</button>

                    <p className="form-footer">
                        Already have an account? <Link to="/">Sign in here</Link>
                    </p>
                </form>
            </section>
        </div>
    )

}