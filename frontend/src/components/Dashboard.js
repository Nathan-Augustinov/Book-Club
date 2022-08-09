import React from "react"
import logoImage from "../resources/logo.png"
import { TextField, IconButton, InputAdornment } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search"
import Box from '@mui/material/Box';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Book from "./Book";

const Dashboard = () => {
    const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
        setValue(newValue);
    };

    return (
        <div>
            <div className="dashboardHeader">
                <img className="logoImage" src={logoImage} alt=""/>
                <TextField
                    className="searchDiv"
                    label="Search books"
                    InputProps={{
                        className: "searchBar",
                        endAdornment: (
                        <InputAdornment>
                            <IconButton>
                            <SearchIcon />
                            </IconButton>
                        </InputAdornment>
                        )
                    }}
                />
                <button type="submit" className="logout_btn">Logout</button>
            </div>
            <div>
                <Box >
                    <Tabs 
                        TabIndicatorProps={{style: {background:'#D1E7E0'}}}
                        value={value} 
                        onChange={handleChange} 
                        variant="fullWidth"
                        indicatorColor="#D1E7E0"
                        textColor="#46522A"
                        centered
                    >
                        <Tab label="All Books" />
                        <Tab label="Your Books" />
                        <Tab label="Available Books" />
                        <Tab label="Rented books" />
                        <Tab label="Waiting list" />
                    </Tabs>
                </Box> 
            </div>  
            <div className="container bookslist">
                <div className="div_container">
                    <div className="flex_child">
                        <Book />
                    </div>
                    <div className="flex_child">
                        <Book />
                    </div>
                </div>
                <div className="div_container">
                    <div className="flex_child">
                        <Book />
                    </div>
                    <div className="flex_child">
                        <Book/>
                    </div>
                </div>
                <div className="div_container">
                    <div className="flex_child">
                        <Book />
                    </div>
                    <div className="flex_child">
                        <Book />
                    </div>
                </div>
                <div className="div_container">
                    <div className="flex_child">
                        <Book />
                    </div>
                    <div className="flex_child">
                        <Book />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;