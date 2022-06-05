import styles from "../css/ToDoComplete.module.css"
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import axios from "axios";

function ToDoComplete(){

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
                    isComplete: 1,
                }
            });
            if(res.status == 200){
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
        <div className={`${styles.body} ${styles.item}`}>
            <h1>완료된 일정</h1>
            { schedules.length != null ? 
            schedules.map((schedule)=>(
                <p key={schedule.no}>
                    <input type="checkbox" checked={true} onChange={(event)=>{isCompleteSchedule(event.target.checked, schedule.no)}}/>
                    <a onClick={() => {handleClick(schedule.no)}}>{schedule.title}</a>
                </p>
            )) :
            null
            }
        </div>
    )
}

export default ToDoComplete;