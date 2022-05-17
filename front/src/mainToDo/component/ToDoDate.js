import axios from "axios";
import { useEffect, useState } from "react";
import styles from "../css/ToDoDate.module.css"


function ToDoDate(){

    const [todo, setTodo] = useState("");
  

    const action = () =>{
        axios.post("/api/account/signUp", {
            userId:"dd",
            password:"aa"
        }).then((response)=>{
            if(response.data){
                setTodo(response.data.userId);
            }
        }).catch(error =>{
            alert("fail");
        })
    }
    return(
        <div>
            <button onClick={action}>프록시 연결</button>
            <h1>{todo}</h1>
            
        </div>
    )
}

export default ToDoDate;