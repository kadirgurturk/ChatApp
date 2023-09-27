import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

export default function Register() {
  const [firstName,setFirstName] = useState("");
  const [lastName,setLastName] = useState("");
  const [email,setEmail] = useState("");
  const [password,setPassword] = useState("");
  const [isLoading,setLoading] = useState(false);



  return (
    <div className='login'>
  <div className='register'>
    <form className='register-form'>
      <div className="input-grid">
        <div className="firstName">
          <label htmlFor="firstName">İsim</label>
          <input
            value={firstName}
            onChange={(e) => {
              setFirstName(e.target.value);
            }}
            className='form_input'
            type="text"
            id="firstName"
            name="firstName"
            minLength="3"
            placeholder='İsminizi Giriniz'
            required
          />
        </div>

        <div className="lastName">
          <label htmlFor="lastName">Soyisim</label>
          <input
            value={lastName}
            onChange={(e) => {
              setLastName(e.target.value);
            }}
            className='form_input'
            type="text"
            id="lastName"
            name="lastName"
            minLength="3"
            placeholder='Soyisminizi Giriniz'
            required
          />
        </div>

        <div className="email">
          <label htmlFor="email">Email</label>
          <input
            value={email}
            onChange={(e) => {
              setEmail(e.target.value);
            }}
            className='form_input'
            type="email"
            id="email"
            name="userName"
            minLength="3"
            placeholder='Mail Adresini Giriniz'
            required
          />
        </div>

        <div className="password">
          <label htmlFor="password">Şifre</label>
          <input
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }}
            className='form_input'
            type="password"
            id="password"
            name="password"
            placeholder='Şifrenizi Giriniz'
            minLength="3"
            required
          />
        </div>
      </div>

      <button className='form_btn' id='register-btn' type="button" value="Gönder">
        Kayıt Olunuz
      </button>
    </form>
    <Link to={"/Login"} className='login-btn'>
      Kayıtlıysanız Giriş Yapınız
    </Link>
  </div>
</div>
  )
}