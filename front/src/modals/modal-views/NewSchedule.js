import { useState } from 'react';
import Switch from '@mui/material/Switch';
import axios from 'axios';
import { useNavigate } from "react-router-dom"

import styles from './modals.module.css'

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
        <div className={styles.container}>
            <div className={styles['input-box']}>
                <input type="text" placeholder="제목" onChange={onChangeTitle} className={styles.title}/>
                <div className={styles['flex-end']}>
                    <input type="date" placeholder="기한" onChange={onChangeDate} className={styles.date}/>
                    <Switch {...label} defaultChecked />
                    공유
                </div>
                <textarea placeholder='내용' onChange={onChangeContent} className={styles['text-box']}/>
            </div>
            <div div className={styles['flex-end']}>
                <button onClick={handleClickSubmit}>등록</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>
        </div>
    );
};

export default NewSchedule;