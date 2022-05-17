import ToDo from "./component/ToDo"
import ToDoDate from "./component/ToDoDate"
import ToDoComplete from "./component/ToDoComplete"
import styles from "./ToDoMain.module.css"


function TODoMain(){
    return(
        <div>
            <ToDo/>
            <ToDoDate/>
            <ToDoComplete/>
        </div>
    )
}

export default TODoMain;