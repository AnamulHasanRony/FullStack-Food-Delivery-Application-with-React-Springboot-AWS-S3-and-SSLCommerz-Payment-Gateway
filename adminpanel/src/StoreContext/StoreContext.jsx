import { createContext, useEffect, useState } from "react";

export const StoreContext=createContext(null);
export const StoreContextProvider=(props)=>{

    const [token, setToken] = useState(
    () => localStorage.getItem("token") || ""
  );
    
   
    const contextValue={
       
       token,
       setToken

    }
    useEffect(()=>{
        async function loadToken(){
           
            if(localStorage.getItem("token")){
              setToken(localStorage.getItem("token"));
            }
        } 

        loadToken();
    },[])
    return(
        <StoreContext.Provider value={contextValue}>
            {props.children}
        </StoreContext.Provider>
    )
}