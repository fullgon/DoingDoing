import styled from 'styled-components'

const Container = styled.div`
display: flex;
`;

const Input = styled.input.attrs(props => ({
    type: "input",
}))`

`;

const NewSchedule = () =>{

    const pressEnter = (e) =>{
        if(e.key === 'Enter'){
           addScheduleApiCall(e);
        }
    }

    const addScheduleApiCall = (data) =>{
       data.target.value = "";
    };
    return (
        <Container>
            <Input onBlur={addScheduleApiCall} onKeyPress={pressEnter}/>
        </Container>
    )
}

export default NewSchedule;