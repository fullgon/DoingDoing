import axios from "axios";

const modifySchedule = async (schedule, scheduleNo) =>{
    try{
        const userId = localStorage.getItem('userId');
        const res = await axios.put(`/api/schedules/${userId}/${scheduleNo}`,
        schedule,
        {
            headers:{
                'Content-Type' : 'application/json',
                'Authorization' : localStorage.getItem('accessToken'),
            }
        })    
    }catch(e){
        //error
        alert("에러");
        console.log(e);
    }
}

export default modifySchedule;