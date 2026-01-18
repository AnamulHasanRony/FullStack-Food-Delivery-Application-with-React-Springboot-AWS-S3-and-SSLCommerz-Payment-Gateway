import React, { useEffect, useState } from 'react'
import { assets } from '../../assets/assets'
import { deleteFoodService, getAllFoodListService } from '../../services/FoodService';
import { toast } from 'react-toastify';
import './ListFood.css'

const ListFood = () => {
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
      const success=await deleteFoodService(foodId);
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
        
        <div key={item.id} className="card col-sm-6 col-md-4 col-lg-2 mb-4">
          <img src={item.imageUrl} className="card-img-top" alt="..."/>
          <div className="card-body">
            <h4 className="card-title">{item.name}</h4>
          </div>
            <ul className="list-group list-group-flush">
              <li className="list-group-item">Tk. {item.price}</li>
              <li className="list-group-item"> {item.category}</li>
              <li className="list-group-item"> {item.description}</li>
              <button type="button" className="btn btn-danger" onClick={()=>{deleteFoodFromFoodList(item.id)}}>Danger</button>


            </ul>
         
        </div>


      

      )
    })
    
        

    }

      </div>
    
  )
}

export default ListFood
