import "primereact/resources/themes/lara-light-purple/theme.css";
import "primereact/resources/primereact.min.css";
import "primeicons/primeicons.css";
import React, {useEffect, useState} from 'react';
import logo from './logo.png';
import './App.css';
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FilterMatchMode} from "primereact/api";
import {InputText} from "primereact/inputtext";

type ResultProps = {
    id: string,
    name: string,
    phone: string,
    petName: string,
    petType: string,
    allergy: string,
    notes: string
};

function App() {
    const [result, setResult] = useState<Array<ResultProps>>([]);
    const [loading, setLoading] = useState(true);
    const [globalFilterValue, setGlobalFilterValue] = useState('');
    const [filters, setFilters] = useState({
        global: { value: null, matchMode: FilterMatchMode.CONTAINS },
        name: { value: null, matchMode: FilterMatchMode.CONTAINS },
        petName: { value: null, matchMode: FilterMatchMode.CONTAINS },
        phone: { value: null, matchMode: FilterMatchMode.CONTAINS },
        notes: { value: null, matchMode: FilterMatchMode.CONTAINS }
    });

    useEffect(() => {
        const api = async () => {
            const data = await fetch("/clients", {
                method: "GET"
            });
            const jsonData = await data.json();
            setResult(jsonData);
        };
        api().then(r => setLoading(false));
    }, []);

    const onGlobalFilterChange = (e: any) => {
        const value = e.target.value;
        let _filters = { ...filters };

        _filters['global'].value = value;

        setFilters(_filters);
        setGlobalFilterValue(value);
    };

    const renderHeader = () => {
        return (
            <div className="flex justify-content-end">
                <span className="p-input-icon-left">
                    <i className="pi pi-search" />
                    <InputText value={globalFilterValue} onChange={onGlobalFilterChange} placeholder="Buscar todos" />
                </span>
            </div>
        );
    };

    const header = renderHeader();

    return (
        <div className="App">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
                <DataTable value={result} paginator rows={10} dataKey="id" filters={filters} filterDisplay="row"
                           loading={loading} globalFilterFields={['name', 'phone', 'petName', 'notes']} header={header}
                           emptyMessage="No hay clientes todavia">
                    <Column field="name" header="Nombre cliente" filter filterPlaceholder="Buscar por nombre" sortable/>
                    <Column field="phone" header="Telefono" filter filterPlaceholder="Buscar por telefono"/>
                    <Column field="petName" header="Nombre mascota" filter filterPlaceholder="Buscar por mascota" sortable/>
                    <Column field="petType" header="Tipo de mascota"/>
                    <Column field="allergy" header="Alergias"/>
                    <Column field="notes" header="Notas" filter filterPlaceholder="Buscar por notas" />
                </DataTable>
            </div>
        </div>
    );
}

export default App;
