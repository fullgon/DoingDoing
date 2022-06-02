import axios from "axios";
import { useEffect, useState } from "react";
import styles from "../css/ToDoDate.module.css"
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"


function ToDoDate(){

    const { openModal } = useModals();
    const [ schedules, setSchedules ] = useState([]);
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
            const res = await axios.get(`/api/schedules/${userId}`,{
                headers:{
                    'Authorization' : localStorage.getItem('accessToken'),
                },
                params:{
                    isComplete: 0,
                    hasDeadLine: 1,
                }
            });
            if(res.status == 200){
                console.log(res);
                setSchedules((schedules) => res.data)
            }
            
        }catch(e){
            alert("에러");
            console.log("스케줄 불러오기 에러",e);
        }
    }

    useEffect(()=>{
        getSchedules();
    }, []);
    
    return(
        <div className={`${styles.item} ${styles.body}`}>
            <h1>할 일(Limit)</h1>
            { schedules.length != null ? 
            schedules.map((schedule)=>(
                <p key={schedule.no}>
                    <input type="checkbox" />
                    <a onClick={() => {handleClick(schedule.no)}}>{schedule.title}</a>
                </p>
            )) :
            null
            }
        </div>
    )
}

export default ToDoDate;