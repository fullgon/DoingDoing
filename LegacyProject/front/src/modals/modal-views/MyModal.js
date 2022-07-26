import { useEffect, useState } from 'react';
import ReactModal from 'react-modal'
import DetailSchedule from './DtailSchedule';
import ModifySchedule from './ModifySchedule';
import NewSchedule from './NewSchedule';
import styles from './modals.module.css'

const modal = {
    new: NewSchedule,
    detail: DetailSchedule,
    modify: ModifySchedule
}

const MyModal = ({ onSubmit, onClose, type, scheduleNo }) => {
    const Modal = modal[type] 
    
    return(
        <ReactModal isOpen className={styles.modal}>
            <Modal onSubmit={onSubmit} onClose={onClose} scheduleNo={scheduleNo}/>
        </ReactModal>
    );
};

export default MyModal;