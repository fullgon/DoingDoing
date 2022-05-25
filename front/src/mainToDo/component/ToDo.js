import styles from "../css/ToDo.module.css"
import useModals from "../../modals/useModals"
import Modals, { modals } from "../../modals/Modals"
import { useEffect } from "react";

function ToDo(){
    
    const todo = "첫 번째 일정입니다";

    const { openModal } = useModals();

    const handleClick = () => {
        openModal(modals.newSchedule, {
            onSubmit: () => {
                console.log('비지니스 로직 처리...');
            },
        });
    };

    useEffect(()=>{
        //일정 가져오기
    },[])

    return(
        <div className={`${styles.body} ${styles.item}`}>
            <h1>할 일</h1>
            <input type="checkbox" /><a onClick={handleClick}>{todo}</a>
        </div>
    )
}

export default ToDo;