import { useEffect, useState } from 'react';
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"
import Switch from '@mui/material/Switch';
import axios from 'axios';
import { useNavigate } from "react-router-dom"

import styles from './modals.module.css'

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const DetailSchedule = ({onClose, scheduleNo}) =>{

    const { openModal } = useModals();
    const navigate = useNavigate();
    
    const handleClickModify = () => {
        onClose();
        openModal(modals.myModal, {
            onSubmit: () => {
                console.log('비지니스 로직 처리...--일정수정으로 이동');
            },
            type:"modify",
            scheduleNo,
        });
    }

    const handleClickCancel = () =>{
        onClose();
    }

    const handleClickDelete = () =>{
        deleteSchedule();
        navigate(0);
        onClose();
    }

    const deleteSchedule = async () =>{
        try{
            const userId = localStorage.getItem('userId');
            const res = await axios.delete(`/api/schedules/${userId}/${scheduleNo}`,
            {
                headers:{
                    'Authorization' : localStorage.getItem('accessToken'),
                }
            });

            if(res.status == 200){
                console.log('게시글 삭제 완료');
                navigate(`/schedule`);
            }
        }catch(e){
            //에러
        }
    }

    const [schedule, setSchedule] = useState({});

    
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
                    return;
                }
                setSchedule(res.data);
            }
            
        }catch(e){
            //error
            console.log(e);
            alert("에러");
        }
    }
    useEffect(() =>{
        getSchedule();
    },[])

    return(
        <div className={styles.container}>
            <div className={styles['input-box']}>           
                <input type="text" value={schedule.title} className={styles.title} disabled/>
                <div className={styles['flex-end']}>
                    <input type="date" value={schedule.endDate} className={styles.date}  disabled/>
                    <Switch {...label} checked={schedule.isPublic}disabled/>
                    공유
                </div>
                <textarea value={schedule.content} className={styles['text-box']} disabled/>
            </div>
            <div div className={styles['flex-end']}>
                <button onClick={handleClickModify}>수정</button>
                <button onClick={handleClickDelete}>삭제</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>
        </div>
    )
            
        
}

export default DetailSchedule;