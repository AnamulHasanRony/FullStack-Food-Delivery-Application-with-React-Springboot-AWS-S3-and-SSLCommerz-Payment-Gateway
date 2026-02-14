import axios from 'axios';
import React, { useContext, useEffect, useState } from 'react'
import './Order.css'
import { StoreContext } from '../../StoreContext/StoreContext';

function Orders() {

  const [orderState,setOrderState]=useState("Order is reviewing");
  const [orderHistoryData,setOrderHistoryData]=useState([]);
  const{token} =useContext(StoreContext);

    useEffect(()=>{
            const loadOrderData=async()=>{
              try {
                const response=await axios.get("http://localhost:8080/api/order/all", {headers: { Authorization: `Bearer ${token}` }});
                setOrderHistoryData(response.data);
                console.log(response.data);
              } catch (error) {
                toast.error("Error while geting data from api calls")
              }
            }
          loadOrderData();
        },[])

        
        const handleChange=async(event,orderId)=>{
          try {
                      const res=await axios.post(`http://localhost:8080/api/order/update/${orderId}?status=${event.target.value}`,{}, {headers: { Authorization: `Bearer ${token}` }});

          } catch (error) {
            console.log(error);
          }
                 
        }


  

  return (
    <div>
      <div className="container mt-4 body-container">
    <div className="p-5 mb-4 bg-light rounded-3">
      <h2 className='text-center display-5 fw-bold'>Order History</h2>
      <hr/>
    </div>

        <table className="table table-bordered table-hover table-striped align-middle">
        <thead className="table-light">
            <tr className="sticky-top">
            <th>OrderId</th>
            <th>Customer Name</th>
            <th>Delivery Address</th>
            <th>FoodItems</th>
            <th>Quantity</th>
            <th>Payment Status</th>
            <th>Order Status</th>
            </tr>
        </thead>
        <tbody>
           {orderHistoryData.length === 0 && (
                <tr>
                <td colSpan="11" className="text-center text-muted">
                    No orders found
                </td>
                </tr>
            )}

             {orderHistoryData.map(order =>
                order.orderedItem.map((item, index) => (
                <tr key={`${order.id}-${index}`}>

                    {index === 0 && (
                    <>
                        <td rowSpan={order.orderedItem.length}>{order.id}</td>
                        <td rowSpan={order.orderedItem.length}>{order.customerName},{order.customerPhoneNo}</td>               
                        <td rowSpan={order.orderedItem.length}>
                        {order.customerAddress}, {order.customerCity}, {order.customerState}, {order.customerCountry}
                        </td>
                    </>
                    )}
                    <td>{item.name}</td>
                    <td>{item.quantity}</td>
                    {index === 0 && (
                        <>
                            {/* <td rowSpan={order.orderedItem.length}>৳{order.totalAmount}</td> */}
                            <td rowSpan={order.orderedItem.length} className={order.paymentStatus === "PAID" ?"text-success fw-bold": (order.paymentStatus === "FAILED" ?"text-danger fw-bold":"text-warning fw-bold")}>{order.paymentStatus}</td>
                            <td rowSpan={order.orderedItem.length} className={order.orderStatus === "DELIVERED" ? "text-success fw-bold" : (order.orderStatus === "FAILED" ?"text-danger fw-bold":"text-warning fw-bold")}>
                              <select value={order.orderStatus || "Under Observation"} onChange={(event)=>handleChange(event,order.id)}>
                                 <option value="Under Observation">Under Observation</option>
                                <option value="CANCEL">CANCEL</option>
                                <option value="OUT FOR DELIVERY">OUT FOR DELIVERY</option>
                                <option value="DELIVERED">DELIVERED</option>
                                <option value="COOKING">COOKING</option>
                                </select>{order.orderStatus}</td>
                        </>
                    )}
                   
                   
                </tr>
                ))
            )}
            
        </tbody>
        </table>

  </div>
    </div>
  )
}

export default Orders;

