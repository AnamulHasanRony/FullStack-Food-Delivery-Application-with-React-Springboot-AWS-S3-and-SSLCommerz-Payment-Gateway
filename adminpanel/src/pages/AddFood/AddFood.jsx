import React, { useEffect, useState } from 'react'
import './AddFood.css'
import '../../assets/assets'
import { assets } from '../../assets/assets'
import axios from 'axios'
import { addFood } from '../../services/FoodService'
import { toast } from 'react-toastify'
import { Await } from 'react-router-dom'
const AddFood = () => {
    const [image, setImage]=useState(false);
    const [data, setData]=useState({
        name:'',
        price:'',
        category:'Fast Food',
        description:''
    })

    const handleChange=(event)=>{
    const{name, value}=event.target;
    setData(prevData=>({...prevData, [name]:value}))
    }

    const handleSubmit=async(event)=>{
        event.preventDefault();

        if(!image){
            toast.error('please insert image of food');
            return;
        }

        try {
            await addFood(data,image);
            toast.success('food added successfully');
            setData({name:'',description:'',category:'Fast Food', price:''});
            setImage(null);
        } catch (error) {
            toast.error('error while adding food');
            
        }
    }
    useEffect(()=>{
    console.log(data)
    },[data])

  return (
      
      <div className="container mt-5">
        <div className="row justify-content-center">
            <div className="col-lg-6 col-md-8 col-sm-12">
                <div className="card shadow-lg">
                    <div className="card-body">
                        <h3 className="card-title text-center mb-4">Add Food</h3>
                        <form onSubmit={handleSubmit}>

                             <div className="mb-3 ">
                                <label htmlFor="image" className="form-label">
                                    <img src={image?URL.createObjectURL(image):assets.uploadImage} alt="" width={100}></img>
                                </label>
                                <input 
                                    type="file" 
                                    className="form-control" 
                                    id="image" 
                                    name='image' 
                                    hidden
                                    onChange={(event)=>{setImage(event.target.files[0])}}
                                />
                            </div>


                            <div className="mb-3">
                                <label htmlFor="name" className="form-label">Name</label>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    id="name" 
                                    placeholder="Enter food name"
                                    name='name' 
                                    required onChange={handleChange} value={data.name}
                                />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="category" className="form-label">Category</label>
                                <select
                                    className="form-control" 
                                    id="category" 
                                    name='category'
                                    required 
                                    onChange={handleChange} value={data.category}
                                >
                                    <option value="Fast Food">Fast Food</option>
                                    <option value="Beverages">Beverages</option>
                                    <option value="Desserts">Desserts</option>
                                    <option value="Local Classics">Local Classics</option>
                                    <option value="International Dishes">International Dishes</option>
                                    <option value="Coffee and Tea">Coffee and Tea</option>
                                    <option value="Specialty Diets">Specialty Diets</option>
                                 </select>
                            </div>

                            <div className="mb-3">
                                <label htmlFor="price" className="form-label">Price</label>
                                <input 
                                    type="number" 
                                    className="form-control" 
                                    id="price" 
                                    placeholder="Enter food price" 
                                    name='price'
                                    required onChange={handleChange} value={data.price} 
                                />
                            </div>

                            <div className="mb-3">
                                <label htmlFor="description" className="form-label">Description</label>
                                <textarea 
                                    className="form-control" 
                                    id="description" 
                                    rows="4" 
                                    placeholder="Write food desciption here"
                                    required
                                    name='description' onChange={handleChange} value={data.description}
                                ></textarea>
                            </div>

                            
                            <div className="text-center">
                                <button type="submit" className="btn btn-primary btn-lg" >Add Food</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

  )
}

export default AddFood
