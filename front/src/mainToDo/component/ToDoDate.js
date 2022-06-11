import axios from "axios";
import { useEffect, useState } from "react";
import styles from "../css/ToDoDate.module.css"
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"
import { useNavigate } from "react-router-dom"


function ToDoDate(){

    const navigate = useNavigate();
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
                setSchedules(res.data)
            }
            
        }catch(e){
            alert("에러");
            console.log("스케줄 불러오기 에러",e);
        }
    }

    const isCompleteSchedule = async (isComplete, scheduleNo) => {
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.put(`/api/schedules/${userId}/${scheduleNo}`,
            { isComplete },
            {
                headers:{
                    'Content-Type' : 'application/json',
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            })    
            if(res.status == 204){
                //일정 완료처리 시 새로고침
                navigate(0);
            }
        }catch(e){
            //error
            alert("에러");
            console.log(e);
        }
    }

    useEffect(()=>{
        getSchedules();
    }, []);
    
    return(
        <div className={styles.container}>
            <h1>ToDo(Limit)</h1>
            <div className={styles.schedules}>
                { schedules.length != null ? 
                schedules.map((schedule)=>(
                    <div key={schedule.no} className={styles['schedule-box']}>
                        <div className={styles.schedule}>
                            <div className={styles['check-point']}> </div>
                            <a className={styles.title} onClick={() => {handleClick(schedule.no)}}>{schedule.title}</a>
                        </div>
                        <a className={styles.date} onClick={() => {handleClick(schedule.no)}}>{schedule.endDate}</a>
                        <input type="checkbox" className={styles.checkbox}
                        onChange={(event)=>{isCompleteSchedule(event.target.checked, schedule.no)}}/>
                    </div>
                )) :
                null
                }
            </div>
        </div>
    )
}

export default ToDoDate;