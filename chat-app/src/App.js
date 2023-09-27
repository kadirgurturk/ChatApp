import React, { useState } from "react";
import ChatWindow from "./component/ChatWindow";
import Login from "./component/Auth/Login";
import { Route,Routes } from "react-router-dom";
import Home from "./component/Home";
import Register from "./component/Auth/Register";



function App() {

  return (
    <>
      <Routes>
          <Route path="/" element= {<Home/>}></Route>
          <Route path="/login" element= {<Login/>}></Route>
          <Route path="/register" element= {<Register/>}></Route>
      </Routes>
    </>
  );
}

export default App;
