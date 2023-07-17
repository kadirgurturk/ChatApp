import React, { useEffect,useState } from 'react'
import {over} from 'stompjs';
import SockJS from 'sockjs-client';

var stompClient =null;
export default function ChatWindow({user, setUser}) {
  const [privateChats, setPrivateChats] = useState(new Map());     
  const [publicChats, setPublicChats] = useState([]); 
  const [tab,setTab] =useState("CHATROOM");

    useEffect(()=>{
      let Sock = new SockJS('http://localhost:8080/ws');
      stompClient = over(Sock);
      stompClient.connect({},onConnected, onError);
    },[])



    const onError = (err) => {
        console.log(err);
        
    }

    const onConnected = () => {
        setUser({...user,"connected": true});
        stompClient.subscribe('/chatroom/public', onMessageReceived);
        stompClient.subscribe('/user/'+user.username+'/private', onPrivateMessage);
        userJoin();
    }

    const userJoin=()=>{
        var chatMessage = {
          senderName: user.username,
          status:"JOIN"
        };
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
  }

    const onMessageReceived = (payload)=>{
      var payloadData = JSON.parse(payload.body);
      switch(payloadData.status){
          case "JOIN":
              if(!privateChats.get(payloadData.sender)){
                  privateChats.set(payloadData.sender,[]);
                  setPrivateChats(new Map(privateChats));
              }
              break;
          case "MESSAGE":
              publicChats.push(payloadData);
              setPublicChats([...publicChats]);
              break;
        }
    }


      const onPrivateMessage = (payload)=>{
        var payloadData = JSON.parse(payload.body);
        if(privateChats.get(payloadData.sender)){
            privateChats.get(payloadData.sender).push(payloadData);
            setPrivateChats(new Map(privateChats));
        }else{
            let list =[];
            list.push(payloadData);
            privateChats.set(payloadData.sender,list);
            setPrivateChats(new Map(privateChats));
        }
      }

      const handleMessage =(event)=>{
        const {value}=event.target;
        setUser({...user,"message": value});
    }

    const sendValue=()=>{
      if (stompClient  && stompClient.connected) {
        var chatMessage = {
          senderName: user.username,
          message: user.message,
          status:"MESSAGE"
        };
        console.log(chatMessage);
        stompClient.send("/app/message", {}, JSON.stringify(chatMessage));
        setUser({...user,"message": ""});
      }
    }

    const sendPrivateValue=()=>{
      if (stompClient  && stompClient.connected) {
        var chatMessage = {
          senderName: user.username,
          receiverName:tab,
          message: user.message,
          status:"MESSAGE"
        };
        
        if(user.username !== tab){
          privateChats.get(tab).push(chatMessage);
          setPrivateChats(new Map(privateChats));
        }
        stompClient.send("/app/private-message", {}, JSON.stringify(chatMessage));
        setUser({...user,"message": ""});
      }
  }



  return (
    <div className='chat-window'>
       <div className="member-list">
                <ul>
                    <li onClick={()=>{setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                    {[...privateChats.keys()].map((name,index)=>(
                        <li onClick={()=>{setTab(name)}} className={`member ${tab===name && "active"}`} key={index}>{name}</li>
                    ))}
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
                <input type="text" className="input-message" placeholder="enter the message" value={user.message} onChange={handleMessage} /> 
                <button type="button" className="send-button" onClick={sendValue}>send</button>
            </div>
        </div>}

        {tab!=="CHATROOM" && <div className="chat-content">
                <ul className="chat-messages">
                    {[...privateChats.get(tab)].map((chat,index)=>(
                        <li className={`message ${chat.sender === user.username && "self"}`} key={index}>
                            {chat.sender !== user.username && <div className="avatar">{chat.sender}</div>}
                            <div className="message-data">{chat.message}</div>
                            {chat.sender === user.username && <div className="avatar self">{chat.sender}</div>}
                        </li>
                    ))}
                </ul>

                <div className="send-message">
                    <input type="text" className="input-message" placeholder="enter the message" value={user.message} onChange={handleMessage} /> 
                    <button type="button" className="send-button" onClick={sendPrivateValue}>send</button>
                </div>
            </div>}

    </div>
  )
}
