import axios from "axios";
const API_URL='http://localhost:8080/api/foods';

export const addFood = async(foodData, image,token)=>{
        
        const formData=new FormData();
        formData.append('food', JSON.stringify(foodData));
        formData.append('file', image);
        try{
            const response=await axios.post(API_URL, formData,{headers:{"Content-Type":"multipart/form-data", "Authorization": `Bearer ${token}`}});
          }
        catch(error){
            console.log(error);
           return error;

        }
        
    }

    export const getAllFoodListService = async()=>{
        try{
            const foodListResponse=await axios.get(API_URL);
            console.log(foodListResponse.data)
            return foodListResponse.data;
          }
        catch(error){
            console.log("error while geting food", error);
           throw error;

        }
        
    }

    export const deleteFoodService = async(foodId, token)=>{
        try{
            const Response=await axios.delete(API_URL+'/'+foodId, {headers: { Authorization: `Bearer ${token}` }});
            return Response.status==204;
          }
        catch(error){
            console.log("error while deleting food", error);
           throw error;

        }
        
    }

    
