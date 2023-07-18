import React, { useState } from "react";
import ChatWindow from "./component/ChatWindow";
import Login from "./component/Login";



function App() {
  const [user,setUser] = useState({
    username: '',
    receiver: '',
    connected: false,
    message: ''
  })

  return (
    <>
      {user.connected ? 
      <ChatWindow user={user} setUser={setUser}/>
        :
      <Login user={user} setUser={setUser} />
      }
    </>
  );
}

export default App;
