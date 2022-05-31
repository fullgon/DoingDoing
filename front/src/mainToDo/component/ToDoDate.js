import axios from "axios";
import { useEffect, useState } from "react";
import styles from "../css/ToDoDate.module.css"


function ToDoDate(){

    const [todo, setTodo] = useState("");
  
    
    return(
        <div className={`${styles.item} ${styles.body}`}>
            <h1>할 일(Limit){todo}</h1>
            
            
            
        </div>
    )
}

export default ToDoDate;