import axios from "axios";
import { useEffect, useState } from "react";
import styles from "../css/ToDoDate.module.css"


function ToDoDate(){

    const [todo, setTodo] = useState("");
  

    const action = () =>{
        axios.post("/api/account/signUp", {
            userId:"ad",
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
        <div className={`${styles.item} ${styles.body}`}>
            <h1>할 일(Limit){todo}</h1>
            <button onClick={action}>프록시 연결</button>
            
            
        </div>
    )
}

export default ToDoDate;