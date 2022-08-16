import ScheduleBanner from "./component/ScheduleBanner";
import styled from "styled-components";

const Container = styled.div`
display: flex;
`;

const Schedule = () =>{
    return(
        <Container>
            <ScheduleBanner title="월간"/>
            <ScheduleBanner title="주간"/>
            <ScheduleBanner title="일별"/>
        </Container>
    )
}

export default Schedule;