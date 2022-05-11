import { Axios } from "axios";
import { useEffect, useState } from "react";


function ToDoDate(){

    const [todo, setTodo] = useState("");

    useEffect(()=>{
        Axios.post("/api/date").then((response)=>{
            if(response.data){
                setTodo(response.data);
            }
            else{
                alert("fail");
            }
        })
    },[])
    return(
        <div>
            <h1>{setTodo.todo}</h1>
            <h1>{setTodo.date}</h1>
        </div>
    )
}

export default ToDoDate;