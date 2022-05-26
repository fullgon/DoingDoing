import { useState } from 'react';
import ReactModal from 'react-modal'
import Switch from '@mui/material/Switch';

const label = { inputProps: { 'aria-label': 'Switch demo' } };

const DetailSchedule = ({onSubmit, onClose}) =>{
    <ReactModal isOpen>
            <div>                
                <p><input type="text" placeholder="제목"/></p>
                <p>
                    <input type="date" placeholder="기한"/>
                    <Switch {...label} defaultChecked />
                    공유
                </p>
                <textarea placeholder='내용'/>
            </div>
            <div>
                <button onClick={handleClickSubmit}>확인</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>
        </ReactModal>
}

export default DetailSchedule;