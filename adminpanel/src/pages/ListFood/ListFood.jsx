import React, { useContext, useEffect, useState } from 'react'
import { assets } from '../../assets/assets'
import { deleteFoodService, getAllFoodListService } from '../../services/FoodService';
import { toast } from 'react-toastify';
import './ListFood.css'
import { StoreContext } from '../../StoreContext/StoreContext';


const ListFood = () => {

  const{token}=useContext(StoreContext);

  

  const [foodList,setFoodList]=useState([]);
  const getAllFoodList=async()=>{
    try {
      setFoodList(await getAllFoodListService())
      
    } catch (error) {
     toast.error("Error while geting foodlist" + error);
    }
    
  }

  const deleteFoodFromFoodList=async(foodId)=>{
    try {
      const success=await deleteFoodService(foodId, token);
      if(success){
        toast.success("food deleted succesfully!!")
        await getAllFoodList();
      }
      else{
        toast.error('food deletion failed!!')
      }
      
    } catch (error) {
     toast.error("Error while deleting food " + error);
    }
    
  }

  useEffect(()=>{
    getAllFoodList();
  }, []);

  return (
    <div className='body-container row'>
      
      
    { foodList.map((item)=>{
      return(
        
        <div key={item.id} className="card h-50 col-sm-12 col-md-8 col-lg-6 g-4 mt-5">
          <img src={item.imageUrl}  className="card-img-top" alt="..."/>
          <div className="card-body">
            <h4 className="card-title">{item.name}</h4>
          </div>
            <ul className="list-group list-group-flush">
              <li className="list-group-item">Tk. {item.price}</li>
              <li className="list-group-item"> {item.category}</li>
              <li className="list-group-item"> {item.description}</li>
              <button type="button" className="btn btn-danger" onClick={()=>{deleteFoodFromFoodList(item.id)}}>Delete</button>
            </ul>
        </div>


      

      )
    })
    
        

    }
      </div>
      
    
  )
}

export default ListFood
