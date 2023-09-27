import React, { useEffect, useState } from 'react'

import {logo} from "../../assets/Avatar/avatar0.png"

export default function UpdatePopup({setOpen}) {

    const [firstName,setFirstName] = useState("");
    const [lastName,setLastName] = useState("");

    const closePopUp = (e) =>{
       
        if(e.target !== e.currentTarget){
        e.stopPropagation();
       }else{
        setOpen(false)
       }
       
    }


    return (
        <div className='popup'  onClick={closePopUp}>
            
           
        <div className="avatar-menu">
          <div className="avatar-menu-form">
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
            placeholder='Yeni İsminizi Giriniz'
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
            placeholder='Yeni Soyisminizi Giriniz'
            required
          />
        </div>
          </div>


               <div className="logos">
               {[0 ,1, 2, 3, 4, 5, 6, 7 ,8 ,9 , 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
                .map(num => (
                        <div key={num}  className={'avatar-menu-logo'}>
                             <img className='avatar-menu-pp' src={require(`../../assets/Avatar/avatar${num}.png`)} alt="sdas" />
                        </div>
                ))}
               </div>
            </div>
        </div>
      )
    }