import styled from "styled-components";
import ToggleHistory from "./component/ToggleHistory";

const Container = styled.div`
display: flex;
`;


const History_test = () => {
    return (
        <Container>
            <ToggleHistory/>
        </Container>
    )
}

export default History_test;