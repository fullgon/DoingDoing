import { useState } from 'react';
import styled from 'styled-components'

const Container = styled.div`
display: flex;
`;

const ToggleHistory = ({ content }) => {
    const [icon, setIcon] = useState("▶");

    const changeIcon = () =>{
        if(icon =="▶"){
            setIcon("▼");
            return;
        }
        setIcon("▶");            
    }
    return(
        <Container onClick={()=>{
            changeIcon();

        }}>
            {icon}
            <div>{content}</div>
        </Container>
    )
}

export default ToggleHistory;