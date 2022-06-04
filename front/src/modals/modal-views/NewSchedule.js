import { useState } from 'react';
import Switch from '@mui/material/Switch';
import axios from 'axios';
import { useNavigate } from "react-router-dom"

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const NewSchedule = ({onSubmit, onClose}) =>{
    
    const navigate = useNavigate();
    const [schedule, setSchedule] = useState({isPublic: true,});

    const onChangeTitle = (e) =>{
        setSchedule({...schedule, title: e.target.value})
    }

    const onChangeDate = (e) =>{
        setSchedule({...schedule, endDate: e.target.value});
    }

    const onChangeContent = (e) =>{
        setSchedule({...schedule, content: e.target.value});
    }
    
    const addSchedule = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.post(`/api/schedules/${userId}`,
            schedule,
            {
                headers:{
                    'Content-Type' : 'application/json',
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            });
            console.log(res);
        }catch(e){
            //error
            console.log(e);
            alert("에러");
        }
    }

    const handleClickSubmit = () => {
        addSchedule();
        navigate(0);
        onSubmit();
    }

    const handleClickCancel = () =>{
        onClose();
    }
    
    return(
        <div>
            <div>                
                <p><input type="text" placeholder="제목" onChange={onChangeTitle}/></p>
                <p>
                    <input type="date" placeholder="기한" onChange={onChangeDate}/>
                    <Switch {...label} defaultChecked />
                    공유
                </p>
                <textarea placeholder='내용' onChange={onChangeContent}/>
            </div>
            <div>
                <button onClick={handleClickSubmit}>등록</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>
        </div>
    );
};

export default NewSchedule;