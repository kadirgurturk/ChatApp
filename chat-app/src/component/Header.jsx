import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { TiTimes } from "react-icons/ti";
import UpdatePopup from './Popup/UpdatePopup';

export default function Header() {

  const [isOpen,setOpen] = useState(false);

  const handlePopup = () =>{
    setOpen(true)
  }


  return (
    <nav>
          <ul className='links'>       
       
            <li onClick={handlePopup} className='nav-user'> <img alt='profile' src={require(`../assets/Avatar/avatar2.png`)}/></li>

            <li  className='nav-quit'><Link to={"/login"}><TiTimes size={"40px"}/></Link></li>
        </ul>
        {isOpen && <UpdatePopup  setOpen={setOpen}/>}
    </nav>
  )
}
