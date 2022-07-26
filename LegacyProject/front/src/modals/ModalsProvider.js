import React, { useMemo, useState } from "react";
import { ModalsDispatchContext, ModalsStateContext } from "./ModalsContext";

const ModalsProvider = ({children}) =>{
    const [openedModals, setOpenedModals] = useState([]);

    const open = (Component, props) => {
        setOpenedModals((modals) =>{//modals는 openedModals 변수를 뜻함
            return [...modals, { Component, props }];
        });
    };

    const close = (Component) => {
        setOpenedModals((modals) =>{
            return modals.filter((modal) =>{
                return modal.Component !== Component;
            });
        });
    };

    const dispatch = useMemo(() => ({ open, close }), []);
    console.log(openedModals);
    
    return(
        <ModalsStateContext.Provider value={openedModals}>
            <ModalsDispatchContext.Provider value={dispatch}>
                {children}
            </ModalsDispatchContext.Provider>
        </ModalsStateContext.Provider>
    );
};

export default ModalsProvider;