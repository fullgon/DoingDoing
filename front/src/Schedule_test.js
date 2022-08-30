import ScheduleBanner from "./component/ScheduleBanner";
import styled from "styled-components";
import ScheduleBox from "./component/ScheduleBox";

const Container = styled.div`
display: flex;
`;

const Schedule_test = () =>{
    return(
        <Container>
            <div>
                <ScheduleBanner title="월간"/>
                <ScheduleBox/>
            </div>
            <div>
                <ScheduleBanner title="주간"/>
                <ScheduleBox/>
            </div>
            <div>
                <ScheduleBanner title="일간"/>
                <ScheduleBox/>
            </div>            
        </Container>
    )
}

export default Schedule_test;