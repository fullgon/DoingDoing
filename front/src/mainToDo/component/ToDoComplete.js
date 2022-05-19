import styles from "../css/ToDoComplete.module.css"

function ToDoComplete(){
    return(
        <div className={`${styles.body} ${styles.item}`}>
            <h1>완료된 일정</h1>
        </div>
    )
}

export default ToDoComplete;