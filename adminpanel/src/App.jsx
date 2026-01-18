import React, { useState } from 'react'
import { ToastContainer, toast } from 'react-toastify';
import Header from './components/header/Header'
import Navbar from './components/navbar/Navbar'
import AddFood from './pages/AddFood/AddFood'
import { Route, Routes } from 'react-router-dom'
import ListFood from './pages/ListFood/ListFood'
import Orders from './pages/Orders/Orders'

const App = () => {

  const [navbarVisible, setNavbarVisible]=useState(true);
  const toggleNavbarVisible=()=>{
    setNavbarVisible(!navbarVisible);
  }
  return (
    <div>
      <ToastContainer/>
      <Header toggleNavbarVisible={toggleNavbarVisible}/>
      <div className="main-container">
      <Navbar navbarVisible={navbarVisible}/>
      
      <Routes className="body-container">
      
        <Route path="/add" element={<AddFood/>}></Route>
        <Route path="/list" element={<ListFood/>}></Route>
        <Route path="/orders" element={<Orders/>}></Route>
        <Route path="/" element={<AddFood/>}></Route>

      </Routes>
      
      </div>
      
    </div>
  )
}

export default App

