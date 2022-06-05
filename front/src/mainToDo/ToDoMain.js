import ToDo from "./component/ToDo"
import ToDoDate from "./component/ToDoDate"
import ToDoComplete from "./component/ToDoComplete"
import styles from "./ToDoMain.module.css"
import { useNavigate } from "react-router-dom"

import useModals from "../modals/useModals"
import { modals } from "../modals/Modals"

function TODoMain(){

    const navigate = useNavigate();
    const { openModal } = useModals();

    const handleClick = () => {
        openModal(modals.myModal, {
            onSubmit: () => {
                console.log('비지니스 로직 처리...--새로운 일정 등록');
            },
            type: "new",
        });
    };
    const goToUserInformation = () => {
        navigate('/users');
    }
    return(
        <div>
            <div className={styles.container}>
                <ToDo/>
                <ToDoDate/>
                <ToDoComplete/>
            </div>
            <button onClick={goToUserInformation}>개인정보</button>
            <button className={styles.btn} onClick={handleClick}>등록</button>
        </div>
    )
}

export default TODoMain;