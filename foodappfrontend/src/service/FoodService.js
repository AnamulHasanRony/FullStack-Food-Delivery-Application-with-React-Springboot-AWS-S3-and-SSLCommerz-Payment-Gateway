import axios from "axios";
const API_URL='http://localhost:8080/api/foods';
export const getFoodlist=async()=>{
    try {
         const responseFoodList=await  axios.get(API_URL);
         return responseFoodList.data;

    } catch (error) {
        console.log("error", error);
        throw error;
    }
    }

  export  const getFoodData=async(id)=>{
    
       try {
        const responseFood=await axios.get('http://localhost:8080/api/foods/'+id);
        if(responseFood.status==200){
           return responseFood.data;
        }
       } catch (error) {
        console.log('error while getting food data',error);
        throw error;
       }
        

    }    