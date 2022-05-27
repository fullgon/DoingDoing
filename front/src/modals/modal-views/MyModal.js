import { useEffect, useState } from 'react';
import ReactModal from 'react-modal'
import DetailSchedule from './DtailSchedule';
import NewSchedule from './NewSchedule';

const MyModal = ({ onSubmit, onClose, type }) => {
    let Modal = null;
    if(type == "New"){
        Modal = NewSchedule;
    }
    else{
        Modal = DetailSchedule;
    }
    return(
        <ReactModal isOpen>
            <Modal onSubmit={onSubmit} onClose={onClose} />
        </ReactModal>
    );
};

export default MyModal;