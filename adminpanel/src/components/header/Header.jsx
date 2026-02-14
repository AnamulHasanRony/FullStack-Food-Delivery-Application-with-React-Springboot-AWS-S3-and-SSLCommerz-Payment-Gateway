//Header.js

import React, { useContext } from 'react';
import './Header.css'
import { assets } from '../../assets/assets';
import { StoreContext } from '../../StoreContext/StoreContext';
import { useNavigate } from 'react-router-dom';

const Header = ({toggleNavbarVisible}) => {

     const {token,setToken}=useContext(StoreContext);
    const navigate=useNavigate();
    const logout=()=>{
        localStorage.removeItem('token');
        setToken("");
        navigate("/");
    }
    return (
        <header>
            <div className="logosec">
                
                <img src={assets.logoImage}
                    className="logo-icn menuicn"
                    id="logo-icn"
                    alt="menu-icon" />

                    <div className="logo">Food Delivery App</div>
                    <button className='btn btn-primary'>
                        <div onClick={toggleNavbarVisible}>
                            <i className='bi bi-list btn-btn-primary'></i>
                        </div>
                    </button>
            </div>

            

            {
                    !token?
                    <>
                      <button className='btn btn-outline-primary' onClick={()=>navigate("/login")}>Login</button>
                     </>:
                    <div className='dropdown test-end'>
                        <a href="" className='d-block link-body-emphasis text-decoration-none dropdown-toggle' data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://media.geeksforgeeks.org/wp-content/uploads/20221210180014/profile-removebg-preview.png" alt="" width={40} height={40} className="rounded-circle"/>
                            <ul className='dropdown-menu text-small'>
                                <li className='dropdown-item' onClick={()=>navigate('/order')}>orders</li>
                                <li className='dropdown-item' onClick={logout}>logout</li>
                                
                            </ul>
                        </a>
                    </div>
                }

        </header>
    );
};

export default Header;