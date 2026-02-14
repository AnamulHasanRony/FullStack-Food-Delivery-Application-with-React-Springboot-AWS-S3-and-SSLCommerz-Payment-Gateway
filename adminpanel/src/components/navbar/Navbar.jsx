

import React, { useContext } from 'react';
import'./Navbar.css'
import { Link, useNavigate } from 'react-router-dom';
import { assets } from '../../assets/assets';
import { StoreContext } from '../../StoreContext/StoreContext';

const Nav = ({navbarVisible}) => {

    const {token,setToken}=useContext(StoreContext);
    const navigate=useNavigate();
    const logout=()=>{
        localStorage.removeItem('token');
        setToken("");
        navigate("/");
    }
    return (
        
        <div className={`navcontainer ${navbarVisible?'':'d-none'}`}>
            <nav className="nav mt-20">
                <div className="nav-upper-options">
                    <Link to="/add" className='text-black text-decoration-none'>
                    <div className="option2 nav-option">
                        
                        <h4></h4> 
                    </div>
                    </Link>
                                        

                    <Link to="/add" className='text-black text-decoration-none'>
                    <div className="option2 nav-option">
                         <img src="https://media.geeksforgeeks.org/wp-content/uploads/20221210183320/5.png"
                            className="nav-img"
                            alt="articles"
                        />
                        <h4>Add Food</h4> 
                    </div>
                    </Link>

                    
                    <Link to="/list" className='text-black text-decoration-none'>
                    <div className="nav-option option5">
                        <img src={assets.listImage}
                            className="nav-img"
                            alt="report"
                        />
                        <h4>Food List</h4>
                    </div>
                    </Link>

                    <Link to="/orders" className='text-black text-decoration-none'>
                        <div className="nav-option option5">
                            <img src={assets.ordersImage}
                                className="nav-img"
                                alt="institution"
                            />
                            <h4>Orders</h4>
                        </div>
                    </Link>

                    <div className="nav-option logout">
                        <img
                            src="https://media.geeksforgeeks.org/wp-content/uploads/20221210183321/7.png"
                            className="nav-img"
                            alt="logout"
                        />
                        <h4 onClick={logout}>Logout</h4>
                    </div>
                </div>
            </nav>
        </div>
    );
};

export default Nav;