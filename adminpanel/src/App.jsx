import React, { useContext, useEffect, useState } from 'react'
import { ToastContainer, toast } from 'react-toastify';
import Header from './components/header/Header'
import Navbar from './components/navbar/Navbar'
import AddFood from './pages/AddFood/AddFood'
import { Route, Routes } from 'react-router-dom'
import ListFood from './pages/ListFood/ListFood'
import Orders from './pages/Orders/Orders'
import Login from './components/Login/Login';
import { StoreContext } from './StoreContext/StoreContext';
import { jwtDecode } from 'jwt-decode';

const App = () => {

  

  const [navbarVisible, setNavbarVisible]=useState(true);
   const {token}=useContext(StoreContext);
  const toggleNavbarVisible=()=>{
    setNavbarVisible(!navbarVisible);
  }

  // useEffect(() => {
  //   if (token) {
  //     const userRole = jwtDecode(token).role;

  //     if (userRole === "ROLE_USER") {
  //       window.location.href = "http://localhost:5173"; // user page
  //     } else if (userRole === "ROLE_ADMIN") {
  //       window.location.href = "http://localhost:5177"; // admin page
  //     }
  //   }
  // }, [token]);

  return (
    <div>
      <ToastContainer/>
      <Header toggleNavbarVisible={toggleNavbarVisible}/>
      <div className="main-container">
      {token && <Navbar navbarVisible={navbarVisible}/>}
      
      <Routes className="body-container">
      
        <Route path="/add" element={<AddFood/>}></Route>
        <Route path="/list" element={<ListFood/>}></Route>
        <Route path="/orders" element={<Orders/>}></Route>
        <Route path="/" element={<ListFood/>}></Route>
        {!token && <Route path="/login" element={<Login/>}></Route>}

      </Routes>
      
      </div>
      
    </div>
  )
}

export default App

