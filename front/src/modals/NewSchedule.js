import { useState } from 'react';
import ReactModal from 'react-modal'
import Switch from '@mui/material/Switch';

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const NewSchedule = ({onSubmit, onClose}) =>{
    
    const [schedule, setSchedule] = useState({public: true,});

    const onChangeTitle = (e) =>{
        setSchedule({...schedule, title: e.target.value})
    }

    const onChangeDate = (e) =>{
        setSchedule({...schedule, date: e.target.value});
    }

    const onChangeContent = (e) =>{
        setSchedule({...schedule, content: e.target.value});
    }
    
    const handleClickSubmit = () => {
        console.log(schedule);
        onSubmit();
    }

    const handleClickCancel = () =>{
        onClose();
    }
    
    return(
        <ReactModal isOpen>
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
                <button onClick={handleClickSubmit}>확인</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>
        </ReactModal>
    );
};

export default NewSchedule;