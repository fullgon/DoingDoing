import styles from "../css/ToDo.module.css"

function ToDo(){
    
    const todo = "첫 번째 일정입니다";

    const onClick = () =>{
        console.log("안녕");
    }
    return(
        <div className={`${styles.body} ${styles.item}`}>
            <h1>할 일</h1>
            <input type="checkbox" /><a onClick={onClick}>{todo}</a>
        </div>
    )
}

export default ToDo;