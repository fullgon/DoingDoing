import styles from "../css/ToDo.module.css"
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import axios from "axios";
import {getSchedules} from "../../axios/scheduleAPI/getSchedules"
import isCompleteSchedule from "../../axios/scheduleAPI/isCompleteSchedule";

function ToDo(){
    
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

    // const isCompleteSchedule = async (isComplete, scheduleNo) => {
    //     try{
    //         const userId = localStorage.getItem('userId');
    //         const res = await axios.put(`/api/schedules/${userId}/${scheduleNo}`,
    //         { isComplete },
    //         {
    //             headers:{
    //                 'Content-Type' : 'application/json',
    //                 'Authorization' : localStorage.getItem('accessToken'),
    //             }
    //         })    
    //         if(res.status == 204){
    //             //일정 완료처리 시 새로고침
    //             navigate(0);
    //         }
    //     }catch(e){
    //         //error
    //         alert("에러");
    //         console.log(e);
    //     }
    // }



    const checkComplete = async (isComplete, scheduleNo) =>{
        if(await isCompleteSchedule(isComplete, scheduleNo)){
            getSchedulesApi();
        }
    }

    const getSchedulesApi = async () =>{
        const data = await getSchedules(0, 0);
        if(data){ setSchedules(data) }
    }

    useEffect(()=>{
        // getSchedules(0, 0).then(data=>{
        //     if(data){ setSchedules(data); }
        // })
        getSchedulesApi();
    }, []);

    return(
        <div className={styles.container}>
            <h1>ToDo</h1>
            <div className={styles.schedules}>
                { schedules.length != null ? 
                schedules.map((schedule)=>(
                    <div key={schedule.no} className={styles['schedule-box']}>
                        <div className={styles.schedule}>
                            <div className={styles['check-point']}> </div>
                            <a className={styles.title} onClick={() => {handleClick(schedule.no)}}>{schedule.title}</a>
                        </div>
                        <input type="checkbox" className={styles.checkbox}
                        onChange={(event)=>{checkComplete(event.target.checked, schedule.no)}}/>
                    </div>
                )) :
                null
                }
            </div>
        </div>
        
    )
}

export default ToDo;