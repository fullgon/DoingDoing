import { useEffect, useState } from 'react';
import ReactModal from 'react-modal'
import DetailSchedule from './DtailSchedule';
import ModifySchedule from './ModifySchedule';
import NewSchedule from './NewSchedule';

const modal = {
    new: NewSchedule,
    detail: DetailSchedule,
    modify: ModifySchedule
}

const MyModal = ({ onSubmit, onClose, type, scheduleNo }) => {
    const Modal = modal[type] 
    
    return(
        <ReactModal isOpen>
            <Modal onSubmit={onSubmit} onClose={onClose} scheduleNo={scheduleNo}/>
        </ReactModal>
    );
};

export default MyModal;