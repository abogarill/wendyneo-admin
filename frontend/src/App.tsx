import React, {useEffect, useState} from 'react';
import logo from './logo.png';
import './App.css';

type resultProps = {
    id: string;
    name: string;
    phone: string;
    petName: string;
    petType: string;
    allergy: string;
    notes: string;
};

function App() {
    const [result, setResult] = useState<resultProps[]>([]);

    useEffect(() => {
        const api = async () => {
            const data = await fetch("/clients", {
                method: "GET"
            });
            const jsonData = await data.json();
            setResult(jsonData);
        };

        api();
    }, []);

    return (
        <div className="App">
            <img src={logo} className="App-logo" alt="logo"/>
            <h2>Clients</h2>
            <div className="App-intro">
                {result.map((value) => {
                    return (
                        <div>
                            <div>{value.id}</div>
                            <div>{value.name}</div>
                            <div>{value.phone}</div>
                            <div>{value.petName}</div>
                            <div>{value.petType}</div>
                            <div>{value.allergy}</div>
                            <div>{value.notes}</div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default App;
