import { useEffect, useState } from 'react';
import Switch from '@mui/material/Switch';
import axios from 'axios';

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const ModifySchedule = ({onSubmit, onClose, scheduleNo}) =>{

    const [schedule, setSchedule] = useState({
        title:"제목",
        content:"안녕하십니까 내용입니다.",
        isPublic: false,
        endDate: "2022-05-31"
    });

    const onChangeTitle = (e) =>{
        setSchedule({...schedule, title: e.target.value})
    }

    const onChangeDate = (e) =>{
        setSchedule({...schedule, date: e.target.value});
    }

    const onChangeContent = (e) =>{
        setSchedule({...schedule, content: e.target.value});
    }

    const modifySchedule = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.patch(`/api/schedules/${userId}/${scheduleNo}`,
            schedule,
            {
                headers:{
                    'Content-Type' : 'application/json',
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            })    
        }catch{
            //error
            alert("에러");
        }
    }
    
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

    const handleClickSubmit = () => {
        modifySchedule();
        onSubmit();
    }

    const handleClickCancel = () =>{
        onClose();
    }


    useEffect(() =>{
        getSchedule();
    },[])

    return(
        <div>
            <div>                
                <p><input type="text" value={schedule.title} onChange={onChangeTitle}/></p>
                <p>
                    <input type="date" value={schedule.endDate} onChange={onChangeDate}/>
                    <Switch {...label} checked={schedule.isPublic} />
                    공유
                </p>
                <textarea value={schedule.content} onChange={onChangeContent}/>
            </div>
            <div>
                <button onClick={handleClickSubmit}>수정완료</button>
                <button onClick={handleClickCancel}>수정취소</button>
            </div>
        </div>
    )
            
        
}

export default ModifySchedule;