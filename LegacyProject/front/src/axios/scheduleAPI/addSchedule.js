import axios from "axios";

const addSchedule = async (schedule) =>{
    try{
        const userId = localStorage.getItem('userId');
        await axios.post(`/api/schedules/${userId}`,
        schedule,
        {
            headers:{
                'Content-Type' : 'application/json',
                'Authorization' : localStorage.getItem('accessToken'),
            }
        });
    }catch(e){
        //error
        console.log("일정 추가하기 에러",e);
        alert("에러");
    }
}

export default addSchedule;