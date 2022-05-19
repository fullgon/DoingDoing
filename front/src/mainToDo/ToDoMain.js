import ToDo from "./component/ToDo"
import ToDoDate from "./component/ToDoDate"
import ToDoComplete from "./component/ToDoComplete"
import styles from "./ToDoMain.module.css"

import ModalsProvider from "../modals/ModalsProvider"
import useModals from "../modals/useModals"
import MyModal from "../modals/MyModal"

function TODoMain(){
    const { openModal } = useModals();

    const handleClick = () => {
        openModal(MyModal, {foo : 'bar'});
    };
    return(
        
        <div>
                <div className={styles.container}>
                    <ToDo/>
                    <ToDoDate/>
                    <ToDoComplete/>
                </div>
                <button className={styles.btn} onClick={handleClick}>등록</button>

        </div>
    )
}

export default TODoMain;