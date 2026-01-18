//Header.js

import React from 'react';
import './Header.css'
import { assets } from '../../assets/assets';

const Header = ({toggleNavbarVisible}) => {
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


            

            <div className="searchbar">
                <input type="text"
                    placeholder="Search" />
                <div className="searchbtn">
                    <img src="https://media.geeksforgeeks.org/wp-content/uploads/20221210180758/Untitled-design-(28).png"
                        className="icn srchicn"
                        alt="search-icon" />
                </div>
            </div>

            <div className="message">
                <div className="circle"></div>
                <img src="https://media.geeksforgeeks.org/wp-content/uploads/20221210183322/8.png"
                    className="icn"
                    alt="" />
                <div className="dp">
                    <img src="https://media.geeksforgeeks.org/wp-content/uploads/20221210180014/profile-removebg-preview.png"
                        className="dpicn"
                        alt="dp" />
                </div>
            </div>

        </header>
    );
};

export default Header;