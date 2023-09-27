import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import SockJS from 'sockjs-client';


export default function Login() {

  const [email,setEmail] = useState("");
  const [password,setPassword] = useState("");
  const [isLoading,setLoading] = useState(false);



  return (
    <div className='login'>
       <div className='Auth'>
      
        <form className='Auth-form'  >
            <label htmlFor="email">Email</label>
            <input value={email}  onChange={(e)=>{setEmail(e.target.value)}} className='form_input' type="email" id="email" name="email" minLength="3" placeholder='Mail Adresini Giriniz' required/>
  
            <label htmlFor="password">Şifre</label>
            <input value={password}  onChange={(e)=>{setPassword(e.target.value)}} className='form_input' type="password" id="password" name="password" placeholder='Şifrenizi Giriniz' minLength="3" required/>

            
            <button   className='form_btn' id='login-btn'  type="button" value="Gönder">Giriş Yap</button>
        </form>
        <Link to={"/register"} className='register-btn'>Kayıtlı Değilseniz Kayıt olun</Link>
    </div>
       
    </div>
  )
}
