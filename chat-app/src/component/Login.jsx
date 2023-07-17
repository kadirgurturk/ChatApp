import React, { useEffect, useState } from 'react'
import SockJS from 'sockjs-client';

var stompClient =null;

export default function Login({user, setUser}) {



    const handleUsername=(event)=>{
        const {value}=event.target;
        setUser({...user,"username": value});
    }



    const onConnected = () => {
        setUser({...user,"connected": true});
    }

  return (
    <div className='login'>
          <input
                id="user-name"
                placeholder="Enter your name"
                name="userName"
                value={user.username}
                onChange={handleUsername}
                margin="normal"
              />
              <button type="button" onClick={onConnected}>
                    connect
              </button> 

    </div>
  )
}
