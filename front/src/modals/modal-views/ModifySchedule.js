import { useEffect, useState } from 'react';
import Switch from '@mui/material/Switch';
import axios from 'axios';
import { useNavigate } from "react-router-dom"

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const ModifySchedule = ({onSubmit, onClose, scheduleNo}) =>{
    
    const navigate = useNavigate();
    const [schedule, setSchedule] = useState({});

    const onChangeTitle = (e) =>{
        setSchedule({...schedule, title: e.target.value})
    }

    const onChangeDate = (e) =>{
        if(e.target.value == ""){
            setSchedule({...schedule, endDate: 0});
            return true;
        }
        setSchedule({...schedule, endDate: e.target.value});
    }

    const onChangeContent = (e) =>{
        setSchedule({...schedule, content: e.target.value});
    }

    const modifySchedule = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.put(`/api/schedules/${userId}/${scheduleNo}`,
            schedule,
            {
                headers:{
                    'Content-Type' : 'application/json',
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            })    
        }catch(e){
            //error
            alert("에러");
            console.log(e);
        }
    }
    
    const getSchedule = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.get(`/api/schedules/${userId}/${scheduleNo}`,{
                headers:{
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            })

            if(res.status == 200){
                if(res.data.endDate){
                    setSchedule(res.data);
                    return true;
                }
                setSchedule(res.data);
            }
            
        }catch(e){
            //error
            alert("데이터 불러오기 에러");
            console.log(e);
        }
    }

    const handleClickSubmit = () => {
        modifySchedule();
        //navigate(0);
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