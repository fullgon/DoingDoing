import ReactModal from 'react-modal'

const MyModal = ({ onSubmit, onClose }) => {
    const handleClickSubmit = () =>{
        onSubmit();
    }

    const handleClickCancel = () =>{
        onClose();
    }
    return(
        <ReactModal isOpen>
            <div>
                <input type="text" placeholder="제목"/>
                <input type="date" placeholder="기한"/>
            </div>
            <div>
                <button onClick={handleClickSubmit}>확인</button>
                <button onClick={handleClickCancel}>취소</button>
            </div>  
        </ReactModal>
    );
};

export default MyModal;