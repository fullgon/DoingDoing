import { useEffect, useState } from 'react';
import ReactModal from 'react-modal'
import Switch from '@mui/material/Switch';
import axios from 'axios';

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const DetailSchedule = ({onSubmit, onClose, scheduleNo}) =>{
    
    const handleClickSubmit = () => {
        onSubmit();
    }

    const handleClickCancel = () =>{
        onClose();
    }

    const [schedule, setSchedule] = useState({
        title:"제목",
        content:"안녕하십니까 내용입니다.",
        isPublic: false,
        endDate: "2022-05-31"
    });

    
    const getSchedule = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.post(`/api/schedules/${userId}/${scheduleNo}`,{},{
                headers:{
                    'Content-Type' : 'application/json',
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            })

            if(res.status == 200){
                setSchedule(res.data.schedule);
            }
            
        }catch{
            //error
            alert("에러");
        }
    }

    useEffect(() =>{
        getSchedule();
    },[])

    return(
        <div>
            <div>                
                <p><input type="text" value={schedule.title} disabled/></p>
                <p>
                    <input type="date" value={schedule.endDate} disabled/>
                    <Switch {...label} checked={schedule.isPublic} disabled/>
                    공유
                </p>
                <textarea value={schedule.content} disabled/>
            </div>
            <div>
                <button onClick={handleClickSubmit}>확인</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>
        </div>
    )
            
        
}

export default DetailSchedule;