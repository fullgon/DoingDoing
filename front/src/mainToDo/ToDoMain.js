import ToDo from "./component/ToDo"
import ToDoDate from "./component/ToDoDate"
import ToDoComplete from "./component/ToDoComplete"
import styles from "./ToDoMain.module.css"

import useModals from "../modals/useModals"
import Modals, { modals } from "../modals/Modals"

function TODoMain(){
    const { openModal } = useModals();

    const handleClick = () => {
        openModal(modals.newSchedule, {
            onSubmit: () => {
                console.log('비지니스 로직 처리...');
            },
        });
    };
    return(
        <div>
            <div className={styles.container}>
                <ToDo/>
                <ToDoDate/>
                <ToDoComplete/>
            </div>
            <button className={styles.btn} onClick={handleClick}>등록</button>
            <Modals/>
        </div>
    )
}

export default TODoMain;