import ReactModal from 'react-modal'
import NewSchedule from './NewSchedule';

const MyModal = ({ onSubmit, onClose }) => {
    
    return(
        <ReactModal isOpen>
            <NewSchedule onSubmit={onSubmit} onClose={onClose} />
        </ReactModal>
    );
};

export default MyModal;