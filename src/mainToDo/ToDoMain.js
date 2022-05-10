import ToDo from "./component/ToDo"
import ToDoDate from "./component/ToDoDate"
import ToDoFinish from "./component/ToDoFinish"


function TODoMain(){
    return(
        <div>
            <ToDo/>
            <ToDoDate/>
            <ToDoFinish/>
        </div>
    )
}

export default TODoMain;