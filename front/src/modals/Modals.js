import React, { useContext } from "react";
import { ModalsDispatchContext, ModalsStateContext } from "./ModalsContext";
import MyModal from "./modal-views/MyModal";
import NewSchedule from "./modal-views/NewSchedule";

export const modals = {
    myModal: MyModal,
    newSchedule : NewSchedule,
};

const Modals = () => {
    const openedModals = useContext(ModalsStateContext);
    const { close } = useContext(ModalsDispatchContext);
    
    return openedModals.map((modal, index) => {

        const { Component, props} = modal;
        const { onSubmit, type,...restProps} = props;

        const onClose = () =>{
            close(Component);
        };

        const handleSubmit = async () =>{
            if(typeof onSubmit === 'function'){
                await onSubmit();
            }
            onClose();
        };
        return (
        <Component
         {...restProps}
         key={index}
         onClose={onClose}
         onSubmit={handleSubmit}
         type={type}/>
        );
    });
};

export default Modals;