import styles from "../css/ToDo.module.css"
import useModals from "../../modals/useModals"
import Modals, { modals } from "../../modals/Modals"
import { useEffect, useState } from "react";
import axios from "axios";

function ToDo(){
    
    const todo = "첫 번째 일정입니다";

    const { openModal } = useModals();
    const [ schedules, setSchedules ] = useState(null);
    const handleClick = (scheduleNo) => {
        openModal(modals.myModal, {
            onSubmit: () => {
                console.log('비지니스 로직 처리...--상세일정');
            },
            type:"detail",
            scheduleNo,
        });
    };

    const getSchedules = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.post(`/api/schedules/${userId}`,{
                isComplete: false,
                isTimeOut: false,
            },{
                headers:{
                    'Content-Type' : 'application/json',
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            })
            if(res.status == 200){
                setSchedules(res.data.schedules);
            }
            
        }catch{
            //error
        }
    }

    useEffect(()=>{
        getSchedules();
    }, []);

    return(
        <div className={`${styles.body} ${styles.item}`}>
            <h1>할 일</h1>
            {schedules != null ? 
            schedules.map((schedule)=>{
                <p key={schedule.no}>
                    <input type="checkbox" />
                    <a onClick={() => {handleClick(schedule.no)}}>{schedule.title}</a>
                </p>
            }) :
            <div>
                <input type="checkbox" />
                <a onClick={() => {handleClick(5)}}>{todo}</a>
            </div>
            }
        </div>
        
    )
}

export default ToDo;