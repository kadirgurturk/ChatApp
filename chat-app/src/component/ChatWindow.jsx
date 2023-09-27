import React, { useEffect,useState,useRef } from 'react'
import { BiSearch } from "react-icons/bi";
import { IoSend } from "react-icons/io5";
import { TiMessages } from "react-icons/ti";
import {over} from 'stompjs';
import SockJS from 'sockjs-client';

var stompClient =null;
  export default function ChatWindow({user, setUser}) {
  const [privateChats, setPrivateChats] = useState(new Map());     
  const [publicChats, setPublicChats] = useState([]); 
  const [tab,setTab] =useState("CHATROOM");



  return (
    <div className='chat-window'>
       <div className="member-list">
                <ul>
                    <li className='search-li' >
                      <input className='search-input' type='text' placeholder='Search' />
                      <BiSearch className='search-icon' size="2rem"/>
                      <TiMessages className='message-icon' size="2rem"/>
                    </li>
                    <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                
                        <li onClick={()=>{setTab()}} className={`member`} >
                          <div className='member-img'>
                              <img alt='profile' src={require(`../assets/Avatar/avatar2.png`)}/>
                              <div className='member-isActive'><div className='active'></div></div>
                          </div>
                          <div className='member-info'> 
                              <div id='name'>Kadir Gürtürk</div>
                              <div id='massege'>Last Massege</div>
                          </div>
                        </li>
  
                </ul>
            </div>

        {tab==="CHATROOM" && <div className="chat-content">
            <ul className="chat-messages">
                {publicChats.map((chat,index)=>(
                    <li className={`message ${chat.sender === user.username && "self"}`} key={index}>
                        {chat.sender !== user.username && <div className="avatar">{chat.sender}</div>}
                        <div className="message-data">{chat.message}</div>
                        {chat.sender === user.username && <div className="avatar self">{chat.sender}</div>}
                    </li>
                ))}
            </ul>
        
            <div className="send-message">
              <textarea type="text" className="input-message" placeholder="enter the message" /> 
                <IoSend className='message-sender'/>
            </div>
        </div>}

        {tab!=="CHATROOM" && <div className="chat-content">
                <ul className="chat-messages" >
                    {[...privateChats.get(tab)].map((chat,index)=>(
                        <li className={`message ${chat.sender === user.username && "self"}`} key={index}>
                            {chat.sender !== user.username && <div className="avatar">{chat.sender}</div>}
                            <div className="message-data">{chat.message}</div>
                            {chat.sender === user.username && <div className="avatar self">{chat.sender}</div>}
                        </li>
                    ))}
                </ul>

                <div className="send-message">
                  <textarea rows={3} type="text" className="input-message" placeholder="enter the message"  /> 
                    <IoSend className='message-sender'/>
                </div>
            </div>}

    </div>
  )
}
