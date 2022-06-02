import { useEffect, useState } from 'react';
import useModals from "../../modals/useModals"
import { modals } from "../../modals/Modals"
import Switch from '@mui/material/Switch';
import axios from 'axios';

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const DetailSchedule = ({onClose, scheduleNo}) =>{

    const { openModal } = useModals();
    
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
            }
        }catch(e){
            //에러
        }
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
                <button onClick={handleClickModify}>수정</button>
                <button onClick={handleClickDelete}>삭제</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>
        </div>
    )
            
        
}

export default DetailSchedule;