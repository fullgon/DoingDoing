import ReactModal from 'react-modal'

const MyModal = ({ isOpen }) => {
    return(
        <ReactModal isOpen>
            <div>모달입니다.</div>
        </ReactModal>
    );
};

export default MyModal;