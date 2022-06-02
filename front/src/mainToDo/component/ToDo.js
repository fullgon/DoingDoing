import styles from "../css/ToDo.module.css"
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"
import { useEffect, useState } from "react";
import axios from "axios";

function ToDo(){
    
    const todo = "첫 번째 일정입니다";

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

    //체크박스 클릭 시 일정 완료 & 미완료 처리하는 api요청 필요

    const getSchedules = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.get(`/api/schedules/${userId}`,{
                headers:{
                    'Authorization' : localStorage.getItem('accessToken'),
                },
                params:{
                    isComplete: 0,
                    hasDeadLine: 0,
                }
            });
            if(res.status == 200){
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
        <div className={`${styles.body} ${styles.item}`}>
            <h1>할 일</h1>
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

export default ToDo;