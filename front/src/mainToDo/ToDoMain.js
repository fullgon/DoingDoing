import ToDo from "./component/ToDo"
import ToDoDate from "./component/ToDoDate"
import ToDoComplete from "./component/ToDoComplete"
import styles from "./ToDoMain.module.css"
import { useNavigate } from "react-router-dom"

import useModals from "../modals/useModals"
import { modals } from "../modals/Modals"
import { useState, useEffect, useRef } from "react"

const TOTAL_SLIDES = 2;
const MOVE_PERCENT = 117;
function TODoMain(){

    const navigate = useNavigate();
    const { openModal } = useModals();
    
    const handleClick = () => {
        openModal(modals.myModal, {
            onSubmit: () => {
                console.log('--새로운 일정 등록');
            },
            type: "new",
        });
    };
    const goToUserInfo = () => {
        navigate('/users');
    }

    
    const [currentSlide, setCurrentSlide] = useState(1);
    const doRef = useRef(null);
    const dateRef = useRef(null);
    const completeRef = useRef(null);
    
    const nextSlide = () =>{
        if(currentSlide >= TOTAL_SLIDES){
            setCurrentSlide(0);
        }
        else{
            setCurrentSlide(currentSlide + 1);
        }
    }

    const prevSlide = () =>{
        if(currentSlide == 0){
            setCurrentSlide(TOTAL_SLIDES);
        }
        else{
            setCurrentSlide(currentSlide - 1);
        }
    }

    console.log(currentSlide)

    useEffect(()=>{
        const percent = MOVE_PERCENT * 2;
        switch(currentSlide){
            case 0:
                doRef.current.style.transition = "all 0.5s ease-in-out";
                doRef.current.style.transform = `translateX(${MOVE_PERCENT}%) scale(1)`;

                dateRef.current.style.transition = "all 0.5s ease-in-out";
                dateRef.current.style.transform = `translateX(${MOVE_PERCENT}%) scale(0.8)`;

                completeRef.current.style.transition = "all 0.5s ease-in-out";
                completeRef.current.style.transform = `translateX(-${percent}%) scale(0.8)`;
                break;

            case 1:
                doRef.current.style.transition = "all 0.5s ease-in-out";
                doRef.current.style.transform = `translateX(0%) scale(0.8)`;

                dateRef.current.style.transition = "all 0.5s ease-in-out";
                dateRef.current.style.transform = `translateX(0%) scale(1)`;

                completeRef.current.style.transition = "all 0.5s ease-in-out";
                completeRef.current.style.transform = `translateX(0%) scale(0.8)`;
                break;

            case 2:
                doRef.current.style.transition = "all 0.5s ease-in-out";
                doRef.current.style.transform = `translateX(${percent}%) scale(0.8)`;

                dateRef.current.style.transition = "all 0.5s ease-in-out";
                dateRef.current.style.transform = `translateX(-${MOVE_PERCENT}%) scale(0.8)`;

                completeRef.current.style.transition = "all 0.5s ease-in-out";
                completeRef.current.style.transform = `translateX(-${MOVE_PERCENT}%) scale(1)`;
                break;
        }
    }, [currentSlide]);

    return(
        <div className={styles['schedule-main']}>
            <div className={styles.header}>
                <button onClick={goToUserInfo}>개인정보</button>
            </div>
            
            <div className={styles.container}>
                <div className={styles.slide} ref={doRef}><ToDo/></div>
                <div className={styles.slide} ref={dateRef}><ToDoDate/></div>
                <div className={styles.slide} ref={completeRef}><ToDoComplete/></div>
            </div>
            <div className={styles.arrow}>
                <button onClick={prevSlide}>{"<<"}</button>
                <button onClick={nextSlide}>{">>"}</button>
            </div>
            {/*   <ToDoComplete/>
            </div>
            <div className={styles.container}>
                <ToDoDate/>
            </div>*/}
            <button className={styles.btn} onClick={handleClick}>등록</button>
        </div>
    )
}

export default TODoMain;