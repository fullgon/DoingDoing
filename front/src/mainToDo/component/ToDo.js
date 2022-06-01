import styles from "../css/ToDo.module.css"
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"
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

    //체크박스 클릭 시 일정 완료 & 미완료 처리하는 api요청 필요

    const getSchedules = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.get(`/api/schedules/${userId}`,{
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