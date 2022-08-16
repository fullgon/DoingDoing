import styled from 'styled-components'

const Banner = styled.div`
display: flex;
flex-direction: column;
width: 30vw;
height: 10vh;
//border: solid 1px yellow;
justify-content: flex-end;
margin-left: 1%;
`;

const TitleBar = styled.div`
display: flex;
//border: solid 1px violet;
height: 50%;
`;

const ColorLine = styled.div`
width: 5px;
background: gray;
`

const Title = styled.div`
font-size: 1rem;
padding-left: 5%;
align-self: center;
`;

const Hr = styled.hr`
width: 100%;
`
const ScheduleBanner = ({title}) =>{
    return(
        <Banner>
            <TitleBar>
                <ColorLine></ColorLine>
                <Title>{title}</Title>
            </TitleBar>
            <Hr/>
        </Banner>
    )
}

export default ScheduleBanner;