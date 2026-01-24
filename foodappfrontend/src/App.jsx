import React from 'react';
import Navbar from './components/Navbar/Navbar';
import { Route,Routes } from 'react-router-dom';
import Home from './pages/Home/Home';
import ContactUs from './pages/ContactUs/ContactUs';
import ExploreFood from './pages/ExploreFood/ExploreFood';
import FoodDetails from './pages/FoodDetails/FoodDetails';
import Cart from './pages/Cart/Cart';
import PlaceOrder from './pages/PlaceOrder/PlaceOrder';
import Login from './components/Login/Login';
import Register from './components/Register/Register';
import { ToastContainer } from 'react-toastify';
import OrderSuccess from './pages/OrderSuccess/OrderSuccess';

const App = () => {
  return (
    <div>
      <ToastContainer/>
      <Navbar/>
      <Routes>
        
        <Route path='/contact' element={<ContactUs/>}></Route>
        <Route path='/explore' element={<ExploreFood/>}></Route>
        <Route path='/food/:id' element={<FoodDetails/>}></Route>
        <Route path='/cart' element={<Cart/>}></Route>
        <Route path='/order/success' element={<OrderSuccess/>}></Route>
        <Route path='/order' element={<PlaceOrder/>}></Route>
        <Route path='/login' element={<Login/>}></Route>
        <Route path='/register' element={<Register/>}></Route>
        <Route path='/' element={<Home/>}></Route>
        

      </Routes>
      
    </div>
  )
}

export default App;
